package com.arjuna.sde.sde;

import java.lang.Error;
import java.lang.Exception;
import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

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
//    public RoCrate processDataSHIELDRequest(RoCrate datashieldRequest)
    public RoCrate processDataSHIELDRequest(JsonObject datashieldRequest)
    {
        String responce     = null;
        String errorMessage = null;

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

            JsonObject rootDataEntity = datashieldRequest.getJsonObject("rootDataEntity");

            String dataSHIELDPlatformName    = rootDataEntity.getString("datashield-platform-name");
            String dataSHIELDProfileName     = rootDataEntity.getString("datashield-profile-name");
            String dataSHIELDSymbolNamesList = rootDataEntity.getString("datashield-symbol-names-list");
            String dataSHIELDTableNamesList  = rootDataEntity.getString("datashield-table-names-list");
            String dataSHIELDWorkspaceName   = rootDataEntity.getString("datashield-workspace-name");
            String dataSHIELDRScript         = rootDataEntity.getString("datashield-r-script");

            log.infof("DataSHIELD Platform Name     %s", dataSHIELDPlatformName);
            log.infof("DataSHIELD Profile Name      %s", dataSHIELDProfileName);
            log.infof("DataSHIELD Symbol Names List %s", dataSHIELDSymbolNamesList);
            log.infof("DataSHIELD Table Names List  %s", dataSHIELDTableNamesList);
            log.infof("DataSHIELD Workspace Name    %s", dataSHIELDWorkspaceName);
            log.infof("DataSHIELD R Script          %s", dataSHIELDRScript);

            // Load Workspace
            armadilloService.postSelectProfile(dataSHIELDProfileName, false);
            armadilloService.postLoadTable(dataSHIELDSymbolNamesList, dataSHIELDTableNamesList, false);

            responce = armadilloService.postExecute(dataSHIELDRScript, false);
            // Save Workspace
        }
        catch(Error error)
        {
            errorMessage = "Error processing of DataSHIELD request";
            log.warn(errorMessage, error);
        }
        catch(Exception exception)
        {
            errorMessage = "Exception processing of DataSHIELD request";
            log.warn(errorMessage, exception);
        }

        log.infof("Responce:      %s\n", responce);
        log.infof("Error message: %s\n", errorMessage);

        RoCrate datashieldResponse = new RoCrate.RoCrateBuilder("Request", UUID.randomUUID().toString()).build();

        return datashieldResponse;
    }
}
