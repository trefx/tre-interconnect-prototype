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

import edu.kit.datamanager.ro_crate.RoCrate;

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
    public byte[] routeResponse(byte[] request)
    {
        try
        {
            log.info("############ DMZ - ROCrateResponseRouter::routeResponse ############");

            byte[] response = request;

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
