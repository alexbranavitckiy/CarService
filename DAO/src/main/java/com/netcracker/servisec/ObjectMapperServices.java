package com.netcracker.servisec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.concurrent.TimeUnit;

public class ObjectMapperServices {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static ObjectMapper getObjectMapper() {//return via method to configure
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return OBJECT_MAPPER;
    }

}
