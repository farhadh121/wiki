package com.example.kafka.stream.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.kafka.stream.demo.models.WikipediaLinks;
import com.example.kafka.stream.demo.models.WikipediaPage;
import com.example.kafka.stream.demo.models.WikipediaSearchResponse;
import com.example.kafka.stream.demo.models.WikipediaSearchResultDto;
import com.example.kafka.stream.demo.models.WikipediaTitleSearchResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WikiConsumer {
   
private final RestTemplate restTemplate;
    private final String WIKIPEDIA_API_URL = "https://en.wikipedia.org/w/api.php";
    private final String WIKIPEDIA_SEARCH_API_URL = "https://en.wikipedia.org/w/api.php?action=query&format=json&pageids=1590s_BC&prop=info|revisions";

    private AmqpTemplate amqpTemplate;

    ObjectMapper objectMapper = new ObjectMapper();
    private String challengesTopicExchange;



    public WikiConsumer(RestTemplate restTemplate, AmqpTemplate amqpTemplate,  
    @Value("${amqp.exchange.attempts}")
    final String challengesTopicExchange) {
        this.restTemplate = restTemplate;
        this.challengesTopicExchange = challengesTopicExchange;
    }

    public WikipediaPage getWikipediaPage(String pageTitle) {
        String apiUrl = WIKIPEDIA_API_URL + "?action=query&format=json&titles=" + pageTitle;
        return restTemplate.getForObject(apiUrl, WikipediaPage.class);
    }

    /**
     * Open Search function that returns related pages
     * @param query 
     * @return List of 
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public List<WikipediaSearchResultDto> searchWikipedia(final String query) throws JsonMappingException, JsonProcessingException {
        List<WikipediaSearchResultDto> searchResultDtos = new ArrayList<>();
        String apiUrl = WIKIPEDIA_API_URL + "?action=opensearch&search=" + query;
        String jsonString = restTemplate.getForObject(apiUrl, String.class);
        WikipediaSearchResponse response = objectMapper.readValue(jsonString, WikipediaSearchResponse.class);
        System.out.println("URL " + response.getUrls());
        for(int i = 0; i < response.getUrls().size(); i++) {
            searchResultDtos.add(WikipediaSearchResultDto.builder().pageTitle(response.getPageTitles().get(i)).url(response.getUrls().get(i)).build());
        }
        return searchResultDtos;
    }

    public WikipediaTitleSearchResponse searchWikipediaByPageTitle(final String pageTitle) throws JsonMappingException, JsonProcessingException {
        String apiUrl = WIKIPEDIA_API_URL + "?action=query&format=json&titles=" + pageTitle+"&prop=links";
        String jsonString = restTemplate.getForObject(apiUrl, String.class);
        WikipediaTitleSearchResponse response = objectMapper.readValue(jsonString, WikipediaTitleSearchResponse.class);
        System.out.println("URL " + response.getPageId());
    
        return response;
    }

    public WikipediaLinks getwikipageLinks(final Integer pageId) throws JsonMappingException, JsonProcessingException {
    
        String apiUrl = WIKIPEDIA_API_URL + "?action=query&format=json&pageids=" + pageId +"&prop=links";
        //    ParameterizedTypeReference<List<WikipediaLinks>> responseType = new ParameterizedTypeReference<List<WikipediaLinks>>() {
        // };

        String jsonString = restTemplate.getForObject(apiUrl, String.class);
        System.out.println("JSON RESPNSE " +jsonString);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        WikipediaLinks response = objectMapper.readValue(jsonString, WikipediaLinks.class);
        // System.out.println("URL " + response.get(0).getTitle());
        
        return response;
        
    }
}
