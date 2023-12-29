package com.arjuna.sde.sde;

import java.lang.Error;
import java.lang.Exception;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import java.io.InputStream;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import io.vertx.core.json.JsonObject;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.minio.MinioClient;
import io.minio.GetObjectArgs;
import io.minio.RemoveObjectArgs;

@Path("/block_response")
public class ROCrateBlockResponse
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Channel("br_outgoing")
    public Emitter<byte[]> responseEmitter;

    @Inject
    public MinioClient minioClient;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postBlockResponse(@QueryParam("responseid") String responseId)
    {
        log.info("############ SDE - ROCrateBlockResponse.postBlockResponse ############");

        try
        {
            StringBuilder stringBuffer = new StringBuilder();
            InputStream   inputStream  = minioClient.getObject(GetObjectArgs.builder().bucket("unchecked-responses").object(responseId).build());
            for (int ch; (ch = inputStream.read()) != -1;)
                stringBuffer.append((char) ch);
            inputStream.close();

            JsonObject responseJson = new JsonObject(stringBuffer.toString());

            responseEmitter.send(responseJson);

            minioClient.removeObject(RemoveObjectArgs.builder().bucket("unchecked-responses").object(responseId).build());
        }
        catch (Error error)
        {
            log.error("Error while blocking response RO_Crate", error);
            return "{\"outcome\": \"Error while blocking response RO_Crate\"}";
        }
        catch (Exception exception)
        {
            log.error("Exception while blocking response RO_Crate", exception);
            return "{\"outcome\": \"Exception while blocking response RO_Crate\"}";
        }

        return "{\"outcome\": \"Done\"}";
    }
}
