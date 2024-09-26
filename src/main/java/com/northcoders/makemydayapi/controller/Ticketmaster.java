package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.model.Events;
import com.northcoders.makemydayapi.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticketmaster")
public class Ticketmaster {

    @Autowired
    EventsService service;

    @GetMapping("/events")
    public ResponseEntity<List<Events>> getAllEvents() {
        try {
            List<Events> events = service.getAllEvents();
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}