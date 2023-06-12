package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import edu.kit.datamanager.ro_crate.RoCrate;

@ApplicationScoped
public class DummyResponseChecker implements ResponseChecker
{
    public DummyResponseChecker()
    {
    }

    public Boolean check(RoCrate response)
    {
        return Boolean.TRUE;
    }
}
