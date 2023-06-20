package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import edu.kit.datamanager.ro_crate.RoCrate;

@ApplicationScoped
public class DummyRequestChecker implements RequestChecker
{
    private Boolean needManualCheck;

    public DummyRequestChecker()
    {
        needManualCheck = Boolean.TRUE;
    }

    public Boolean check(RoCrate request)
    {
        needManualCheck = Boolean.logicalXor(needManualCheck, Boolean.TRUE);

        return needManualCheck;
    }
}
