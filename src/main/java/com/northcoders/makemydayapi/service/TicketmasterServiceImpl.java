package com.northcoders.makemydayapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.makemydayapi.dto.skiddle.SkiddleEventsResult;
import com.northcoders.makemydayapi.dto.ticketmaster.Event;
import com.northcoders.makemydayapi.dto.ticketmaster.TicketmasterResponse;
import com.northcoders.makemydayapi.mapper.TicketmasterResponseMapper;
import com.northcoders.makemydayapi.model.Activity;
import com.northcoders.makemydayapi.model.ActivityType;
import com.northcoders.makemydayapi.model.Location;
import com.northcoders.makemydayapi.model.ResourceType;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@Slf4j
public class TicketmasterServiceImpl implements TicketmasterService {

    private static final String BASE_URL = "https://app.ticketmaster.com/discovery/v2";
    private static final String LONDON_LAT = "51.5074";
    private static final String LONDON_LON = "-0.1278";

    @Value("${ticketmaster-api-key}")
    private String ticketmasterApiKey;
    private final WebClient webClient;

    public TicketmasterServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Accept", "application/json")
                .build();
    }


    @Override
    public List<Activity> getAllEvents() {
        log.info("Retrieving {} events for Ticketmaster", "all");

        TicketmasterResponse result = this.webClient.get()
                .uri("/events"
                        + "?apikey=" + ticketmasterApiKey
                        + "&latlong=" + LONDON_LAT + "," + LONDON_LON
                )
                .retrieve()
                .bodyToMono(TicketmasterResponse.class)
                .block();

        List<Event> ticketmasterEvents = result.get_embedded().getEvents();

        log.info("Retrieved {} events for Ticketmaster", ticketmasterEvents.size());

        List<Activity> activities = new ArrayList<>();

        log.info("Mapping {} events to an Activity", ticketmasterEvents.size());

        ticketmasterEvents.forEach(ticketMasterEvent -> {
            Activity activity = TicketmasterResponseMapper.toEntity(ticketMasterEvent);
            activities.add(activity);
        });

        log.info("Mapped {} events to an Activity", activities.size());

        return activities;
    }
}


//    @Override
//    public void saveToItinerary(Activity activity) {
//        LOG.info("Saving event [{}] to itinerary", activity.getName());
//        itineraryRepository.save(activity);  // Save event to the itinerary in the database
//    }