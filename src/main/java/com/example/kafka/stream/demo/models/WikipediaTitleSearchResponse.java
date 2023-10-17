package com.example.kafka.stream.demo.models;

import java.util.ArrayList;
import java.util.List;

import com.example.kafka.stream.demo.deserializers.WikiTitleSearchResultDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = WikiTitleSearchResultDeserializer.class)
public class WikipediaTitleSearchResponse {
    private String pageId;
    private String pageTitle;
    private List<WikipediaLinks> links = new ArrayList<>();

}
