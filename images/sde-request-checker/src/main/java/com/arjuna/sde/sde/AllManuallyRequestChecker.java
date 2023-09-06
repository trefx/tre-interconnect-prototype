package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class AllManuallyRequestChecker implements RequestChecker
{
    public AllManuallyRequestChecker()
    {
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
}
