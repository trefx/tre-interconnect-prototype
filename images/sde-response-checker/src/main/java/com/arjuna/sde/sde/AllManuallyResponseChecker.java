package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class AllManuallyResponseChecker implements ResponseChecker
{
    public AllManuallyResponseChecker()
    {
    }

    public String getName()
    {
        return "All Manually Response Checker";
    }

    public String getDescription()
    {
       return "Causes all responses to be manually checked. No response augmentation is performed by this checker";
    }

    public Boolean check(JsonObject response)
    {
        return Boolean.TRUE;
    }
}
