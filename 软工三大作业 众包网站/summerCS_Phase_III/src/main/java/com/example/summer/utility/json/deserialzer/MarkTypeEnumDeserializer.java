package com.example.summer.utility.json.deserialzer;

import com.example.summer.utility.enums.MarkTypeEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class MarkTypeEnumDeserializer extends JsonDeserializer<MarkTypeEnum> {
    @Override
    public MarkTypeEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return MarkTypeEnum.valueOf(p.getValueAsString());
    }
}
