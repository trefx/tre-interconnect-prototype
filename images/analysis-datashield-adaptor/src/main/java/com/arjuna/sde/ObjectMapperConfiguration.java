package com.arjuna.sde;

import jakarta.inject.Singleton;
import jakarta.enterprise.inject.Instance;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.jackson.ObjectMapperCustomizer;

public class ObjectMapperConfiguration
{
//    @Singleton
    public ObjectMapper objectMapper(Instance<ObjectMapperCustomizer> objectMapperCustomizers)
    {
        System.err.println("******** ObjectMapper Setup ********");

        ObjectMapper objectMapper = edu.kit.datamanager.ro_crate.objectmapper.MyObjectMapper.getMapper();
        for (ObjectMapperCustomizer objectMapperCustomizer: objectMapperCustomizers)
             objectMapperCustomizer.customize(objectMapper);

        return objectMapper;
    }
}
