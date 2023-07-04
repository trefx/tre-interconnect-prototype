package com.arjuna.sde.sde;

import jakarta.inject.Inject;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class DataSHIELDAnalysisInvoker implements QuarkusApplication
{
    @Inject
    public Logger log;

    @Inject
    @RestClient
    public ArmadilloService armadilloService;

    public int run(String... args)
        throws Exception
    {
        try
        {
            if (args.length != 6)
            {
                log.error("Usage: DataSHIELDAnalysisInvoker platform_name profile_name symbol_names_list table_names_list workspace_name rscript");
                return 1;
            }

            String dataSHIELDPlatformName    = args[0];
            String dataSHIELDProfileName     = args[1];
            String dataSHIELDSymbolNamesList = args[2];
            String dataSHIELDTableNamesList  = args[3];
            String dataSHIELDWorkspaceName   = args[4];
            String dataSHIELDRScript         = args[5];

            log.infof("DataSHIELD Platform Name     %s", dataSHIELDPlatformName);
            log.infof("DataSHIELD Profile Name      %s", dataSHIELDProfileName);
            log.infof("DataSHIELD Symbol Names List %s", dataSHIELDSymbolNamesList);
            log.infof("DataSHIELD Table Names List  %s", dataSHIELDTableNamesList);
            log.infof("DataSHIELD Workspace Name    %s", dataSHIELDWorkspaceName);
            log.infof("DataSHIELD R Script          %s", dataSHIELDRScript);

            // Load Workspace
            armadilloService.postSelectProfile(dataSHIELDProfileName, false);

            armadilloService.postLoadTable(dataSHIELDSymbolNamesList, dataSHIELDTableNamesList, false);

            String response = armadilloService.postExecute(dataSHIELDRScript, false);
            // Save Workspace
        }
        catch(Error error)
        {
            log.error("Error performing of DataSHIELD Analysis invocation", error);
        }
        catch(Exception exception)
        {
            log.error("Exception performing of DataSHIELD Analysis invocation", exception);
        }

        return 0;
    }
}
