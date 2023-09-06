package com.arjuna.sde.sde;

import java.util.UUID;
import java.lang.Error;
import java.lang.Exception;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
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

    @Channel("ad_outgoing")
    public Emitter<JsonObject> responseEmitter;

    @Channel("dsa_outgoing")
    public Emitter<JsonObject> analysisRequestEmitter;

    @Blocking
    @Incoming("ad_incoming")
    public void dispatchAnalysis(JsonObject requestJson)
    {
        try
        {
            log.info("############ SDE - ROCrateAnalysisDispatcher::dispatchAnalysis ############");

            if (requestJson.containsKey("allContextualEntities") && (requestJson.getJsonArray("allContextualEntities") != null))
            {
                String requestType = null;
                JsonArray requestContextualEntities = requestJson.getJsonArray("allContextualEntities");
                for (int index = 0; index < requestContextualEntities.size(); index++)
                {
                    JsonObject entity = requestContextualEntities.getJsonObject(index);
                    if (entity.getString("@type").equals("FederatedAnalysis") && entity.getString("request-type").equals("DataSHIELDAnalysis"))
                        requestType = entity.getString("request-type");
                }

                if ("DataSHIELDAnalysis".equals(requestType))
                    analysisRequestEmitter.send(requestJson);
                else
                {
                    unknownRequestTypeRequested(requestJson);

                    responseEmitter.send(requestJson);
                }
            }
            else
            {
                requestJson.put("allContextualEntities", new JsonArray());

                noRequestTypeRequested(requestJson);

                responseEmitter.send(requestJson);
            }
        }
        catch (Error error)
        {
            log.error("Error while dispatching request RO_Crate", error);
        }
        catch (Exception exception)
        {
            log.error("Exception while dispatching request RO_Crate", exception);
        }
    }

    private void noRequestTypeRequested(JsonObject requestJson)
    {
        JsonObject responseErrorEntity = new JsonObject();
        responseErrorEntity.put("@type", "RequestError");
        responseErrorEntity.put("@id", "http://example.org/" + UUID.randomUUID().toString());
        responseErrorEntity.put("message", "No Request Type");

        JsonArray allContextualEntities = requestJson.getJsonArray("allContextualEntities");
        allContextualEntities.add(responseErrorEntity);
    }

    private void unknownRequestTypeRequested(JsonObject requestJson)
    {
        JsonObject requestErrorEntity = new JsonObject();
        requestErrorEntity.put("@type", "RequestError");
        requestErrorEntity.put("@id", "http://example.org/" + UUID.randomUUID().toString());
        requestErrorEntity.put("message", "Unknown Request Type");

        JsonArray allContextualEntities = requestJson.getJsonArray("allContextualEntities");
        allContextualEntities.add(requestErrorEntity);
    }

    @Blocking
    @Incoming("dsa_incoming")
    public void analysisResponseProcessor(JsonObject responseJson)
    {
        log.info("############ SDE - ROCrateAnalysisDispatcher::analysisResponseProcessor ############");

        responseEmitter.send(responseJson);
    }
}
