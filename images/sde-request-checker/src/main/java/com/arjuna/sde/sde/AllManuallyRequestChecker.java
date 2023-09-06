package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class AllManuallyRequestChecker implements RequestChecker
{
    private Boolean enabled;
    private Boolean immutable;

    public AllManuallyRequestChecker()
    {
        enabled   = Boolean.TRUE;
        immutable = Boolean.FALSE;
    }

    public String getName()
    {
        return "All Manually Request Checker";
    }

    public String getDescription()
    {
       return "Causes all requests to be manually checked. No request augmentation is performed by this checker";
    }

    public Boolean check(JsonObject request)
    {
        return Boolean.TRUE;
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
