package com.arjuna.sde.analysis.datashield;

import java.lang.Exception;

import java.util.Base64;
import java.util.UUID;

import jakarta.inject.Inject;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.QuarkusApplication;

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
        if (args.length != 6)
        {
            System.err.printf("Command line: analysis-datashield-invoker <platform name> <profile name> <symbol names list> <table names list> <workspace name> <r script>%n");
            return -1;
        }

        try
        {
            String platformName    = args[0];
            String profileName     = args[1];
            String symbolNamesList = args[2];
            String tableNamesList  = args[3];
            String workspaceName   = args[4];
            String rScript         = args[5];

            log.debug("PlatformName:    " + platformName);
            log.debug("ProfileName:     " + profileName);
            log.debug("SymbolNamesList: " + symbolNamesList);
            log.debug("TableNamesList:  " + tableNamesList);
            log.debug("WorkspaceName:   " + workspaceName);
            log.debug("RScript:         " + rScript);

            String selectProfileResult = armadilloService.postSelectProfile(profileName, Boolean.FALSE);
            String loadTableResult     = armadilloService.postLoadTable(symbolNamesList, tableNamesList, Boolean.FALSE);
            String executeResult       = armadilloService.postExecute(rScript, Boolean.FALSE);

            log.debug("SelectProfileResult: " + selectProfileResult);
            log.debug("LoadTableResult:     " + loadTableResult);
            log.debug("ExecuteResult:       " + executeResult);

            System.out.println(Base64.getEncoder().encodeToString(executeResult.getBytes()));

            return 0;
        }
        catch(Error error)
        {
            log.error("Error performing of DataSHIELD Analysis invocation", error);
            return -1;
        }
        catch(Exception exception)
        {
            log.error("Exception performing of DataSHIELD Analysis invocation", exception);
            return -1;
        }
    }
}
