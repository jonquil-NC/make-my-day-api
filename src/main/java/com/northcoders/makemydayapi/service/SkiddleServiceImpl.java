package com.northcoders.makemydayapi.service.impl;


import com.northcoders.makemydayapi.jpa.entities.Event;
import com.northcoders.makemydayapi.jpa.entities.EventType;
import com.northcoders.makemydayapi.jpa.entities.SourceEvent;
import com.northcoders.makemydayapi.jpa.repositories.EventsRepository;
import com.northcoders.makemydayapi.model.skiddle.SkiddleEvent;
import com.northcoders.makemydayapi.model.skiddle.SkiddleEventsResult;
import com.northcoders.makemydayapi.service.SkiddleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class SkiddleServiceImpl implements SkiddleService {

    private static final String API_KEY = "6267ce9bfd046dd037f269901a410015";
    private static final Logger LOG = LoggerFactory.getLogger(SkiddleServiceImpl.class);
    @Autowired
    private EventsRepository eventsRepository;
    private final WebClient webClient;

    public SkiddleServiceImpl() {

        this.webClient = WebClient.builder()
                .baseUrl("https://www.skiddle.com/api/v1/")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    public List<SkiddleEvent> getAllEvents() {

        LOG.info("Retrieving all events for Skiddler");

        SkiddleEventsResult result = this.webClient.get()
                .uri("/events?limit=100&api_key=" + API_KEY)
                .retrieve()
                .bodyToMono(SkiddleEventsResult.class)
                .block();

        LOG.info("Retrieved [{} of {}] events from Skiddler", result.getResults().size(), result.getTotalcount());
        return result.getResults();
    }

    public void populateDatabase() {
        List<SkiddleEvent> eventList = this.getAllEvents();

        // Map from SkiddleEvent to Event (from Entities)
        List<Event> databaseEventList = eventList.stream()
                .map(skiddleEvent -> {
                    Event event = new Event();
                    event.setId(skiddleEvent.getId());
                    event.setSourceEvent(SourceEvent.SKIDDLE);
                    event.setName(skiddleEvent.getEventname());
                    event.setDescription(skiddleEvent.getDescription());
                    event.setDate(skiddleEvent.getDate());
                    event.setStartTime(skiddleEvent.getStartdate().toLocalTime());
                    event.setEndTime(skiddleEvent.getEnddate().toLocalTime());
                    event.setPrice(null);
                    event.setUrl(skiddleEvent.getLink());
                    event.setImageUrl(skiddleEvent.getImageurl());
                    event.setLatitude(skiddleEvent.getVenue().getLatitude());
                    event.setLongitude(skiddleEvent.getVenue().getLongitude());

                    //Set Type
                    if(skiddleEvent.getEventCode() == null) {
                        event.setType(EventType.EVENT);
                    } else {
                        switch (skiddleEvent.getEventCode()) {
                            case "SPORT" -> event.setType(EventType.SPORTS);
                            case "ARTS" -> event.setType(EventType.ARTS);
                            case "FEST" -> event.setType(EventType.FESTIVAL);
                            case "LIVE" -> event.setType(EventType.LIVE);
                            case "CLUB" -> event.setType(EventType.CLUB);
                            case "DATE" -> event.setType(EventType.DATING);
                            case "THEATRE" -> event.setType(EventType.THEATRE);
                            case "COMEDY" -> event.setType(EventType.COMEDY);
                            case "EXHIB" -> event.setType(EventType.EXHIBITION);
                            case "KIDS" -> event.setType(EventType.KIDS);
                            case "BARPUB" -> event.setType(EventType.BAR);
                            case "LGB" -> event.setType(EventType.LGBT);
                            default -> event.setType(EventType.EVENT);
                        }
                    }

                    return event;
                })
                .toList();

        LOG.info("Map completed. Result: {}", databaseEventList);

        this.eventsRepository.saveAll(databaseEventList);
    }


}


