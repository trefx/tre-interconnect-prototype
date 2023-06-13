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
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import io.minio.MinioClient;

class DataSHIELDRequest
{
    public String platformName;
    public String profileName;
    public String symbolNamesList;
    public String tableNamesList;
    public String workspaceName;
    public String rScript;

    public DataSHIELDRequest()
    {
    }

    public DataSHIELDRequest(String platformName, String profileName, String symbolNamesList, String tableNamesList, String workspaceName, String rScript)
    {
        this.platformName    = platformName;
        this.profileName     = profileName;
        this.symbolNamesList = symbolNamesList;
        this.tableNamesList  = tableNamesList;
        this.workspaceName   = workspaceName;
        this.rScript         = rScript;
    }
}

@Path("/datashield_request_submitter")
public class ROCrateDataSHIELDRequestSubmitterResource
{
    @Inject
    public Logger log;

    @Inject
    public ObjectMapper objectMapper;

    @Channel("drs_outgoing")
    public Emitter<RoCrate> requestEmitter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postSubmitDataSHIELDRequest(DataSHIELDRequest dataSHIELDRequest)
    {
        log.info("############ Lab - ROCrateDataSHIELDRequestSubmitterResource::postSubmitDataSHIELDRequest ############");

        try
        {
            log.debugf("DataSHIELD Platform Name:     %s", dataSHIELDRequest.platformName);
            log.debugf("DataSHIELD Profile Name:      %s", dataSHIELDRequest.profileName);
            log.debugf("DataSHIELD Symbol Names List: %s", dataSHIELDRequest.symbolNamesList);
            log.debugf("DataSHIELD Table Names List:  %s", dataSHIELDRequest.tableNamesList);
            log.debugf("DataSHIELD Workspace Name:    %s", dataSHIELDRequest.workspaceName);
            log.debugf("DataSHIELD R Script:          %s", dataSHIELDRequest.rScript);

            String platformName    = validDataSHIELDPlatformName(dataSHIELDRequest.platformName);
            String profileName     = validDataSHIELDProfileName(dataSHIELDRequest.profileName);
            String symbolNamesList = validDataSHIELDSymbolNamesList(dataSHIELDRequest.symbolNamesList);
            String tableNamesList  = validDataSHIELDTableNamesList(dataSHIELDRequest.tableNamesList);
            String workspaceName   = validDataSHIELDWorkspaceName(dataSHIELDRequest.workspaceName);
            String rScript         = validDataSHIELDRScript(dataSHIELDRequest.rScript);

            RootDataEntity rootDataEntity = new RootDataEntity.RootDataEntityBuilder()
                .addProperty("request-type", "DataSHIELD:1.0.0")
                .addProperty("datashield-platform-name", platformName)
                .addProperty("datashield-profile-name", profileName)
                .addProperty("datashield-symbol-names-list", symbolNamesList)
                .addProperty("datashield-table-names-list", tableNamesList)
                .addProperty("datashield-workspace-name", workspaceName)
                .addProperty("datashield-r-script", rScript)
                .build();

            RoCrate roCrate = new RoCrate.RoCrateBuilder("Request", UUID.randomUUID().toString())
                .build();

            roCrate.setRootDataEntity(rootDataEntity);

            requestEmitter.send(roCrate);

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
