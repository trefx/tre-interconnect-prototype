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
    @jakarta.ws.rs.Path("/reload_agreementsdata")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postReloadAgreementsData()
    {
        log.info("############ SDE - StorageControlResource::postReloadAgreementsData ############");

        try
        {
            Path agreementsDataInfoFilesPath = FileSystems.getDefault().getPath("/data/agreementsdata_infos");

            MongoDatabase sde = mongoClient.getDatabase("sde");
            sde.getCollection("agreementsdata_infos").drop();

            log.info("-- dev:Summary --");
            try (DirectoryStream<Path> agreementsDataInfoPathsStream = Files.newDirectoryStream(agreementsDataInfoFilesPath))
            {
                for (Path agreementsDataInfoPath: agreementsDataInfoPathsStream)
                {
                    String   agreementsDataInfoContent = Files.readString(agreementsDataInfoPath);
                    Document document                  = Document.parse(agreementsDataInfoContent);

                    log.infof("[[%s]]", document);
                    log.info("----");

                    sde.getCollection("agreementsdata_infos").insertOne(document);
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
            Path agreementsDataFilesPath = FileSystems.getDefault().getPath("/data/agreementsdata");

            MongoDatabase sde = mongoClient.getDatabase("sde");

            log.info("-- dev:Data --");
            try (DirectoryStream<Path> agreementsDataPathsStream = Files.newDirectoryStream(agreementsDataFilesPath))
            {
                for (Path agreementsDataPath: agreementsDataPathsStream)
                {
                    String collectionName = "ad_" + agreementsDataPath.getFileName().toString().replace(".json", "");
                    sde.getCollection(collectionName).drop();

                    String   agreementsDataContent = Files.readString(agreementsDataPath);
                    Document document              = Document.parse(agreementsDataContent);

                    log.infof("[[%s|%s]]", collectionName, document);
                    log.info("----");

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