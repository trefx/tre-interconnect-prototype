package com.arjuna.sde.sde;

import java.lang.Error;
import java.lang.Exception;
import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;

import edu.kit.datamanager.ro_crate.RoCrate;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import io.vertx.core.json.JsonObject;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class DataSHIELDAnalysisAdapter
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Inject
    @RestClient
    public ArmadilloService armadilloService;

    @Blocking
    @Incoming("daa_incoming")
    @Outgoing("daa_outgoing")
    public JsonObject processDataSHIELDRequest(JsonObject datashieldRequest)
    {
        try
        {
            log.info("############ SDE - DataSHIELDAnalysisAdapter::processDataSHIELDRequest ############");

//            RootDataEntity rootDataEntity = datashieldRequest.getRootDataEntity();

//            String dataSHIELDPlatformName    = rootDataEntity.getProperty("datashield-platform-name").asText();
//            String dataSHIELDProfileName     = rootDataEntity.getProperty("datashield-profile-name").asText();
//            String dataSHIELDSymbolNamesList = rootDataEntity.getProperty("datashield-symbol-names-list").asText();
//            String dataSHIELDTableNamesList  = rootDataEntity.getProperty("datashield-table-names-list").asText();
//            String dataSHIELDWorkspaceName   = rootDataEntity.getProperty("datashield-workspace-name").asText();
//            String dataSHIELDRScript         = rootDataEntity.getProperty("datashield-r-script").asText();

            String dataSHIELDPlatformName    = datashieldRequest.getString("datashield-platform-name");
            String dataSHIELDProfileName     = datashieldRequest.getString("datashield-profile-name");
            String dataSHIELDSymbolNamesList = datashieldRequest.getString("datashield-symbol-names-list");
            String dataSHIELDTableNamesList  = datashieldRequest.getString("datashield-table-names-list");
            String dataSHIELDWorkspaceName   = datashieldRequest.getString("datashield-workspace-name");
            String dataSHIELDRScript         = datashieldRequest.getString("datashield-r-script");

            log.infof("DataSHIELD Platform Name     %s", dataSHIELDPlatformName);
            log.infof("DataSHIELD Profile Name      %s", dataSHIELDProfileName);
            log.infof("DataSHIELD Symbol Names List %s", dataSHIELDSymbolNamesList);
            log.infof("DataSHIELD Table Names List  %s", dataSHIELDTableNamesList);
            log.infof("DataSHIELD Workspace Name    %s", dataSHIELDWorkspaceName);
            log.infof("DataSHIELD R Script          %s", dataSHIELDRScript);

            // Load Workspace
            armadilloService.postSelectProfile(dataSHIELDProfileName, false);
            armadilloService.postLoadTable(dataSHIELDSymbolNamesList, dataSHIELDTableNamesList, false);

            String responce     = armadilloService.postExecute(dataSHIELDRScript, false);
            String errorMessage = "";
            // Save Workspace

            log.infof("Responce:      %s\n", responce);
            log.infof("Error message: %s\n", errorMessage);

            JsonObject datashieldResponse = new JsonObject("{ \"outcome\", \"success\" }");

            return datashieldResponse;
        }
        catch(Error error)
        {
            String errorMessage = "Error processing of DataSHIELD request";
            log.warn(errorMessage, error);
            return new JsonObject("{ \"outcome\", \"error\" }");
        }
        catch(Exception exception)
        {
            String errorMessage = "Exception processing of DataSHIELD request";
            log.warn(errorMessage, exception);
            return new JsonObject("{ \"outcome\", \"exception\" }");
        }
    }
}
