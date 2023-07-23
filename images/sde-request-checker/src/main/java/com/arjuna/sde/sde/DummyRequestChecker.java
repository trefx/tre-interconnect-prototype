package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import io.vertx.core.json.JsonObject;

import edu.kit.datamanager.ro_crate.RoCrate;

@ApplicationScoped
public class DummyRequestChecker implements RequestChecker
{
    private Boolean needManualCheck;

    public DummyRequestChecker()
    {
        needManualCheck = Boolean.TRUE;
    }

    public String getName()
    {
        return "Dummy Request Checker";
    }

    public String getDescription()
    {
       return "Does not augment requests, but cause alternate requests to be manualy checked.";
    }

    public Boolean check(JsonObject request)
    {
        needManualCheck = Boolean.logicalXor(needManualCheck, Boolean.TRUE);

        return needManualCheck;
    }
}
