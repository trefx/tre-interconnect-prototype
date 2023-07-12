package com.arjuna.sde.sde;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.lang.Error;
import java.lang.Exception;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

class AgreementsDataSummary
{
    public String       name;
    public String       label;
    public List<String> columnFields;
    public List<String> columnLabels;

    public AgreementsDataSummary()
    {
    }

    public AgreementsDataSummary(String name, String label, List<String> columnFields, List<String> columnLabels)
    {
       this.name         = name;
       this.label        = label;
       this.columnFields = columnFields;
       this.columnLabels = columnLabels;
    }
}

@Path("/agreementsdata")
public class AgreementsDataResource
{
    @Inject
    public Logger log;

    @Inject
    public MongoClient mongoClient;

    @GET
    @Path("/summaries")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AgreementsDataSummary> getAgreementsDataSummaries()
    {
        log.info("############ SDE - AgreementsDataResource::getAgreementsDataSummaries ############");

        List<AgreementsDataSummary> list = new ArrayList<AgreementsDataSummary>();

        try
        {
            MongoCursor<Document> cursor = mongoClient.getDatabase("sde").getCollection("agreementsdata_infos").find().iterator();

            try
            {
                log.info("-- control:Summary --");
                while (cursor.hasNext())
                {
                    Document document = cursor.next();

                    log.infof("[[%s]]", document);
                    log.info("----");

                    AgreementsDataSummary agreementsDataSummary = new AgreementsDataSummary();
                    agreementsDataSummary.name         = document.getString("name");
                    agreementsDataSummary.label        = document.getString("label");
                    agreementsDataSummary.columnFields = document.getList("columnFields", String.class);
                    agreementsDataSummary.columnLabels = document.getList("columnLabels", String.class);

                    list.add(agreementsDataSummary);
                }
            }
            finally
            {
                cursor.close();
            }
        }
        catch (Error error)
        {
            log.error("Error while creating agreements data summaries", error);
            return new ArrayList<AgreementsDataSummary>();
        }
        catch (Exception exception)
        {
            log.error("Exception while creating agreements data summaries", exception);
            return new ArrayList<AgreementsDataSummary>();
        }

        return list;
    }

    @GET
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getAgreementsDataData(@QueryParam("agreements_data_name") String agreementsDataName)
    {
        log.infof("############ SDE - AgreementsDataResource::getData(%s) ############", agreementsDataName);

        JsonArray list = new JsonArray();

        try
        {
            MongoCursor<Document> cursor = mongoClient.getDatabase("sde").getCollection("ad_" + agreementsDataName).find().iterator();

            try
            {
                log.info("-- control:Data --");
                while (cursor.hasNext())
                {
                    Document document = cursor.next();

                    log.infof("[[%s|%s]]", agreementsDataName, document);
                    log.info("----");

                    list.add(document);
                }
            }
            finally
            {
                cursor.close();
            }
        }
        catch (Error error)
        {
            log.error("Error while obaining data", error);
            return new JsonArray();
        }
        catch (Exception exception)
        {
            log.error("Exception while obtaining data", exception);
            return new JsonArray();
        }

        return list;
    }
}
