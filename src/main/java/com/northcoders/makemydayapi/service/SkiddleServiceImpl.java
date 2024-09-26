package com.northcoders.makemydayapi.service;



import com.northcoders.makemydayapi.model.SkiddleEvent;
import com.northcoders.makemydayapi.model.SkiddleEventsResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

    @Service
    public class SkiddleServiceImpl implements SkiddleService{


        private WebClient webClient;

        private static final String API_KEY="6267ce9bfd046dd037f269901a410015";

        public SkiddleServiceImpl() {

            this.webClient = WebClient.builder()
                    .baseUrl("https://www.skiddle.com/api/v1/")
                    .defaultHeader("Accept","application/json" )
                    .build();
        }

        public List<SkiddleEvent>getAllEvents() {

            SkiddleEventsResult result =this.webClient.get()
                    .uri("/events?api_key=" + API_KEY)
                    .retrieve()
                    .bodyToMono(SkiddleEventsResult.class)
                    .block();

            return result.getResults();
        }
    }


