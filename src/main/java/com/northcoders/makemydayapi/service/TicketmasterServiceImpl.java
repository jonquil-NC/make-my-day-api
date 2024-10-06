package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.dto.ticketmaster.Event;
import com.northcoders.makemydayapi.dto.ticketmaster.TicketmasterResponse;
import com.northcoders.makemydayapi.dto.ticketmaster.enums.TicketmasterSegment;
import com.northcoders.makemydayapi.mapper.TicketmasterResponseMapper;
import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class TicketmasterServiceImpl implements TicketmasterService {

    private static final String BASE_URL = "https://app.ticketmaster.com/discovery/v2";
    //    private static final String LONDON_LAT = "51.5074";
//    private static final String LONDON_LON = "-0.1278";
    private final WebClient webClient;

    @Value("${ticketmaster-api-key}")
    private String ticketmasterApiKey;

    public TicketmasterServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Override
    @Async
    public CompletableFuture<List<TicketmasterSkiddleActivity>> getEventsByActivityType(OneOffActivityType activityType) {
        log.info("Retrieving {} events from Ticketmaster", activityType);

        TicketmasterResponse result = this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/events")
                        .queryParam("apikey", ticketmasterApiKey)
                        .queryParam("dmaId", "602")
//                        .queryParam("latlong", LONDON_LAT + "," + LONDON_LON)
//                        .queryParam("keyword", activityType.name())
                        .queryParam("classificationId", getClassificationId(activityType))
                        .build())
                .retrieve()
                .bodyToMono(TicketmasterResponse.class)
                .block();

        List<Event> ticketmasterEvents = result.getEmbeddedEvents().getEvents();

        if (ticketmasterEvents.isEmpty()) {
            log.info("Retrieved {} events from Ticketmaster", ticketmasterEvents.size());
            return CompletableFuture.completedFuture(List.of());
        }

        log.info("Retrieved {} {} events from Ticketmaster", ticketmasterEvents.size(), activityType);

        List<TicketmasterSkiddleActivity> activities = new ArrayList<>();

        log.info("Mapping {} {} events to an Activity", ticketmasterEvents.size(), activityType);

        for (Event ticketMasterEvent : ticketmasterEvents) {
            TicketmasterSkiddleActivity activity = TicketmasterResponseMapper.toEntity(ticketMasterEvent);
            activities.add(activity);
        }

        ticketmasterEvents.forEach(ticketmasterEvent -> {
            TicketmasterSkiddleActivity ticketmasterSkiddleActivity = TicketmasterResponseMapper.toEntity(ticketmasterEvent);
            activities.add(ticketmasterSkiddleActivity);
        });

        log.info("Mapped {} {} events to an Activity", activities.size(), activityType);

        return CompletableFuture.completedFuture(activities);
    }

    @Override
    @Async
    public CompletableFuture<List<TicketmasterSkiddleActivity>> getEventsByActivityTypes(List<OneOffActivityType> activityTypes) {

        activityTypes.forEach(activityType -> log.info("Retrieving {} events from Ticketmaster", activityType));

        TicketmasterResponse result = this.webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder
                            .path("/events")
                            .queryParam("apikey", ticketmasterApiKey)
                            .queryParam("dmaId", "602");

                    activityTypes.forEach(activityType -> uriBuilder.queryParam("classificationId", getClassificationId(activityType)));

                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToMono(TicketmasterResponse.class)
                .block();

        List<Event> ticketmasterEvents = result.getEmbeddedEvents().getEvents();


        if (ticketmasterEvents.isEmpty()) {
            log.info("Retrieved {} events from Ticketmaster", ticketmasterEvents.size());
            return CompletableFuture.completedFuture(List.of());
        }

        log.info("Retrieved {} events from Ticketmaster", ticketmasterEvents.size());

        List<TicketmasterSkiddleActivity> activities = new ArrayList<>();

        log.info("Mapping {} events to an Activity", ticketmasterEvents.size());

        ticketmasterEvents.forEach(ticketmasterEvent -> {
            TicketmasterSkiddleActivity activity = TicketmasterResponseMapper.toEntity(ticketmasterEvent);
            activities.add(activity);
        });

        log.info("Mapped {} events to an Activity", activities.size());

        return CompletableFuture.completedFuture(activities);
    }

    private String getClassificationId(OneOffActivityType activityType) {
        if (activityType.equals(OneOffActivityType.SPORTS)) {
            return TicketmasterSegment.Sports.getId();
        } else if (activityType.equals(OneOffActivityType.MUSIC)) {
            return TicketmasterSegment.Music.getId();
        } else if (activityType.equals(OneOffActivityType.CULTURAL)) {
            return TicketmasterSegment.Arts_N_Theatre.getId();
        } else {
            throw new RuntimeException("Unsupported Ticketmaster Activity Type!");
        }
    }


}
