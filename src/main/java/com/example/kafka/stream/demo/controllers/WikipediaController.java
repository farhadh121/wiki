package com.example.kafka.stream.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.kafka.stream.demo.WikiConsumer;
import com.example.kafka.stream.demo.models.WikipediaLinks;
import com.example.kafka.stream.demo.models.WikipediaPage;
import com.example.kafka.stream.demo.models.WikipediaSearchResultDto;
import com.example.kafka.stream.demo.models.WikipediaTitleSearchResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class WikipediaController {
    private final WikiConsumer wikipediaService;

    public WikipediaController(WikiConsumer wikipediaService) {
        this.wikipediaService = wikipediaService;
    }

    @GetMapping("/wikipedia/{pageTitle}")
    public WikipediaPage getWikipediaPage(@PathVariable String pageTitle) {
        return wikipediaService.getWikipediaPage(pageTitle);
    }

    @GetMapping("/search/{query}")
    public List<WikipediaSearchResultDto> getSearchResultUrls(@PathVariable String query) throws JsonMappingException, JsonProcessingException {
        return wikipediaService.searchWikipedia(query);
    }

     @GetMapping("/search/titles/{query}")
    public WikipediaTitleSearchResponse getPageIdByTitle(@PathVariable String query) throws JsonMappingException, JsonProcessingException {
        return wikipediaService.searchWikipediaByPageTitle(query);
    }

    @GetMapping("/page/links/{pageId}")
    public List<WikipediaLinks> getPageLinklsByPageId(@PathVariable Integer pageId) throws JsonMappingException, JsonProcessingException {
        return wikipediaService.getwikipageLinks(pageId);
    }
}
