package com.arjuna.sde.sde;

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

@ApplicationScoped
@jakarta.ws.rs.Path("/")
public class StorageControlResource
{
    @Inject
    public Logger log;

    @Inject
    public MongoClient mongoClient;

    @POST
    @jakarta.ws.rs.Path("/reload_agreementdatas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postReloadAgreementDatas()
    {
        log.info("############ SDE - StorageControlResource::postReloadAgreementDatas ############");

        try
        {
            Path agreementDatasInfoFilesPath = FileSystems.getDefault().getPath("/data/agreementdatas_infos");

            MongoDatabase sde = mongoClient.getDatabase("sde");
            sde.getCollection("agreementdatas_infos").drop();

            try (DirectoryStream<Path> agreementDatasInfoPathsStream = Files.newDirectoryStream(agreementDatasInfoFilesPath))
            {
                for (Path agreementDatasInfoPath: agreementDatasInfoPathsStream)
                {
                    String   agreementDatasInfoContent = Files.readString(agreementDatasInfoPath);
                    Document document                  = Document.parse(agreementDatasInfoContent);

                    sde.getCollection("agreementdatas_infos").insertOne(document);
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
            log.error("Error while reloading agreements info", error);
            return "{\"outcome\": \"Error while reloading agreements data\"}";
        }
        catch (Exception exception)
        {
            log.error("Exception while reloading agreements info", exception);
            return "{\"outcome\": \"Exception while reloading agreements data\"}";
        }

        try
        {
            Path agreementDatasFilesPath = FileSystems.getDefault().getPath("/data/agreementdatas");

            MongoDatabase sde = mongoClient.getDatabase("sde");

            try (DirectoryStream<Path> agreementDatasPathsStream = Files.newDirectoryStream(agreementDatasFilesPath))
            {
                for (Path agreementDatasPath: agreementDatasPathsStream)
                {
                    String collectionName = "ad_" + agreementDatasPath.getFileName().toString().replace(".json", "");
                    sde.getCollection(collectionName).drop();

                    String   agreementDatasContent = Files.readString(agreementDatasPath);
                    Document document              = Document.parse(agreementDatasContent);

                    sde.getCollection(collectionName).insertOne(document);
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
            log.error("Error while reloading agreements data", error);
            return "{\"outcome\": \"Error while reloading agreements data\"}";
        }
        catch (Exception exception)
        {
            log.error("Exception while reloading agreements data", exception);
            return "{\"outcome\": \"Exception while reloading agreements data\"}";
        }

        return "{\"outcome\": \"Done\"}";
    }
}
