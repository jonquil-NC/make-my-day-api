package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.model.Activity;
import com.northcoders.makemydayapi.service.TicketmasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticketmaster")
public class TicketmasterController {

    @Autowired
    TicketmasterService ticketmasterService;

    //Defining event types (Ticketmaster keywords)
//    private static final List<String> EVENT_TYPES = List.of("museum", "concert", "live events", "shows");


    @GetMapping("/events")
    public ResponseEntity<List<Activity>> getAllEvents() {

        List<Activity> ticketmasterEvents = ticketmasterService.getAllEvents();

        return new ResponseEntity<>(ticketmasterEvents, HttpStatus.OK);
    }

//    TODO
//    @GetMapping("/events")
//    public ResponseEntity<List<Activity>> getEventsByType(@RequestParam String eventType) {
//
//        //Validate event type is in the list
//        if (!EVENT_TYPES.contains(eventType.toLowerCase())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        try {
//            List<Activity> activities = ticketmasterService.getEventsByKeyword(eventType);
//            if (activities.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(activities, HttpStatus.OK);
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
//        }
//    }
}
