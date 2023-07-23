package com.arjuna.sde.lab;

import java.util.UUID;
import java.lang.Error;
import java.lang.Exception;

import jakarta.inject.Inject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import io.vertx.core.json.JsonObject;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.entities.contextual.ContextualEntity;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import io.minio.MinioClient;

@Path("/datashield_request_submitter")
public class ROCrateDataSHIELDRequestSubmitterResource
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Channel("drs_outgoing")
    public Emitter<JsonObject> requestEmitter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postSubmitDataSHIELDRequest(JsonObject dataSHIELDRequest)
    {
        log.info("############ Lab - ROCrateDataSHIELDRequestSubmitterResource::postSubmitDataSHIELDRequest ############");

        try
        {
            String platformName    = validDataSHIELDPlatformName(dataSHIELDRequest.getString("dataSHIELDPlatformName"));
            String profileName     = validDataSHIELDProfileName(dataSHIELDRequest.getString("dataSHIELDProfileName"));
            String symbolNamesList = validDataSHIELDSymbolNamesList(dataSHIELDRequest.getString("dataSHIELDSymbolNamesList"));
            String tableNamesList  = validDataSHIELDTableNamesList(dataSHIELDRequest.getString("dataSHIELDTableNamesList"));
            String workspaceName   = validDataSHIELDWorkspaceName(dataSHIELDRequest.getString("dataSHIELDWorkspaceName"));
            String rScript         = validDataSHIELDRScript(dataSHIELDRequest.getString("dataSHIELDRScript"));

            log.debugf("DataSHIELD Platform Name:     %s", platformName);
            log.debugf("DataSHIELD Profile Name:      %s", profileName);
            log.debugf("DataSHIELD Symbol Names List: %s", symbolNamesList);
            log.debugf("DataSHIELD Table Names List:  %s", tableNamesList);
            log.debugf("DataSHIELD Workspace Name:    %s", workspaceName);
            log.debugf("DataSHIELD R Script:          %s", rScript);

            RoCrate request = new RoCrate.RoCrateBuilder("TRE-FX Request", UUID.randomUUID().toString())
                .addContextualEntity(
                    new ContextualEntity.ContextualEntityBuilder()
                        .addType("DataSHIELDRequest")
                        .setId("http://example.org/" + UUID.randomUUID().toString())
                        .addProperty("request-type", "DataSHIELD:1.0.0")
                        .addProperty("datashield-platform-name", platformName)
                        .addProperty("datashield-profile-name", profileName)
                        .addProperty("datashield-symbol-names-list", symbolNamesList)
                        .addProperty("datashield-table-names-list", tableNamesList)
                        .addProperty("datashield-workspace-name", workspaceName)
                        .addProperty("datashield-r-script", rScript)
                        .build()
                )
                .build();

            JsonObject requestJson = new JsonObject(objectMapper.writeValueAsString(request));

            requestEmitter.send(requestJson);

            return "{ \"outcome\": \"success\" }";
        }
        catch (Error error)
        {
            log.error("Error while creating request RO_Crate", error);
            return "{ \"outcome\": \"error\", \"message\": \"" + error.getMessage() + "\" }";
        }
        catch (Exception exception)
        {
            log.error("Exception while creating request RO_Crate", exception);
            return "{ \"outcome\": \"exception\", \"message\": \"" + exception.getMessage() + "\" }";
        }
    }

    private String validDataSHIELDPlatformName(String dataSHIELDPlatformName)
        throws Exception
    {
        return dataSHIELDPlatformName;
    }

    private String validDataSHIELDProfileName(String dataSHIELDProfileName)
        throws Exception
    {
        return dataSHIELDProfileName;
    }

    private String validDataSHIELDSymbolNamesList(String dataSHIELDSymbolNamesList)
        throws Exception
    {
        return dataSHIELDSymbolNamesList;
    }

    private String validDataSHIELDTableNamesList(String dataSHIELDTableNamesList)
        throws Exception
    {
        return dataSHIELDTableNamesList;
    }

    private String validDataSHIELDWorkspaceName(String dataSHIELDWorkspaceName)
        throws Exception
    {
        return dataSHIELDWorkspaceName;
    }

    private String validDataSHIELDRScript(String dataSHIELDRScript)
        throws Exception
    {
        return dataSHIELDRScript;
    }
}
