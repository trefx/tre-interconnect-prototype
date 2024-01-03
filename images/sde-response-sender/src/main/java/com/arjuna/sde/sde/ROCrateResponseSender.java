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

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.reactive.messaging.annotations.Blocking;

import com.arjuna.sde.utils.ROCrateTransformer;

@ApplicationScoped
public class ROCrateResponseSender
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Blocking
    @Incoming("rs_incoming")
    @Outgoing("rs_outgoing")
    public byte[] sendResponse(byte[] response)
    {
        try
        {
            log.info("############ SDE - ROCrateResponseSender::sendResponse ############");

            return response;
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
