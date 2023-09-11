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
import com.mongodb.BasicDBObject;
import org.bson.Document;

class AgreementDataSummary
{
    public String       name;
    public String       label;

    public AgreementDataSummary()
    {
    }

    public AgreementDataSummary(String name, String label)
    {
       this.name  = name;
       this.label = label;
    }
}

class AgreementData
{
    public List<String> columnFields;
    public List<String> columnLabels;
    public JsonArray    data;

    public AgreementData()
    {
    }

    public AgreementData(List<String> columnFields, List<String> columnLabels, JsonArray data)
    {
       this.columnFields = columnFields;
       this.columnLabels = columnLabels;
       this.data         = data;
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

        List<AgreementDataSummary> agreementDataSummarys = new ArrayList<AgreementDataSummary>();

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

                    agreementDataSummarys.add(agreementDataSummary);
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

        return agreementDataSummarys;
    }

    @GET
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    public AgreementData getAgreementData(@QueryParam("agreement_data_name") String agreementDataName)
    {
        log.infof("############ SDE - AgreementDatasResource::getAgreementData(%s) ############", agreementDataName);

        AgreementData agreementData = new AgreementData();

        try
        {
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("name", agreementDataName);
            MongoCursor<Document> cursorMetadata = mongoClient.getDatabase("sde").getCollection("agreementdatas_infos").find(whereQuery).iterator();

            try
            {
                if (cursorMetadata.hasNext())
                {
                    Document document = cursorMetadata.next();

                    agreementData.columnFields = document.getList("columnFields", String.class);
                    agreementData.columnLabels = document.getList("columnLabels", String.class);
                }
            }
            finally
            {
                cursorMetadata.close();
            }

            MongoCursor<Document> cursorData = mongoClient.getDatabase("sde").getCollection("ad_" + agreementDataName).find().iterator();

            try
            {
                if (cursorData.hasNext())
                {
                    Document document = cursorData.next();

                    JsonObject jsonObject = new JsonObject(document.toJson());

                    log.info("======");
                    log.info(jsonObject);
                    log.info("------");
                    log.info(jsonObject.getJsonArray("data"));
                    log.info("------");
                    log.info(jsonObject.getJsonArray("list"));
                    log.info("======");

                    agreementData.data = jsonObject.getJsonArray("data");
                }
            }
            finally
            {
                cursorData.close();
            }
        }
        catch (Error error)
        {
            log.error("Error while obaining data", error);
            return new AgreementData();
        }
        catch (Exception exception)
        {
            log.error("Exception while obtaining data", exception);
            return new AgreementData();
        }

        return agreementData;
    }
}
