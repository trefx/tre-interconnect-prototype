package com.arjuna.sde.lab;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.lang.Error;
import java.lang.Exception;

import java.io.InputStream;

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
import io.vertx.core.json.JsonObject;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import io.minio.MinioClient;
import io.minio.GetObjectArgs;

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

class TemplateField
{
    public String id;
    public String name;
    public String path;

    public TemplateField()
    {
    }

    public TemplateField(String id, String name, String path)
    {
        this.id   = id;
        this.name = name;
        this.path = path;
    }
}

@Path("/template")
public class ROCrateTemplateResource
{
    @Inject
    public Logger log;

    @Inject
    public MongoClient mongoClient;

    @Inject
    public MinioClient minioClient;

    @GET
    @Path("/summaries")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TemplateSummary> getTemplateSummaries()
    {
        log.info("############ Lab - ROCrateTemplateResource::getTemplateSummaries ############");

        List<TemplateSummary> list = new ArrayList<TemplateSummary>();

        try
        {
            MongoCursor<Document> cursor = mongoClient.getDatabase("sde").getCollection("template_infos").find().iterator();

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
    @Path("/fields")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TemplateField> getTemplateFields(@QueryParam("template_id") String templateId)
    {
        log.info("############ Lab - ROCrateTemplateResource::getTemplateFields ############");

        List<TemplateField> list = new ArrayList<TemplateField>();

        try
        {
            MongoCursor<Document> cursor = mongoClient.getDatabase("sde").getCollection("templates").find().iterator();

            try
            {
                while (cursor.hasNext())
                {
                    Document document = cursor.next();

                    TemplateField templateField = new TemplateField();
                    templateField.id            = document.getString("id");
                    templateField.name          = document.getString("name");
                    templateField.path          = document.getString("path");

                    list.add(templateField);
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
            return new ArrayList<TemplateField>();
        }
        catch (Exception exception)
        {
            log.error("Exception while creating request RO_Crate", exception);
            return new ArrayList<TemplateField>();
        }

        return list;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getTemplate(@QueryParam("template_id") String templateId)
    {
        log.info("############ Lab - ROCrateResponse.getTemplate ############");

        JsonObject    results      = new JsonObject();
        StringBuilder stringBuffer = new StringBuilder();
        try
        {
            InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket("templates").object(templateId).build());

            for (int ch; (ch = inputStream.read()) != -1;)
                stringBuffer.append((char) ch);

            results = new JsonObject(stringBuffer.toString());
        }
        catch (Error error)
        {
            log.error("Error while obtaining template RO_Crate", error);
        }
        catch (Exception exception)
        {
            log.error("Exception while obtaining template RO_Crate", exception);
        }

        return results;
    }
}
