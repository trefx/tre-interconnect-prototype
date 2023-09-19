package com.arjuna.sde.dev;

import java.lang.Error;
import java.lang.Exception;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class QueuePullerResource
{
    @Inject
    public Logger log;

    @Blocking
    @Incoming("incoming")
    public void pullMessage(byte[] message)
    {
        log.info("############ Dev - QueuePuller::pullMessage ############");

        try
        {
        }
        catch (Error error)
        {
            log.error("Error while pulling message", error);
        }
        catch (Exception exception)
        {
            log.error("Exception while pulling message", exception);
        }
    }
}
