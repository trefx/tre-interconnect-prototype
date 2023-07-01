package com.arjuna.sde.lab;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.lang.Error;
import java.lang.Exception;

import jakarta.inject.Inject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

class AnalysisProviderSummary
{
    public String id;
    public String name;
    public String summary;
    public String description;

    public AnalysisProviderSummary()
    {
    }

    public AnalysisProviderSummary(String id, String name, String summary, String description)
    {
        this.id          = id;
        this.name        = name;
        this.summary     = summary;
        this.description = description;
    }
}

@Path("/providers")
public class AnalysisProviderResource
{
    @Inject
    public Logger log;

    @Inject
    public MongoClient mongoClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AnalysisProviderSummary> getAnalysisProviderSummaries()
    {
        log.info("############ Lab - AnalysisProviderResource::getAnalysisProviderSummaries ############");

        List<AnalysisProviderSummary> list = new ArrayList<AnalysisProviderSummary>();

        try
        {
            MongoCursor<Document> cursor = mongoClient.getDatabase("sde").getCollection("provider_infos").find().iterator();

            try
            {
                while (cursor.hasNext())
                {
                    Document document = cursor.next();

                    AnalysisProviderSummary analysisProviderSummary = new AnalysisProviderSummary();
                    analysisProviderSummary.id          = document.getString("id");
                    analysisProviderSummary.name        = document.getString("name");
                    analysisProviderSummary.summary     = document.getString("summary");
                    analysisProviderSummary.description = document.getString("description");

                    list.add(analysisProviderSummary);
                }
            }
            finally
            {
                cursor.close();
            }
        }
        catch (Error error)
        {
            log.error("Error while creating analysis provider summaries", error);
            return new ArrayList<AnalysisProviderSummary>();
        }
        catch (Exception exception)
        {
            log.error("Exception while creating snalysis provider summaries", exception);
            return new ArrayList<AnalysisProviderSummary>();
        }

        return list;
    }
}
