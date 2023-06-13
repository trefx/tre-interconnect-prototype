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
import com.fasterxml.jackson.databind.JsonNode;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.ListObjectsArgs;
import io.minio.messages.Item;

import io.smallrye.reactive.messaging.annotations.Blocking;

@Path("/unchecked_requests")
public class ROCrateUncheckedRequests
{
    @Inject
    public Logger log;

    @Inject
    public MinioClient minioClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getUncheckedRequestIds()
    {
        log.info("############ SDE - ROCrateUncheckedRequests.getUncheckedRequestIds ############");

        List<String> results = new ArrayList();
        try
        {
            Iterable<Result<Item>> requestInfos = minioClient.listObjects(ListObjectsArgs.builder().bucket("unchecked_requests").build());
            requestInfos.forEach((result) -> { try { results.add(result.get().objectName()); } catch (Throwable throwable) { log.error("Error while ..."); } } );
        }
        catch (Error error)
        {
            log.error("Error while obtaining unchecked request RO_Crates", error);
            results.clear();
        }
        catch (Exception exception)
        {
            log.error("Exception while obtaining unchecked request RO_Crates", exception);
            results.clear();
        }

        return results;
    }
}
