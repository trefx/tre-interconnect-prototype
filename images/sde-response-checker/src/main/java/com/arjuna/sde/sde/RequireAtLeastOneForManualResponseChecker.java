package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class RequireAtLeastOneForManualResponseChecker implements ResponseChecker
{
    public RequireAtLeastOneForManualResponseChecker()
    {
    }

    public String getName()
    {
        return "Require At Least One For Manual Response Checker Response Checker";
    }

    public String getDescription()
    {
        return "Causes responses to be manually only if one checker indicates response should be. No response augmentation is performed by this checker";
    }

    public Boolean check(JsonObject response)
    {
        return Boolean.FALSE;
    }
}
