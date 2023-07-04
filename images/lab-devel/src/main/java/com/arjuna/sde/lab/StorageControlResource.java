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
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import io.minio.MinioClient;
import io.minio.messages.Item;
import io.minio.Result;
import io.minio.PutObjectArgs;
import io.minio.BucketExistsArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;

@ApplicationScoped
@jakarta.ws.rs.Path("/")
public class StorageControlResource
{
    @Inject
    public Logger log;

    @Inject
    public MongoClient mongoClient;

    @Inject
    public MinioClient minioClient;

    @POST
    @jakarta.ws.rs.Path("/reload_templates")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postReloadTemplates()
    {
        log.info("############ Lab - StorageControlResource::postReloadTemplates ############");

        try
        {
            loadTemplates(minioClient, FileSystems.getDefault().getPath("/data/templates"));
            loadTemplateInfos(mongoClient, FileSystems.getDefault().getPath("/data/template_infos"));
        }
        catch (Error error)
        {
            log.error("Error while creating request RO_Crate", error);
            return "{\"outcome\": \"Error while creating request RO_Crate\"}";
        }
        catch (Exception exception)
        {
            log.error("Exception while creating request RO_Crate", exception);
            return "{\"outcome\": \"Exception while creating request RO_Crate\"}";
        }

        return "{\"outcome\": \"Done\"}";
    }

    private void loadTemplates(MinioClient minioClient, Path templateFilesPath)
    {
        try
        {
            if (minioClient.bucketExists(BucketExistsArgs.builder().bucket("templates").build()))
            {
                  Iterable<Result<Item>> bucketObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket("templates").build());
                  for (Result<Item> bucketObject: bucketObjects)
                  {
                      log.infof("Object name: %s", bucketObject.get().objectName());
                      minioClient.removeObject(RemoveObjectArgs.builder().bucket("templates").object(bucketObject.get().objectName()).versionId(bucketObject.get().versionId()).build());
                  }

                  minioClient.removeBucket(RemoveBucketArgs.builder().bucket("templates").build());
            }
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("templates").build());

            try (DirectoryStream<Path> templatePathsStream = Files.newDirectoryStream(templateFilesPath))
            {
                for (Path templatePath: templatePathsStream)
                {
                    log.info(templatePath.getFileName());

                    InputStream inputStream = new FileInputStream(templatePath.toFile());
                    minioClient.putObject(PutObjectArgs.builder().bucket("templates").object(templatePath.toString()).stream(inputStream, -1, 10485760).contentType(MediaType.APPLICATION_JSON).build());
                    inputStream.close();
                }
            }
            catch (Error error)
            {
                throw error;
            }
            catch (Exception exception)
            {
                throw exception;
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
    }

    private void loadTemplateInfos(MongoClient mongoClient, Path templateInfoFilesPath)
    {
        try
        {
            MongoDatabase sde = mongoClient.getDatabase("sde");
            sde.getCollection("template_infos").drop();

            try (DirectoryStream<Path> templateInfoPathsStream = Files.newDirectoryStream(templateInfoFilesPath))
            {
                for (Path templateInfoPath: templateInfoPathsStream)
                {
                    log.infof("Template Info File name: %s", templateInfoPath.getFileName());

                    String   templateInfoContent = Files.readString(templateInfoPath);
                    Document document            = Document.parse(templateInfoContent);

                    sde.getCollection("template_infos").insertOne(document);
                }
            }
            catch (Error error)
            {
                throw error;
            }
            catch (Exception exception)
            {
                throw exception;
            }
        }
        catch (Error error)
        {
            log.error("Error while loading TemplateInfos", error);
        }
        catch (Exception exception)
        {
            log.error("Exception while loading TemplateInfos", exception);
        }
    }

    @POST
    @jakarta.ws.rs.Path("/empty_requests")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postEmptyRequests()
    {
        log.info("############ Lab - StorageControlResource::postEmptyRequests ############");

        try
        {
            if (minioClient.bucketExists(BucketExistsArgs.builder().bucket("requests").build()))
            {
                  Iterable<Result<Item>> bucketObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket("requests").build());
                  for (Result<Item> bucketObject: bucketObjects)
                      minioClient.removeObject(RemoveObjectArgs.builder().bucket("requests").object(bucketObject.get().objectName()).versionId(bucketObject.get().versionId()).build());
            }
        }
        catch (Error error)
        {
            log.error("Error while removing requests", error);
            return "{\"outcome\": \"Error while removing requests\"}";
        }
        catch (Exception exception)
        {
            log.error("Exception while removing requests", exception);
            return "{\"outcome\": \"Exception while removing request RO_Crate\"}";
        }

        return "{\"outcome\": \"Done\"}";
    }

    @POST
    @jakarta.ws.rs.Path("/empty_responses")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postEmptyResponses()
    {
        log.info("############ Lab - StorageControlResource::postEmptyResponses ############");

        try
        {
            if (minioClient.bucketExists(BucketExistsArgs.builder().bucket("responses").build()))
            {
                  Iterable<Result<Item>> bucketObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket("responses").build());
                  for (Result<Item> bucketObject: bucketObjects)
                      minioClient.removeObject(RemoveObjectArgs.builder().bucket("responses").object(bucketObject.get().objectName()).versionId(bucketObject.get().versionId()).build());
            }
        }
        catch (Error error)
        {
            log.error("Error while removing responses", error);
            return "{\"outcome\": \"Error while removing responses\"}";
        }
        catch (Exception exception)
        {
            log.error("Exception while removing responses", exception);
            return "{\"outcome\": \"Exception while removing response RO_Crate\"}";
        }

        return "{\"outcome\": \"Done\"}";
    }

    @POST
    @jakarta.ws.rs.Path("/reload_providers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postReloadProviders()
    {
        log.info("############ Lab - StorageControlResource::postReloadProviders ############");

        try
        {
            Path providerInfoFilesPath = FileSystems.getDefault().getPath("/data/provider_infos");

            MongoDatabase sde = mongoClient.getDatabase("sde");
            sde.getCollection("provider_infos").drop();

            try (DirectoryStream<Path> providerInfoPathsStream = Files.newDirectoryStream(providerInfoFilesPath))
            {
                for (Path providerInfoPath: providerInfoPathsStream)
                {
                    String   providerInfoContent = Files.readString(providerInfoPath);
                    Document document            = Document.parse(providerInfoContent);

                    sde.getCollection("provider_infos").insertOne(document);
                }
            }
            catch (Error error)
            {
                throw error;
            }
            catch (Exception exception)
            {
                throw exception;
            }
        }
        catch (Error error)
        {
            log.error("Error while reloading providers", error);
            return "{\"outcome\": \"Error while reloading providers\"}";
        }
        catch (Exception exception)
        {
            log.error("Exception while reloading providers", exception);
            return "{\"outcome\": \"Exception while reloading providers\"}";
        }

        return "{\"outcome\": \"Done\"}";
    }
}
