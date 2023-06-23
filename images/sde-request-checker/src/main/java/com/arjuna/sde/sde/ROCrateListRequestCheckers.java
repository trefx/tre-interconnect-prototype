package com.arjuna.sde.sde;

import java.lang.Error;
import java.lang.Exception;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
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

    @All
    @Inject
    public List<RequestChecker> requestCheckers;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getListRequestCheckers()
    {
        log.info("############ SDE - ROCrateListRequestCheckers.getListRequestCheckers ############");

        JsonObject results = new JsonObject();
        try
        {
            JsonArray checkers = new JsonArray();
            for (RequestChecker requestChecker : requestCheckers)
            {
                checkers.add(
                    new JsonObject()
                        .put("name", requestChecker.getName())
                        .put("description", requestChecker.getDescription())
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
}
