package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class EenyMeenyMinyMoeResponseChecker implements ResponseChecker
{
    private Boolean needManualCheck;

    public EenyMeenyMinyMoeResponseChecker()
    {
        needManualCheck = Boolean.TRUE;
    }

    public String getName()
    {
        return "Eeny, Meeny, Miny, Moe Response Checker";
    }

    public String getDescription()
    {
       return "Causes alternate responses to be manualy checked. No response augmentation is performed by this checker";
    }

    public Boolean check(JsonObject response)
    {
        needManualCheck = Boolean.logicalXor(needManualCheck, Boolean.TRUE);

        return needManualCheck;
    }
}
