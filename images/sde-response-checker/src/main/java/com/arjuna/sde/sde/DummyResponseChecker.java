package com.arjuna.sde.sde;

import jakarta.enterprise.context.ApplicationScoped;

import edu.kit.datamanager.ro_crate.RoCrate;

@ApplicationScoped
public class DummyResponseChecker implements ResponseChecker
{
    private Boolean needManualCheck;

    public DummyResponseChecker()
    {
        needManualCheck = Boolean.TRUE;
    }

    public Boolean check(RoCrate response)
    {
        needManualCheck = Boolean.logicalXor(needManualCheck, Boolean.TRUE);

        return needManualCheck;
    }
}
