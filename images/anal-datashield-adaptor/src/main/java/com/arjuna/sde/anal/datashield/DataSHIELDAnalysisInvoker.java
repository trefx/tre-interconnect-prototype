package com.arjuna.sde.anal.datashield;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class DataSHIELDAnalysisInvoker
{
    @Inject
    public Logger log;

//    @Inject
//    @RestClient
//    public ArmadilloService armadilloService;

    @Blocking
    @Incoming("dsa_incoming")
    @Outgoing("dsa_outgoing")
    public JsonObject processRequest(JsonObject responseObject)
        throws Exception
    {
        try
        {
            log.info("############ Anal - DataSHIELDAnalysisInvoker::processRequest ############");

            return responseObject;
        }
        catch(Error error)
        {
            log.error("Error performing of DataSHIELD Analysis invocation", error);
        }
        catch(Exception exception)
        {
            log.error("Exception performing of DataSHIELD Analysis invocation", exception);
        }

        return null;
    }
}
