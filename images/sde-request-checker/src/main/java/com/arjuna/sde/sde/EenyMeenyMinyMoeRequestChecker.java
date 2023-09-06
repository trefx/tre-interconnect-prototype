package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class EenyMeenyMinyMoeRequestChecker implements RequestChecker
{
    private Boolean needManualCheck;

    public EenyMeenyMinyMoeRequestChecker()
    {
        needManualCheck = Boolean.TRUE;
    }

    public String getName()
    {
        return "Eeny, Meeny, Miny, Moe Request Checker";
    }

    public String getDescription()
    {
       return "Causes alternate requests to be manualy checked. No request augmentation is performed by this checker";
    }

    public Boolean check(JsonObject request)
    {
        needManualCheck = Boolean.logicalXor(needManualCheck, Boolean.TRUE);

        return needManualCheck;
    }
}
