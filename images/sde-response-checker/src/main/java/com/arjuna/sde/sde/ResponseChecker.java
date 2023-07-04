package com.arjuna.sde.sde;

import edu.kit.datamanager.ro_crate.RoCrate;

interface ResponseChecker
{
    public String getName();

    public String getDescription();

    public Boolean check(RoCrate response);
}
