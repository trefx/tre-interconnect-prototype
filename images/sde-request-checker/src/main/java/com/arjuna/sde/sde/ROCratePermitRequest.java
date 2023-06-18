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
import io.vertx.core.json.JsonObject;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.minio.MinioClient;
import io.minio.GetObjectArgs;

@Path("/permit_request")
public class ROCratePermitRequest
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Inject
    public MinioClient minioClient;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject postPermitRequest(@QueryParam("requestid") String requestId)
    {
        log.info("############ SDE - ROCratePermitRequest.postPermitRequest ############");

        JsonObject    results      = new JsonObject();
        StringBuilder stringBuffer = new StringBuilder();
        try
        {
//            InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket("unchecked-requests").object(requestId).build());

//            for (int ch; (ch = inputStream.read()) != -1;)
//                stringBuffer.append((char) ch);

//            results = new JsonObject(stringBuffer.toString());
        }
        catch (Error error)
        {
            log.error("Error while obtaining permit request RO_Crate", error);
        }
        catch (Exception exception)
        {
            log.error("Exception while obtaining permit request RO_Crate", exception);
        }

        return results;
    }
}
