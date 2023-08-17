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
public class ROCrateAnalysisAdaptor
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Blocking
    @Incoming("aa_incoming")
    @Outgoing("aa_outgoing")
    public JsonObject doAnalysis(JsonObject requestJson)
    {
        try
        {
            log.info("############ SDE - ROCrateAnalysisAdaptor::doAnalysis ############");

               JsonObject responseJson = requestJson;

//             RoCrate response = objectMapper.convertValue(requestObject, RoCrate.class);

//             RoCrate response = new RoCrate.RoCrateBuilder(request)
//                 .addDataEntity(
//                     new DataEntity.DataEntityBuilder()
//                       .addType("CreativeWork")
//                       .setId("http://example.org/" + UUID.randomUUID().toString())
//                       .addProperty("Answer to the Ultimate Question of Life, The Universe, and Everything", "42")
//                       .build()
//               )
//               .build();

            return responseJson;
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
