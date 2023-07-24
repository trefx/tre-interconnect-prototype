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
import io.vertx.core.json.JsonObject;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.entities.contextual.ContextualEntity;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import io.minio.MinioClient;

@Path("/templated_request_submitter")
public class ROCrateTemplatedRequestSubmitterResource
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Channel("trs_outgoing")
    public Emitter<JsonObject> requestEmitter;

    @Inject
    public MongoClient mongoClient;

    @Inject
    public MinioClient minioClient;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postSubmitTemplatedRequest(JsonObject templatedRequest)
    {
        log.info("############ Lab - ROCrateTemplatedRequestSubmitterResource::postSubmitTemplatedRequest ############");

        try
        {
             String templateId = templatedRequest.getString("templateID");

             RoCrate request = new RoCrate.RoCrateBuilder()
                .addContextualEntity(
                    new ContextualEntity.ContextualEntityBuilder()
                        .addType("TemplatedRequest")
                        .setId("http://example.org/" + UUID.randomUUID().toString())
                        .addProperty("template-id", templateId)
                        .build()
                )
                .build();

            JsonObject requestJson = new JsonObject(objectMapper.writeValueAsString(request));

            requestEmitter.send(requestJson);

            return "{ \"outcome\": \"success\" }";
        }
        catch (Error error)
        {
            log.error("Error while creating request RO_Crate", error);
            return "{ \"outcome\": \"error\", \"message\": \"" + error.getMessage() + "\" }";
        }
        catch (Exception exception)
        {
            log.error("Exception while creating request RO_Crate", exception);
            return "{ \"outcome\": \"exception\", \"message\": \"" + exception.getMessage() + "\" }";
        }
    }
}
