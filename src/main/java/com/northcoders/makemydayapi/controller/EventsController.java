package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.jpa.entities.Event;
import com.northcoders.makemydayapi.jpa.repositories.EventsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EventsController {

    private static final Logger LOG = LoggerFactory.getLogger(EventsController.class);

    @Autowired
    private EventsRepository eventsRepository;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEvents() {
        try {
            List<Event> events = this.eventsRepository.findAll();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            LOG.error("Error during retrieving all events", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
