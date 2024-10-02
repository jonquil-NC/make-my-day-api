package com.northcoders.makemydayapi.controller;


import com.northcoders.makemydayapi.dto.skiddle.SkiddleEvent;
import com.northcoders.makemydayapi.model.Activity;
import com.northcoders.makemydayapi.service.SkiddleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/skiddle")
public class SkiddleController {

    @Autowired
    private SkiddleService skiddleService;

    @GetMapping("/events")
    public ResponseEntity<List<Activity>> getAllEvents() {
        try {
            List<Activity> skiddleEvents = skiddleService.getAllEvents();
            return new ResponseEntity<>(skiddleEvents, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/events/date/{date}")
    public ResponseEntity<List<Activity>> getEventsByDate(@PathVariable String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            List<Activity> events = skiddleService.getEventsByDate(parsedDate);
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @GetMapping("/events/date/{venueName}")
    public ResponseEntity<List<Activity>> getEventsByVenue(@PathVariable String venueName) {
        try {
            List<Activity> events = skiddleService.getEventsByVenue(venueName);
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/events/date/{price}")
    public ResponseEntity<List<Activity>> getEventsByPrice(@PathVariable String price) {
        try {
            List<Activity> events = skiddleService.getEventsByPrice(price);
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}