package com.arjuna.sde.lab;

import java.util.UUID;
import java.lang.Error;
import java.lang.Exception;

import javax.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.FolderWriter;
import edu.kit.datamanager.ro_crate.entities.data.RootDataEntity;

@Path("/request_creator")
public class ROCrateRequestCreatorResource
{
    @Channel("outgoing-requests")
    public Emitter<RoCrate> requestEmitter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postRequest(@QueryParam("datashield-platform-name") String dataSHIELDPlatformName, @QueryParam("datashield-profile-name") String dataSHIELDProfileName, @QueryParam("datashield-symbol-names-list") String dataSHIELDSymbolNamesList, @QueryParam("datashield-table-names-list") String dataSHIELDTableNamesList, @QueryParam("datashield-workspace-name") String dataSHIELDWorkspaceName, @QueryParam("datashield-r-script") String dataSHIELDRScript)
    {
        try
        {
            System.out.printf("DataSHIELD Platform Name:     %s\n", dataSHIELDPlatformName);
            System.out.printf("DataSHIELD Profile Name:      %s\n", dataSHIELDProfileName);
            System.out.printf("DataSHIELD Symbol Names List: %s\n", dataSHIELDSymbolNamesList);
            System.out.printf("DataSHIELD Table Names List:  %s\n", dataSHIELDTableNamesList);
            System.out.printf("DataSHIELD Workspace Name:    %s\n", dataSHIELDWorkspaceName);
            System.out.printf("DataSHIELD R Script:          %s\n", dataSHIELDRScript);

            dataSHIELDPlatformName    = validDataSHIELDPlatformName(dataSHIELDPlatformName);
            dataSHIELDProfileName     = validDataSHIELDProfileName(dataSHIELDProfileName);
            dataSHIELDSymbolNamesList = validDataSHIELDSymbolNamesList(dataSHIELDSymbolNamesList);
            dataSHIELDTableNamesList  = validDataSHIELDTableNamesList(dataSHIELDTableNamesList);
            dataSHIELDWorkspaceName   = validDataSHIELDWorkspaceName(dataSHIELDWorkspaceName);
            dataSHIELDRScript         = validDataSHIELDRScript(dataSHIELDRScript);

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

            requestEmitter.send(roCrate);

            return "Done";
        }
        catch (Error error)
        {
            return "Error: " + error.getMessage();
        }
        catch (Exception exception)
        {
            return "Exception: " + exception.getMessage();
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
