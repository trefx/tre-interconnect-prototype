package com.arjuna.sde.lab;

import java.util.UUID;
import java.lang.Error;
import java.lang.Exception;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import io.vertx.core.json.JsonObject;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class ROCrateResponseProcessor
{
    @Inject
    Logger log;

    @Blocking
//    @Incoming("incoming")
    @Incoming("incoming_responses")
    public void processResponse(JsonObject responseObject)
    {
        log.info("@@@@@@@@@@@@ ROCrateResponseProcessor.responseObject @@@@@@@@@@@@");

        try
        {
            ObjectMapper objectMapper = new ObjectMapper();

            log.info("@@@@@@@@@@@@@@@@@@@@@@@@");
            log.infof("Class: %s\n", responseObject.getClass().getName());
            log.info("@@@@@@@@@@@@@@@@@@@@@@@@");
        }
        catch (Error error)
        {
            log.error("Error while process response RO_Crate", error);
        }
        catch (Exception exception)
        {
            log.error("Exception while process response RO_Crate", exception);
        }
    }
}
