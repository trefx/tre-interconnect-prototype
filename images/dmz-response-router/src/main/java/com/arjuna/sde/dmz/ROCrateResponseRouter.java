package com.arjuna.sde.dmz;

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

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class ROCrateResponseRouter
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Blocking
    @Incoming("rr_incoming")
    @Outgoing("rr_outgoing")
    public RoCrate routeResponse(JsonObject responseObject)
    {
        try
        {
            log.info("############ DMZ - ROCrateResponseRouter::routeResponse ############");

            RoCrate response = objectMapper.convertValue(responseObject, RoCrate.class);

            return response;
        }
        catch (Error error)
        {
            log.error("Error while routing response RO_Crate", error);
            return null;
        }
        catch (Exception exception)
        {
            log.error("Exception while routing response RO_Crate", exception);
            return null;
        }
    }
}
