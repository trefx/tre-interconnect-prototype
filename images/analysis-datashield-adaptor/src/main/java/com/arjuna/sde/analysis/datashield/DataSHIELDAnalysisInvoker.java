package com.arjuna.sde.analysis.datashield;

import java.util.Base64;
import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import edu.kit.datamanager.ro_crate.RoCrate;

import io.smallrye.reactive.messaging.annotations.Blocking;

import com.arjuna.sde.utils.ROCrateTransformer;

@ApplicationScoped
public class DataSHIELDAnalysisInvoker
{
    @Inject
    public Logger log;

    @Inject
    @RestClient
    public ArmadilloService armadilloService;

    @Blocking
    @Incoming("dsa_incoming")
    @Outgoing("dsa_outgoing")
    public byte[] processRequest(byte[] requestBytes)
        throws Exception
    {
        try
        {
            log.info("############ Analysis - DataSHIELDAnalysisInvoker::processRequest ############");

            RoCrate request  = ROCrateTransformer.zipBytesToROC(requestBytes);
            RoCrate response = new RoCrate();

//            JsonArray requestContextualEntities = requestObject.getJsonArray("allContextualEntities");
//
//            JsonObject responseObject = requestObject;
//
//            JsonObject requestParameters = null;
//            for (int index = 0; index < requestContextualEntities.size(); index++)
//            {
//                JsonObject entity = requestContextualEntities.getJsonObject(index);
//                log.info("Object - Class: " + entity.getClass().getName());
//                if (entity.getString("@type").equals("DataSHIELDAnalysisRequestParameters"))
//                    requestParameters = entity;
//            }
//
//            if (requestParameters != null)
//            {
//                String    platformName      = requestParameters.getString("platform-name");
//                String    profileName       = requestParameters.getString("profile-name");
//                String    symbolNamesList   = requestParameters.getString("symbol-names-list");
//                String    tableNamesList    = requestParameters.getString("table-names-list");
//                String    workspaceName     = requestParameters.getString("workspace-name");
//                String    rScript           = requestParameters.getString("r-script");
//
//                log.debug("PlatformName:    " + platformName);
//                log.debug("ProfileName:     " + profileName);
//                log.debug("SymbolNamesList: " + symbolNamesList);
//                log.debug("TableNamesList:  " + tableNamesList);
//                log.debug("WorkspaceName:   " + workspaceName);
//                log.debug("RScript:         " + rScript);
//
//                String selectProfileResult = armadilloService.postSelectProfile(profileName, Boolean.FALSE);
//                String loadTableResult     = armadilloService.postLoadTable(symbolNamesList, tableNamesList, Boolean.FALSE);
//                String executeResult       = armadilloService.postExecute(rScript, Boolean.FALSE);
//
//                log.debug("SelectProfileResult: " + selectProfileResult);
//                log.debug("LoadTableResult:     " + loadTableResult);
//                log.debug("ExecuteResult:       " + executeResult);
//
//                JsonObject responseEntity = new JsonObject();
//                responseEntity.put("@type", "DataSHIELDAnalysisResponse");
//                responseEntity.put("@id", "http://example.org/" + UUID.randomUUID().toString());
//                if (executeResult != null)
//                {
//                    responseEntity.put("outcome", "success");
//                    responseEntity.put("value", Base64.getEncoder().encodeToString(executeResult.getBytes()));
//                }
//                else
//                {
//                    responseEntity.put("outcome", "failed");
//                    responseEntity.put("message", "No result produced");
//                }
//
//                JsonArray responseContextualEntities = responseObject.getJsonArray("allContextualEntities");
//                responseContextualEntities.add(response);
//            }
//            else
//            {
//                JsonObject responseEntity = new JsonObject();
//                responseEntity.put("@type", "DataSHIELDAnalysisResponse");
//                responseEntity.put("@id", "http://example.org/" + UUID.randomUUID().toString());
//                responseEntity.put("outcome", "failed");
//                responseEntity.put("message", "Malformed request, no parameters");
//
//                JsonArray responseContextualEntities = responseObject.getJsonArray("allContextualEntities");
//                responseContextualEntities.add(requestROC);
//            }

            return ROCrateTransformer.rocToZipBytes(response);
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
