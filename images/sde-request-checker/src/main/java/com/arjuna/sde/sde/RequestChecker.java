package com.arjuna.sde.sde;

import edu.kit.datamanager.ro_crate.RoCrate;

interface RequestChecker
{
    public String getName();

    public String getDescription();

    public Boolean check(RoCrate request);
}
