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
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import io.vertx.core.json.JsonObject;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import io.minio.MinioClient;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.PutObjectArgs;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class ROCrateRequestChecker
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Channel("rc_outgoing")
    public Emitter<RoCrate> requestEmitter;

    @Inject
    public MinioClient minioClient;

    @Blocking
    @Incoming("rc_incoming")
    public void checkRequest(JsonObject requestObject)
    {
        try
        {
            log.info("############ SDE - ROCrateRequestChecker::checkRequest ############");

            RoCrate request = objectMapper.convertValue(requestObject, RoCrate.class);

            requestEmitter.send(request);
        }
        catch (Error error)
        {
            log.debug("Error while forwarding request RO_Crate", error);
        }
        catch (Exception exception)
        {
            log.debug("Exception while forwarding request RO_Crate", exception);
        }
    }
}
