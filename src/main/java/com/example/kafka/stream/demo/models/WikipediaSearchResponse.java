package com.example.kafka.stream.demo.models;

import java.util.List;

import com.example.kafka.stream.demo.deserializers.WikiSearchResultDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = WikiSearchResultDeserializer.class)
public class WikipediaSearchResponse {

    private String searcgQuery;
    List<String> pageTitles;
    List<String> emplyList;
    List<String> urls;
}

