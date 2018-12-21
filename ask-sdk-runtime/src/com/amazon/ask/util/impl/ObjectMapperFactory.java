/*
    Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
    except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
    the specific language governing permissions and limitations under the License.
 */

package com.amazon.ask.util.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.*;
import java.lang.*;

public final class ObjectMapperFactory {

    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY); // for amazon pay access for group
        mapper.registerModule(new JavaTimeModule());
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }
    public class CustomStringDeserializer extends JsonDeserializer<List<String>>{

    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return mapper.readValue(p, List.class);
    }

}

}
