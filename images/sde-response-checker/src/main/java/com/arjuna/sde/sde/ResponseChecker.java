package com.arjuna.sde.sde;

import edu.kit.datamanager.ro_crate.RoCrate;

interface ResponseChecker
{
    public Boolean check(RoCrate response);
}
