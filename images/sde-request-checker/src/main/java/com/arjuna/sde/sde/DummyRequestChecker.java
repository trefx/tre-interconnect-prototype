package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import edu.kit.datamanager.ro_crate.RoCrate;

@ApplicationScoped
public class DummyRequestChecker implements RequestChecker
{
    public DummyRequestChecker()
    {
    }

    public Boolean check(RoCrate request)
    {
        return Boolean.FALSE;
    }
}
