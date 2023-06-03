package com.arjuna.sde.lab;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.arc.All;
import io.quarkus.jackson.ObjectMapperCustomizer;

import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

public class ROCrateObjectMapper
{
    @Singleton
    @Produces
    public ObjectMapper objectMapper(@All Instance<ObjectMapperCustomizer> customizers)
    {
        System.err.println("@#@#@#@#@#@# ROCrateObjectMapper @#@#@#@#@#@#");

        ObjectMapper objectMapper = edu.kit.datamanager.ro_crate.objectmapper.MyObjectMapper.getMapper();
        for (ObjectMapperCustomizer customizer : customizers)
            customizer.customize(objectMapper);

        return objectMapper;
    }
}
