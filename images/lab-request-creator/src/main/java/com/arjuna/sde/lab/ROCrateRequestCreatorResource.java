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

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import io.minio.MinioClient;

class Request
{
    public String dataSHIELDPlatformName;
    public String dataSHIELDProfileName;
    public String dataSHIELDSymbolNamesList;
    public String dataSHIELDTableNamesList;
    public String dataSHIELDWorkspaceName;
    public String dataSHIELDRScript;

    public Request()
    {
    }

    public Request(String dataSHIELDPlatformName, String dataSHIELDProfileName, String dataSHIELDSymbolNamesList, String dataSHIELDTableNamesList, String dataSHIELDWorkspaceName, String dataSHIELDRScript)
    {
        this.dataSHIELDPlatformName    = dataSHIELDPlatformName;
        this.dataSHIELDProfileName     = dataSHIELDProfileName;
        this.dataSHIELDSymbolNamesList = dataSHIELDSymbolNamesList;
        this.dataSHIELDTableNamesList  = dataSHIELDTableNamesList;
        this.dataSHIELDWorkspaceName   = dataSHIELDWorkspaceName;
        this.dataSHIELDRScript         = dataSHIELDRScript;
    }
}

@Path("/request_creator")
public class ROCrateRequestCreatorResource
{
    @Inject
    Logger log;

    @Channel("outgoing")
    public Emitter<RoCrate> requestEmitter;

    @Inject
    public  MongoClient mongoClient;

//    @Inject
//    public MinioClient minioClient;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postRequest(Request request)
    {
        try
        {
            log.infof("DataSHIELD Platform Name:     %s", request.dataSHIELDPlatformName);
            log.infof("DataSHIELD Profile Name:      %s", request.dataSHIELDProfileName);
            log.infof("DataSHIELD Symbol Names List: %s", request.dataSHIELDSymbolNamesList);
            log.infof("DataSHIELD Table Names List:  %s", request.dataSHIELDTableNamesList);
            log.infof("DataSHIELD Workspace Name:    %s", request.dataSHIELDWorkspaceName);
            log.infof("DataSHIELD R Script:          %s", request.dataSHIELDRScript);

            String dataSHIELDPlatformName    = validDataSHIELDPlatformName(request.dataSHIELDPlatformName);
            String dataSHIELDProfileName     = validDataSHIELDProfileName(request.dataSHIELDProfileName);
            String dataSHIELDSymbolNamesList = validDataSHIELDSymbolNamesList(request.dataSHIELDSymbolNamesList);
            String dataSHIELDTableNamesList  = validDataSHIELDTableNamesList(request.dataSHIELDTableNamesList);
            String dataSHIELDWorkspaceName   = validDataSHIELDWorkspaceName(request.dataSHIELDWorkspaceName);
            String dataSHIELDRScript         = validDataSHIELDRScript(request.dataSHIELDRScript);

            RootDataEntity rootDataEntity = new RootDataEntity.RootDataEntityBuilder()
                .addProperty("request-type", "DataSHIELD:1.0.0")
                .addProperty("datashield-platform-name", dataSHIELDPlatformName)
                .addProperty("datashield-profile-name", dataSHIELDProfileName)
                .addProperty("datashield-symbol-names-list", dataSHIELDSymbolNamesList)
                .addProperty("datashield-table-names-list", dataSHIELDTableNamesList)
                .addProperty("datashield-workspace-name", dataSHIELDWorkspaceName)
                .addProperty("datashield-r-script", dataSHIELDRScript)
                .build();

            RoCrate roCrate = new RoCrate.RoCrateBuilder("Request", UUID.randomUUID().toString())
                .build();

            roCrate.setRootDataEntity(rootDataEntity);

            log.info("====== Emmitting request ======");
            requestEmitter.send(roCrate);
            log.info("====== Emmitted  request ======");

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
