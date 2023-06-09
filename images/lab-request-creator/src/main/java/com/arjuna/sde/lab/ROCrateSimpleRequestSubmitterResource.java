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

@Path("/simple_request_submitter")
public class ROCrateSimpleRequestSubmitterResource
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Channel("trs_outgoing")
    public Emitter<RoCrate> requestEmitter;

    @Inject
    public MongoClient mongoClient;

    @Inject
    public MinioClient minioClient;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postSubmitSimpleRequest(Request request)
    {
        log.info("############ Lab - ROCrateSimpleRequestSubmitterResource::postSubmitSampleRequest ############");

        try
        {
            RootDataEntity rootDataEntity = new RootDataEntity.RootDataEntityBuilder()
                .addProperty("template-id", request.templateID)
                .build();

            RoCrate roCrate = new RoCrate.RoCrateBuilder("Request", UUID.randomUUID().toString())
                .build();

            roCrate.setRootDataEntity(rootDataEntity);

            requestEmitter.send(roCrate);

            return "{ \"outcome\": \"success\" }";
        }
        catch (Error error)
        {
            log.error("Error while creating simple request RO_Crate", error);
            return "{ \"outcome\": \"error\", \"message\": \"" + error.getMessage() + "\" }";
        }
        catch (Exception exception)
        {
            log.error("Exception while creating simple request RO_Crate", exception);
            return "{ \"outcome\": \"exception\", \"message\": \"" + exception.getMessage() + "\" }";
        }
    }
}
