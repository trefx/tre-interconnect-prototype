package com.arjuna.sde.sde;

import java.lang.Error;
import java.lang.Exception;
import java.util.UUID;
import java.util.List;

import java.io.InputStream;
import java.io.ByteArrayInputStream;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import edu.kit.datamanager.ro_crate.RoCrate;

import io.quarkus.arc.All;

import io.minio.MinioClient;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.PutObjectArgs;

import io.smallrye.reactive.messaging.annotations.Blocking;

import com.arjuna.sde.utils.ROCrateTransformer;

@ApplicationScoped
public class ROCrateResponseChecker
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Channel("rc_outgoing")
    public Emitter<byte[]> responseEmitter;

    @Inject
    public MinioClient minioClient;

    @All
    @Inject
    public List<ResponseChecker> responseCheckers;

    @Blocking
    @Incoming("rc_incoming")
    public void checkResponse(byte[] responseBytes)
    {
        try
        {
            log.info("############ SDE - ROCrateResponseChecker::checkResponse ############");

            RoCrate response = ROCrateTransformer.zipBytesToROC(responseBytes);

            Boolean needsManualChecking = null;
            for (ResponseChecker responseChecker : responseCheckers)
            {
                Boolean checkManually = responseChecker.check(null);
//                Boolean checkManually = responseChecker.check(responseJson);
                if (needsManualChecking == null)
                    needsManualChecking = checkManually;
                else if ((checkManually != null) && checkManually.booleanValue())
                    needsManualChecking = Boolean.TRUE;
            }

            log.info("############ SDE - ROCrateResponseChecker::needsManualChecking " + needsManualChecking);

            byte[] checkedResponseBytes = ROCrateTransformer.rocToZipBytes(response);
            if ((needsManualChecking == null) || needsManualChecking.booleanValue())
            {
                if (! minioClient.bucketExists(BucketExistsArgs.builder().bucket("unchecked-responses").build()))
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket("unchecked-responses").build());

                InputStream inputStream = new ByteArrayInputStream(checkedResponseBytes);
                minioClient.putObject(PutObjectArgs.builder().bucket("unchecked-responses").object(UUID.randomUUID().toString()).stream(inputStream, -1, 10485760).contentType(MediaType.APPLICATION_JSON).build());
                inputStream.close();
            }
            else
            {
                responseEmitter.send(checkedResponseBytes);
            }
        }
        catch (Error error)
        {
            log.error("Error while forwarding request RO_Crate", error);
        }
        catch (Exception exception)
        {
            log.error("Exception while forwarding request RO_Crate", exception);
        }
    }
}
