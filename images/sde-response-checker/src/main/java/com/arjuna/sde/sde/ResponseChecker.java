package com.arjuna.sde.sde;

import io.vertx.core.json.JsonObject;

interface ResponseChecker
{
    public String getName();

    public String getDescription();

    public Boolean check(JsonObject response);
}
