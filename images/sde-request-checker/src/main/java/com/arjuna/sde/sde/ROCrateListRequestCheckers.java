package com.arjuna.sde.sde;

import java.lang.Error;
import java.lang.Exception;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import io.quarkus.arc.All;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

@Path("/list_request_checkers")
public class ROCrateListRequestCheckers
{
    @Inject
    public Logger log;

    @Inject
    public ROCrateRequestChecker roCrateRequestChecker;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getListRequestCheckers()
    {
        log.info("############ SDE - ROCrateListRequestCheckers.getListRequestCheckers ############");

        JsonObject results = new JsonObject();
        try
        {
            JsonArray checkers = new JsonArray();
            for (RequestChecker requestChecker: roCrateRequestChecker.requestCheckers)
            {
                checkers.add(
                    new JsonObject()
                        .put("name", requestChecker.getName())
                        .put("description", requestChecker.getDescription())
                        .put("enabled", requestChecker.getEnabled())
                        .put("immutable", requestChecker.getImmutable())
                        .put("className", requestChecker.getClass().getName())
                );
            }
            results.put("checkers", checkers);
            results.put("outcome", "Done");

            return results;
        }
        catch (Error error)
        {
            log.error("Error while blocking request RO_Crate", error);
            return new JsonObject().put("outcome", "Error while blocking request RO_Crate");
        }
        catch (Exception exception)
        {
            log.error("Exception while blocking request RO_Crate", exception);
            return new JsonObject().put("outcome", "Exception while blocking request RO_Crate");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject postListRequestCheckers(JsonObject parameters)
    {
        log.info("############ SDE - ROCrateListRequestCheckers.postListRequestCheckers ############");

        JsonObject results = new JsonObject();
        try
        {
            JsonArray requestCheckers = parameters.getJsonArray("checkers");
            for (Object requestCheckerObject: requestCheckers.getList())
            {
                JsonObject requestChecker = (JsonObject) requestCheckerObject;

                String  className = requestChecker.getString("className");
                Boolean enabled   = requestChecker.getBoolean("enabled");

                enabledMap.put(className, enabled);
            }

            results.put("outcome", "Done");

            return results;
        }
        catch (Error error)
        {
            log.error("Error while blocking request RO_Crate", error);
            return new JsonObject().put("outcome", "Error while blocking request RO_Crate");
        }
        catch (Exception exception)
        {
            log.error("Exception while blocking request RO_Crate", exception);
            return new JsonObject().put("outcome", "Exception while blocking request RO_Crate");
        }
    }
}
