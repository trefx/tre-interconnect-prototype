package com.arjuna.sde.lab;

import java.util.UUID;
import java.lang.Error;
import java.lang.Exception;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import io.vertx.core.json.JsonObject;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.minio.MinioClient;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.PutObjectArgs;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class ROCrateResponseProcessor
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Inject
    public MinioClient minioClient;

    @Blocking
    @Incoming("rp_incoming")
    public void processResponse(JsonObject responseJson)
    {
        log.info("############ Lab - ROCrateResponseProcessor::processResponse ############");

        try
        {
            if (! minioClient.bucketExists(BucketExistsArgs.builder().bucket("responses").build()))
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("responses").build());

            InputStream inputStream = new StringBufferInputStream(responseJson.toString());
            minioClient.putObject(PutObjectArgs.builder().bucket("responses").object(UUID.randomUUID().toString()).stream(inputStream, -1, 10485760).contentType(MediaType.APPLICATION_JSON).build());
            inputStream.close();
        }
        catch (Error error)
        {
            log.error("Error while processing response RO_Crate", error);
        }
        catch (Exception exception)
        {
            log.error("Exception while processing response RO_Crate", exception);
        }
    }
}
