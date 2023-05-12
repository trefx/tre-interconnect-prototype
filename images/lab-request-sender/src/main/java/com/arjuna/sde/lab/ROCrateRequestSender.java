package com.arjuna.sde.lab;

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
public class ROCrateRequestSender
{
    @Inject
    Logger log;

    @Blocking
//    @Incoming("incoming")
//    @Outgoing("outgoing")
    @Incoming("outgoing_requests")
    @Outgoing("incoming_responses")
    public RoCrate forwardRequest(JsonObject requestObject)
    {
        log.info("############ ROCrateRequestSender::forwardRequest  ############");

        try
        {
            ObjectMapper objectMapper = new ObjectMapper();

            log.info("########################");
            log.infof("Class: %s\n", requestObject.getClass().getName());
            log.info("########################");

            RoCrate roCrate = new RoCrate.RoCrateBuilder("Request", UUID.randomUUID().toString())
                .build();

            return roCrate;
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
}
