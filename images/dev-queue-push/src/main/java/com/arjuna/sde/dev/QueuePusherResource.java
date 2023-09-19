package com.arjuna.sde.dev;

import java.lang.Error;
import java.lang.Exception;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import io.smallrye.reactive.messaging.annotations.Blocking;

@Path("/")
public class QueuePusherResource
{
    @Inject
    public Logger log;

    @Channel("outgoing")
    public Emitter<byte[]> messageEmitter;

    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public void pushMessage(byte[] message)
    {
        log.info("############ Dev - QueuePusher::pushMessage ############");

        try
        {
            messageEmitter.send(message);
        }
        catch (Error error)
        {
            log.error("Error while pushing message", error);
        }
        catch (Exception exception)
        {
            log.error("Exception while pushing message", exception);
        }
    }
}
