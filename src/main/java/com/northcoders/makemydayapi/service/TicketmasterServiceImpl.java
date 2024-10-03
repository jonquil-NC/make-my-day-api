package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.dto.ticketmaster.Event;
import com.northcoders.makemydayapi.dto.ticketmaster.TicketmasterResponse;
import com.northcoders.makemydayapi.mapper.TicketmasterResponseMapper;
import com.northcoders.makemydayapi.model.Activity;
import com.northcoders.makemydayapi.model.ActivityType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TicketmasterServiceImpl implements TicketmasterService {

    private static final String BASE_URL = "https://app.ticketmaster.com/discovery/v2";
    private static final String LONDON_LAT = "51.5074";
    private static final String LONDON_LON = "-0.1278";
    private final WebClient webClient;

    @Value("${ticketmaster-api-key}")
    private String ticketmasterApiKey;

    public TicketmasterServiceImpl(WebClient.Builder webclientBuilder) {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Override
    public List<Activity> getEventsByActivityType(ActivityType activityType){
        TicketmasterResponse result = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/events")
                        .queryParam("apikey", ticketmasterApiKey)
                        .queryParam("dmaId", "602")
//                        .queryParam("latlong", LONDON_LAT + "," + LONDON_LON)
                        .queryParam("keyword", activityType.name())
                        .build())
                .retrieve()
                .bodyToMono(TicketmasterResponse.class)
                .block();

        List<Event> ticketmasterEvents = result.get_embedded().getEvents();
        List<Activity> activities = new ArrayList<>();
        for (Event ticketMasterEvent : ticketmasterEvents) {
            Activity activity = TicketmasterResponseMapper.toEntity(ticketMasterEvent);
            activities.add(activity);
        }
        return activities;
    }

//    @Override
//    public List<Activity> getAllEvents() {
//        log.info("Retrieving {} events for Ticketmaster", "all");
//        TicketmasterResponse result = this.webClient.get()
//                .uri("/events"
//                        + "?apikey=" + ticketmasterApiKey
//                        + "&latlong=" + LONDON_LAT + "," + LONDON_LON
//                )
//                .retrieve()
//                .bodyToMono(TicketmasterResponse.class)
//                .block();
//        List<Event> ticketmasterEvents = result.get_embedded().getEvents();
//        log.info("Retrieved {} events for Ticketmaster", ticketmasterEvents.size());
//        List<Activity> activities = new ArrayList<>();
//        log.info("Mapping {} events to an Activity", ticketmasterEvents.size());
//        ticketmasterEvents.forEach(ticketMasterEvent -> {
//            Activity activity = TicketmasterResponseMapper.toEntity(ticketMasterEvent);
//            activities.add(activity);
//        });
//        log.info("Mapped {} events to an Activity", activities.size());
//        return activities;
//    }

}
