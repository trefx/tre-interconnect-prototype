package com.arjuna.sde.sde;

import edu.kit.datamanager.ro_crate.RoCrate;

import io.vertx.core.json.JsonObject;

interface RequestChecker
{
    public String getName();

    public String getDescription();

    public Boolean check(JsonObject request);
}
