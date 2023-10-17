package com.example.kafka.stream.demo.models;

import com.example.kafka.stream.demo.deserializers.WikiLinksResultDeserilizer;
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
public class WikipediaLinks {
    private Integer ns;
    private String title;
}
