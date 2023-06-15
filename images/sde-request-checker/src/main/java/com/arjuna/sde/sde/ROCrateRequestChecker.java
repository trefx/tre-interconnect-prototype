package com.arjuna.sde.sde;

import java.lang.Error;
import java.lang.Exception;
import java.util.UUID;
import java.util.List;

import java.io.InputStream;
import java.io.StringBufferInputStream;

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
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import io.quarkus.arc.All;

import io.vertx.core.json.JsonObject;

import io.minio.MinioClient;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.PutObjectArgs;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class ROCrateRequestChecker
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Channel("rc_outgoing")
    public Emitter<RoCrate> requestEmitter;

    @Inject
    public MinioClient minioClient;

    @All
    @Inject
    public List<RequestChecker> requestCheckers;

    @Blocking
    @Incoming("rc_incoming")
    public void checkRequest(JsonObject requestObject)
    {
        try
        {
            log.info("############ SDE - ROCrateRequestChecker::checkRequest ############");

            RoCrate request = objectMapper.convertValue(requestObject, RoCrate.class);

            Boolean needsManualChecking = null;
            for (RequestChecker requestChecker : requestCheckers)
            {
                Boolean checkManually = requestChecker.check(request);
                if (needsManualChecking == null)
                    needsManualChecking = checkManually;
                else if ((checkManually != null) && checkManually.booleanValue())
                    needsManualChecking = Boolean.TRUE;
            }

            log.info("############ SDE - ROCrateRequestChecker::needsManualChecking " + needsManualChecking);

            if ((needsManualChecking == null) || needsManualChecking.booleanValue())
            {
                if (! minioClient.bucketExists(BucketExistsArgs.builder().bucket("unchecked-requests").build()))
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket("unchecked-requests").build());

                InputStream inputStream = new StringBufferInputStream(objectMapper.writeValueAsString(request));
                minioClient.putObject(PutObjectArgs.builder().bucket("unchecked-requests").object(UUID.randomUUID().toString()).stream(inputStream, -1, 10485760).contentType(MediaType.APPLICATION_JSON).build());
                inputStream.close();
            }
            else
                requestEmitter.send(request);
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
