package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class RequireAtLeastOneForManualRequestChecker implements RequestChecker
{
    private Boolean enabled;
    private Boolean immutable;

    public RequireAtLeastOneForManualRequestChecker()
    {
        enabled   = Boolean.TRUE;
        immutable = Boolean.FALSE;
    }

    public String getName()
    {
        return "Require At Least One For Manual Request Checker Request Checker";
    }

    public String getDescription()
    {
        return "Causes requests to be manually checked only if one checker indicates request should be. No request augmentation is performed by this checker";
    }

    public Boolean check(JsonObject request)
    {
        return Boolean.FALSE;
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
