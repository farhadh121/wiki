package com.example.kafka.stream.demo.deserializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.kafka.stream.demo.models.WikipediaLinks;
import com.example.kafka.stream.demo.models.WikipediaTitleSearchResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class WikiTitleSearchResultDeserializer extends StdDeserializer<WikipediaTitleSearchResponse> {

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    public WikiTitleSearchResultDeserializer() {
        this(null);
    }

    protected WikiTitleSearchResultDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public WikipediaTitleSearchResponse deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode node = jsonParser.readValueAsTree();
        WikipediaTitleSearchResponse page = new WikipediaTitleSearchResponse();
        List<WikipediaLinks> myObjects = new ArrayList<>();


        // Iterate through the fields in the "pages" object
        Iterator<Map.Entry<String, JsonNode>> fields = node.path("query").path("pages").fields();
        if (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            page.setPageId(entry.getValue().path("pageid").asText());
            page.setPageTitle(entry.getValue().path("title").asText());
            JsonNode jsonLinks = entry.getValue().path("links");
            ArrayNode arrayNode = (ArrayNode) jsonLinks;


                // Convert each element to a generic object and add it to the list
                Object genericObject = objectMapper.treeToValue(arrayNode, WikipediaLinks.class);
                //myObjects.add(genericObject);

                System.out.println("asda  " + genericObject);
                // for (JsonNode linkNode : ) {
                //     System.out.println("TEST ++ "+ linkNode);
                //     List<WikipediaLinks> myObject = objectMapper.(linkNode,  new ParameterizedTypeReference<List<WikipediaLinks>>() {} );
                //     myObjects.add(myObject);
                // }
            page.setLinks(myObjects);
        }

        return page;
    }
}