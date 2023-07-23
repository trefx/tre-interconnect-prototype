package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class DummyResponseChecker implements ResponseChecker
{
    private Boolean needManualCheck;

    public DummyResponseChecker()
    {
        needManualCheck = Boolean.TRUE;
    }

    public String getName()
    {
        return "Dummy Response Checker";
    }

    public String getDescription()
    {
       return "Does not augment response, but cause alternate responses to be manualy checked.";
    }

    public Boolean check(JsonObject response)
    {
        needManualCheck = Boolean.logicalXor(needManualCheck, Boolean.TRUE);

        return needManualCheck;
    }
}
