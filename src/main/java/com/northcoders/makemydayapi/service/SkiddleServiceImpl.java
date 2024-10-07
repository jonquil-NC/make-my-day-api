package com.northcoders.makemydayapi.service;


import com.northcoders.makemydayapi.dto.skiddle.SkiddleEvent;
import com.northcoders.makemydayapi.dto.skiddle.SkiddleEventsResult;
import com.northcoders.makemydayapi.mapper.SkiddleResponseMapper;
import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.dto.activity.oneoff.OneOffActivityResponse;
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
public class SkiddleServiceImpl implements SkiddleService {

    private static final String BASE_URL = "https://www.skiddle.com/api/v1/";
    private static final String LONDON_LAT = "51.5074";
    private static final String LONDON_LON = "-0.1278";
    private static final String LONDON_RADIUS_IN_MILES = "15";

    @Value("${skiddle-api-key}")
    private String skiddleApiKey;
    private final WebClient webClient;


    public SkiddleServiceImpl() {

        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Override
    @Async
    public CompletableFuture<List<OneOffActivityResponse>> getEventsByActivityType(OneOffActivityType activityType) {
        Integer limit = 10;

        log.info("Retrieving {} {} events from Skiddle", limit, activityType);

        SkiddleEventsResult result = this.webClient.get()
                .uri("/events"
                        + "?api_key=" + skiddleApiKey
                        + "&longitude=" + LONDON_LON
                        + "&latitude=" + LONDON_LAT
                        + "&radius=" + LONDON_RADIUS_IN_MILES
                        + "&eventcode=" + activityType.name()
                        + "&limit=" + limit
                )
                .retrieve()
                .bodyToMono(SkiddleEventsResult.class)
                .block();

        List<SkiddleEvent> skiddleEvents = result.getResults();

        if (skiddleEvents.isEmpty()) {
            log.info("Retrieved {} events from Skiddle", skiddleEvents.size());
            return CompletableFuture.completedFuture(List.of());
        }

        log.info("Retrieved [{} of {}] {} events from Skiddle", skiddleEvents.size(), result.getTotalcount(), activityType);

        List<OneOffActivityResponse> activities = new ArrayList<>();

        log.info("Mapping {} {} events to an Activity", skiddleEvents.size(), activityType);

        skiddleEvents.forEach(skiddleEvent -> {
            OneOffActivityResponse oneOffActivityResponse = SkiddleResponseMapper.toResponseDTO(skiddleEvent);
            activities.add(oneOffActivityResponse);
        });

        log.info("Mapped {} {} events to an Activity", activities.size(), activityType);

        return CompletableFuture.completedFuture(activities);
    }
}


