package com.arjuna.sde.sde;

import edu.kit.datamanager.ro_crate.RoCrate;

import io.vertx.core.json.JsonObject;

interface RequestChecker
{
    public String getName();

    public String getDescription();

    public Boolean check(JsonObject request);

    public Boolean isEnabled();

    public void setEnabled(Boolean enabled);

    public Boolean isImmutable();

    public void setImmutable(Boolean immutable);
}
