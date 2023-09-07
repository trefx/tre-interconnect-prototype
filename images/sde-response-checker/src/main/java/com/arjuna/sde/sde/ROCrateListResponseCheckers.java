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

@Path("/list_response_checkers")
public class ROCrateListResponseCheckers
{
    @Inject
    public Logger log;

    @All
    @Inject
    public List<ResponseChecker> responseCheckers;

    private Map<String, Boolean> enabledMap = new HashMap<String, Boolean>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getListResponseCheckers()
    {
        log.info("############ SDE - ROCrateListResponseCheckers.getListResponseCheckers ############");

        JsonObject results = new JsonObject();
        try
        {
            JsonArray checkers = new JsonArray();
            for (ResponseChecker responseChecker : responseCheckers)
            {
                String  className = responseChecker.getClass().getName();
                Boolean enabled   = Boolean.TRUE;
                if (enabledMap.containsKey(className))
                    enabled = enabledMap.get(className);

                checkers.add(
                    new JsonObject()
                        .put("name", responseChecker.getName())
                        .put("description", responseChecker.getDescription())
                        .put("enabled", enabled)
                        .put("immutable", true)
                        .put("className", className)
                );
            }
            results.put("checkers", checkers);
            results.put("outcome", "Done");

            return results;
        }
        catch (Error error)
        {
            log.error("Error while blocking response RO_Crate", error);
            return new JsonObject().put("outcome", "Error while blocking response RO_Crate");
        }
        catch (Exception exception)
        {
            log.error("Exception while blocking response RO_Crate", exception);
            return new JsonObject().put("outcome", "Exception while blocking response RO_Crate");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject postListResponseCheckers(JsonObject parameters)
    {
        log.info("############ SDE - ROCrateListResponseCheckers.postListResponseCheckers ############");

        JsonObject results = new JsonObject();
        try
        {
            JsonArray responseCheckers = parameters.getJsonArray("checkers");
            for (Object responseCheckerObject: responseCheckers.getList())
            {
                JsonObject responseChecker = (JsonObject) responseCheckerObject;

                String  className = responseChecker.getString("className");
                Boolean enabled   = responseChecker.getBoolean("enabled");

                enabledMap.put(className, enabled);
            }

            results.put("outcome", "Done");

            return results;
        }
        catch (Error error)
        {
            log.error("Error while blocking response RO_Crate", error);
            return new JsonObject().put("outcome", "Error while blocking response RO_Crate");
        }
        catch (Exception exception)
        {
            log.error("Exception while blocking response RO_Crate", exception);
            return new JsonObject().put("outcome", "Exception while blocking response RO_Crate");
        }
    }
}
