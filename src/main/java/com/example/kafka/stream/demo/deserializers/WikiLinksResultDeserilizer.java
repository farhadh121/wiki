package com.example.kafka.stream.demo.deserializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.kafka.stream.demo.models.WikipediaLinks;
import com.example.kafka.stream.demo.models.WikipediaTitleSearchResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class WikiLinksResultDeserilizer  extends StdDeserializer<List<WikipediaLinks>> {

    public WikiLinksResultDeserilizer() {
        this(null);
    }

    protected WikiLinksResultDeserilizer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<WikipediaLinks> deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode node = jsonParser.readValueAsTree();
        List<WikipediaLinks> links = new ArrayList<>();

        // Iterate through the fields in the "pages" object
        Iterator<Map.Entry<String, JsonNode>> fields = node.path("query").path("pages").path("links").fields();
        if (fields.hasNext()) {
            WikipediaLinks link = new WikipediaLinks();
            Map.Entry<String, JsonNode> entry = fields.next();
           link.setNs(entry.getValue().path("ns").asInt());
           link.setTitle(entry.getValue().path("title").asText());
        }

        return links;
    }
}