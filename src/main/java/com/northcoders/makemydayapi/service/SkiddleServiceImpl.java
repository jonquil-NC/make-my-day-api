package com.northcoders.makemydayapi.service;


import com.northcoders.makemydayapi.dto.skiddle.SkiddleEvent;
import com.northcoders.makemydayapi.dto.skiddle.SkiddleEventsResult;
import com.northcoders.makemydayapi.mapper.SkiddleResponseMapper;
import com.northcoders.makemydayapi.model.Activity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SkiddleServiceImpl implements SkiddleService {

//    private static final Logger LOG = LoggerFactory.getLogger(SkiddleServiceImpl.class);
    @Value("${skiddle-api-key}")
    private String skiddleApiKey;
    private final WebClient webClient;

    public SkiddleServiceImpl() {

        this.webClient = WebClient.builder()
                .baseUrl("https://www.skiddle.com/api/v1/")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    public List<Activity> getAllEvents() {
        Integer limit = 100;

        log.info("Retrieving {} events for Skiddler", limit);

        SkiddleEventsResult result = this.webClient.get()
                .uri("/events"
                        + "?api_key=" + skiddleApiKey
                        + "&limit=" + limit
                )
                .retrieve()
                .bodyToMono(SkiddleEventsResult.class)
                .block();

        List<SkiddleEvent> skiddleEvents = result.getResults();

        log.info("Retrieved [{} of {}] events from Skiddler", skiddleEvents.size(), result.getTotalcount());

        List<Activity> activities = new ArrayList<>();

        log.info("Mapping {} events to an Activity", skiddleEvents.size());

        skiddleEvents.forEach(skiddleEvent -> {
            Activity activity = SkiddleResponseMapper.toEntity(skiddleEvent);
            activities.add(activity);
        });

        log.info("Mapped {} events to an Activity", activities.size());

        return activities;
    }

    @Override
    public List<Activity> getEventsByDate(LocalDate date) {
       List<Activity> allEvents = getAllEvents();
       return allEvents.stream()
               .filter(event -> event.getDate() != null && event.getDate().equals(date))
               .collect(Collectors.toList());
    }

    @Override
    public List<Activity> getEventsByVenue(String venueName) {
        List<Activity> allEvents = getAllEvents();
        return allEvents.stream()
                .filter(event -> event.getVenue() != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Activity> getEventsByPrice(String price) {
        List<Activity> allEvents = getAllEvents();
        return allEvents.stream()
                .filter(event -> event.getPrice() != null)
                .collect(Collectors.toList());
    }


}


