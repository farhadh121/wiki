package com.example.kafka.stream.demo.deserializers;

import com.example.kafka.stream.demo.models.WikipediaSearchResponse;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WikiSearchResultDeserializer extends StdDeserializer<WikipediaSearchResponse> {

    public WikiSearchResultDeserializer() {
        this(null);
    }

    protected WikiSearchResultDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public WikipediaSearchResponse deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String stringValue = node.get(0).asText();

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();

        for (JsonNode item : node.get(1)) {
            list1.add(item.asText());
        }

        for (JsonNode item : node.get(2)) {
            list2.add(item.asText());
        }

        for (JsonNode item : node.get(3)) {
            list3.add(item.asText());
        }


        return new WikipediaSearchResponse(stringValue, list1, list2, list3);
    }
}