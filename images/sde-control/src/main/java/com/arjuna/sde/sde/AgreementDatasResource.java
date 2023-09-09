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

class AgreementDataSummary
{
    public String       name;
    public String       label;
    public List<String> columnFields;
    public List<String> columnLabels;

    public AgreementDataSummary()
    {
    }

    public AgreementDataSummary(String name, String label, List<String> columnFields, List<String> columnLabels)
    {
       this.name         = name;
       this.label        = label;
       this.columnFields = columnFields;
       this.columnLabels = columnLabels;
    }
}

@Path("/agreementdatas")
public class AgreementDatasResource
{
    @Inject
    public Logger log;

    @Inject
    public MongoClient mongoClient;

    @GET
    @Path("/summaries")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AgreementDataSummary> getAgreementDataSummaries()
    {
        log.info("############ SDE - AgreementDatasResource::getAgreementDataSummaries ############");

        List<AgreementDataSummary> list = new ArrayList<AgreementDataSummary>();

        try
        {
            MongoCursor<Document> cursor = mongoClient.getDatabase("sde").getCollection("agreementdatas_infos").find().iterator();

            try
            {
                while (cursor.hasNext())
                {
                    Document document = cursor.next();

                    AgreementDataSummary agreementDataSummary = new AgreementDataSummary();
                    agreementDataSummary.name         = document.getString("name");
                    agreementDataSummary.label        = document.getString("label");
                    agreementDataSummary.columnFields = document.getList("columnFields", String.class);
                    agreementDataSummary.columnLabels = document.getList("columnLabels", String.class);

                    list.add(agreementDataSummary);
                }
            }
            finally
            {
                cursor.close();
            }
        }
        catch (Error error)
        {
            log.error("Error while creating agreement data summaries", error);
            return new ArrayList<AgreementDataSummary>();
        }
        catch (Exception exception)
        {
            log.error("Exception while creating agreement data summaries", exception);
            return new ArrayList<AgreementDataSummary>();
        }

        return list;
    }

    @GET
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getAgreementData(@QueryParam("agreement_data_name") String agreementDataName)
    {
        log.infof("############ SDE - AgreementDatasResource::getAgreementData(%s) ############", agreementDataName);

        JsonArray list = new JsonArray();

        try
        {
            MongoCursor<Document> cursor = mongoClient.getDatabase("sde").getCollection("ad_" + agreementDataName).find().iterator();

            try
            {
                if (cursor.hasNext())
                {
                    Document document = cursor.next();

                    JsonObject object = new JsonObject(document.toJson());
                    list = object.getJsonArray("data");
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
