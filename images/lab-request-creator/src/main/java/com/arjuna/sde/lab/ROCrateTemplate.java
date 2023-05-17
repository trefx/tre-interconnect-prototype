package com.arjuna.sde.lab;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.lang.Error;
import java.lang.Exception;

import jakarta.inject.Inject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import io.minio.MinioClient;

class TemplateSummary
{
    public String id;
    public String name;
    public String summary;
    public String description;

    public TemplateSummary()
    {
    }

    public TemplateSummary(String id, String name, String summary, String description)
    {
        this.id          = id;
        this.name        = name;
        this.summary     = summary;
        this.description = description;
    }
}

@Path("/template")
public class ROCrateTemplate
{
    @Inject
    Logger log;

    @Inject
    public MongoClient mongoClient;

    @Inject
    public MinioClient minioClient;

    @GET
    @Path("/summaries")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TemplateSummary> getTemplateSummaries()
    {
        List<TemplateSummary> list = new ArrayList<TemplateSummary>();

        try
        {
            MongoCursor<Document> cursor = mongoClient.getDatabase("tre").getCollection("templates").find().iterator();

            try
            {
                while (cursor.hasNext())
                {
                    Document document = cursor.next();

                    TemplateSummary templateSummary = new TemplateSummary();
                    templateSummary.id          = document.getString("id");
                    templateSummary.name        = document.getString("name");
                    templateSummary.summary     = document.getString("summary");
                    templateSummary.description = document.getString("description");

                    list.add(templateSummary);
                }
            }
            finally
            {
                cursor.close();
            }
        }
        catch (Error error)
        {
            log.error("Error while creating request RO_Crate", error);
            return new ArrayList<TemplateSummary>();
        }
        catch (Exception exception)
        {
            log.error("Exception while creating request RO_Crate", exception);
            return new ArrayList<TemplateSummary>();
        }

        return list;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TemplateSummary> getTemplate(@QueryParam("template_id") String templateId)
    {
        List<TemplateSummary> list = new ArrayList<TemplateSummary>();

        try
        {
            MongoCursor<Document> cursor = mongoClient.getDatabase("tre").getCollection("templates").find().iterator();

            try
            {
                while (cursor.hasNext())
                {
                }
            }
            finally
            {
                cursor.close();
            }
        }
        catch (Error error)
        {
            log.error("Error while creating request RO_Crate", error);
            return new ArrayList<TemplateSummary>();
        }
        catch (Exception exception)
        {
            log.error("Exception while creating request RO_Crate", exception);
            return new ArrayList<TemplateSummary>();
        }

        return list;
    }
}

