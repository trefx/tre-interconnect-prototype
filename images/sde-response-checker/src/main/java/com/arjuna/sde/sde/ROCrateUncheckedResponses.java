package com.arjuna.sde.sde;

import java.lang.Error;
import java.lang.Exception;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
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
import io.minio.Result;
import io.minio.ListObjectsArgs;
import io.minio.messages.Item;
import io.minio.errors.ErrorResponseException;

@Path("/unchecked_responses")
public class ROCrateUncheckedResponses
{
    @Inject
    public Logger log;

    @Inject
    public MinioClient minioClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getUncheckedResponseIds()
    {
        log.info("############ SDE - ROCrateUncheckedResponses.getUncheckedResponseIds ############");

        List<String> results = new ArrayList();
        try
        {
            Iterable<Result<Item>> responseInfos = minioClient.listObjects(ListObjectsArgs.builder().bucket("unchecked-responses").build());
            responseInfos.forEach((result) -> { try { results.add(result.get().objectName()); } catch (ErrorResponseException errorResponseException) { } catch (Throwable throwable) { log.error("Error while ...", throwable); } } );
        }
        catch (Error error)
        {
            log.error("Error while obtaining unchecked response RO_Crates", error);
            results.clear();
        }
        catch (Exception exception)
        {
            log.error("Exception while obtaining unchecked response RO_Crates", exception);
            results.clear();
        }

        return results;
    }
}
