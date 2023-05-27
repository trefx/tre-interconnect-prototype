package com.arjuna.sde.lab;

import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.DirectoryStream;

import java.io.InputStream;
import java.io.FileInputStream;

import jakarta.inject.Inject;

import jakarta.ws.rs.POST;
// import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.BucketExistsArgs;
import io.minio.RemoveBucketArgs;
import io.minio.MakeBucketArgs;

@jakarta.ws.rs.Path("/stores_reset")
@ApplicationScoped
public class StoresResetResource
{
    @Inject
    Logger log;

    @Inject
    public MongoClient mongoClient;

    @Inject
    public MinioClient minioClient;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postStoresReset()
    {
        log.info("############ Lab - StoresResetResource::postStoresReset ############");

        try
        {
            loadTemplates(minioClient, FileSystems.getDefault().getPath("/data/templates"));
        }
        catch (Error error)
        {
            log.error("Error while creating request RO_Crate", error);
            return "Error while creating request RO_Crate";
        }
        catch (Exception exception)
        {
            log.error("Exception while creating request RO_Crate", exception);
            return "Exception while creating request RO_Crate";
        }

        return "Done";
    }

    private void loadTemplates(MinioClient minioClient, Path templateFilesPath)
    {
        log.info("#### loadTemplates");
        log.info(templateFilesPath.getFileName());

        try (DirectoryStream<Path> templatePathsStream = Files.newDirectoryStream(templateFilesPath))
        {
            for (Path templatePath: templatePathsStream)
            {
                log.info(templatePath.getFileName());

                if (minioClient.bucketExists(BucketExistsArgs.builder().bucket("templates").build()))
                    minioClient.removeBucket(RemoveBucketArgs.builder().bucket("templates").build());

                minioClient.makeBucket(MakeBucketArgs.builder().bucket("templates").build());

                InputStream inputStream = new FileInputStream(templatePath.toFile());
                minioClient.putObject(PutObjectArgs.builder().bucket("templates").object(templatePath.toString()).stream(inputStream, -1, 10485760).contentType(MediaType.APPLICATION_JSON).build());
                inputStream.close();
            }
        }
        catch (Error error)
        {
            log.error("Error while loading Templates", error);
        }
        catch (Exception exception)
        {
            log.error("Exception while loading Templates", exception);
        }

        log.info("#### loadTemplates - end");
    }
}
