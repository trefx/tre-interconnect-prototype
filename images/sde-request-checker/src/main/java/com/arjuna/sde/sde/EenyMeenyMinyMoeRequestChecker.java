package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class EenyMeenyMinyMoeRequestChecker implements RequestChecker
{
    private Boolean needManualCheck;

    private Boolean enabled;
    private Boolean immutable;

    public EenyMeenyMinyMoeRequestChecker()
    {
        needManualCheck = Boolean.TRUE;

        enabled   = Boolean.TRUE;
        immutable = Boolean.FALSE;
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
    public Boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    public Boolean isImmutable()
    {
        return immutable;
    }

    public void setImmutable(Boolean immutable)
    {
        this.immutable = immutable;
    }
}
