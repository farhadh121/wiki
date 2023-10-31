package com.example.kafka.stream.demo.models;

import com.example.kafka.stream.demo.deserializers.WikiLinksResultDeserilizer;
import com.example.kafka.stream.demo.deserializers.WikiSearchResultDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @JsonDeserialize(using = WikiSearchResultDeserializer.class)
public class WikipediaLinks {
    @JsonProperty("continue")
    private Continue continueData;
    private Query query;
}
