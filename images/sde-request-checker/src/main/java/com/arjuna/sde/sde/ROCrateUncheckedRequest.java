package com.arjuna.sde.sde;

import java.lang.Error;
import java.lang.Exception;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
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

@Path("/unchecked_request")
public class ROCrateUncheckedRequest
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Inject
    public MinioClient minioClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] getUncheckedRequest(@QueryParam("requestid") String requestId)
    {
        log.info("############ SDE - ROCrateUncheckedRequest.getUncheckedRequest ############");

        byte[]                results                = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try
        {
            InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket("unchecked-requests").object(requestId).build());
            for (int ch; (ch = inputStream.read()) != -1;)
                byteArrayOutputStream.write(ch);
            byteArrayOutputStream.close();
            inputStream.close();

            results = byteArrayOutputStream.toByteArray();
        }
        catch (Error error)
        {
            log.error("Error while obtaining unchecked request RO_Crate", error);
        }
        catch (Exception exception)
        {
            log.error("Exception while obtaining unchecked request RO_Crate", exception);
        }

        return results;
    }
}
