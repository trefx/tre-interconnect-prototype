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

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class ROCrateRequestReceiver
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Blocking
    @Incoming("rr_incoming")
    @Outgoing("rr_outgoing")
    public RoCrate receiveRequest(JsonObject requestObject)
    {
        try
        {
            log.info("############ SDE - ROCrateRequestReceiver::receiveRequest ############");

            RoCrate request = objectMapper.convertValue(requestObject, RoCrate.class);

            return request;
        }
        catch (Error error)
        {
            log.debug("Error while forwarding request RO_Crate", error);
            return null;
        }
        catch (Exception exception)
        {
            log.debug("Exception while forwarding request RO_Crate", exception);
            return null;
        }
    }
}
