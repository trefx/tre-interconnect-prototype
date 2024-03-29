package com.arjuna.sde.lab;

import java.util.UUID;
import java.lang.Error;
import java.lang.Exception;

import java.io.InputStream;
import java.io.ByteArrayInputStream;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import io.vertx.core.json.JsonObject;

import edu.kit.datamanager.ro_crate.RoCrate;

import io.minio.MinioClient;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.PutObjectArgs;

import io.smallrye.reactive.messaging.annotations.Blocking;

import com.arjuna.sde.utils.ROCrateTransformer;

@ApplicationScoped
public class ROCrateRequestSender
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Inject
    public MinioClient minioClient;

    @Blocking
    @Incoming("rs_incoming")
    @Outgoing("rs_outgoing")
    public byte[] forwardRequest(byte[] requestBytes)
    {
        log.info("############ Lab - ROCrateRequestSender::forwardRequest ############");

        try
        {
            if (! minioClient.bucketExists(BucketExistsArgs.builder().bucket("requests").build()))
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("requests").build());

            InputStream inputStream = new ByteArrayInputStream(requestBytes);
            minioClient.putObject(PutObjectArgs.builder().bucket("requests").object(UUID.randomUUID().toString()).stream(inputStream, -1, 10485760).contentType(MediaType.APPLICATION_JSON).build());
            inputStream.close();

            return requestBytes;
        }
        catch (Error error)
        {
            log.error("Error while forwarding request RO_Crate", error);
            return null;
        }
        catch (Exception exception)
        {
            log.error("Exception while forwarding request RO_Crate", exception);
            return null;
        }
    }
}
