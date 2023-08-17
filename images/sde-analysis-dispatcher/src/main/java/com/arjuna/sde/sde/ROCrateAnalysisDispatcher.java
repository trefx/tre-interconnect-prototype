package com.arjuna.sde.sde;

import java.util.UUID;
import java.lang.Error;
import java.lang.Exception;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class ROCrateAnalysisDispatcher
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Blocking
    @Incoming("aa_incoming")
    @Outgoing("aa_outgoing")
    public JsonObject dispatchAnalysis(JsonObject requestJson)
    {
        try
        {
            log.info("############ SDE - ROCrateAnalysisDispatcher::dispatchAnalysis ############");

            if (requestJson.containsKey("allContextualEntities") && (requestJson.getJsonArray("allContextualEntities") != null))
            {
                JsonArray allContextualEntities = requestJson.getJsonArray("allContextualEntities");

                JsonObject requestErrorEntity = new JsonObject();
                requestErrorEntity.put("@type", "Response");
                requestErrorEntity.put("@id", "http://example.org/" + UUID.randomUUID().toString());
                requestErrorEntity.put("message", "The answer to life, the universe, and everything is 42");

                allContextualEntities.add(requestErrorEntity);

                return requestJson;
            }
            else
                return unknownRequestTypeRequested(requestJson);
        }
        catch (Error error)
        {
            log.error("Error while forwarding request RO_Crate", error);
            return null;
        }
        catch (Exception exception)
        {
            log.error("Exception while forwarding request RO_Crate", exception);
            return null;
        }
    }

    private JsonObject unknownRequestTypeRequested(JsonObject requestJson)
    {
        JsonArray allContextualEntities = null;
        if ((! requestJson.containsKey("allContextualEntities")) || (requestJson.getJsonArray("allContextualEntities") == null))
        {
            allContextualEntities = new JsonArray();
            requestJson.put("allContextualEntities", allContextualEntities);
        }
        else
            allContextualEntities = requestJson.getJsonArray("allContextualEntities");

        JsonObject requestErrorEntity = new JsonObject();
        requestErrorEntity.put("@type", "RequestError");
        requestErrorEntity.put("@id", "http://example.org/" + UUID.randomUUID().toString());
        requestErrorEntity.put("message", "Unknown Request Type");

        allContextualEntities.add(requestErrorEntity);

        return requestJson;
    }
}
