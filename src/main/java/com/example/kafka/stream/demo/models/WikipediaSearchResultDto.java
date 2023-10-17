package com.example.kafka.stream.demo.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WikipediaSearchResultDto {
    private String pageTitle;
    private String url;
    List<String> wikipageLinks;
}