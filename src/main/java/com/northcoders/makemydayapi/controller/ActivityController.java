package com.northcoders.makemydayapi.controller;


import com.northcoders.makemydayapi.model.Activity;
import com.northcoders.makemydayapi.service.GooglePlacesService;
import com.northcoders.makemydayapi.service.SkiddleService;
import com.northcoders.makemydayapi.service.TicketmasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activities")
public class ActivityController {

    @Autowired
    private GooglePlacesService googlePlacesService;

    @Autowired
    private SkiddleService skiddleService;

    @Autowired
    private TicketmasterService ticketmasterService;

//    google places activities endpoint
    @GetMapping("/google-places")
    public ResponseEntity<List<Activity>> getGooglePlacesActivities() {
        List<Activity> activities = googlePlacesService.getAllPlaces();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    //    skiddle activities endpoint
@GetMapping("/skiddle")
    public ResponseEntity<List<Activity>> getSkiddleActivities() {
        List<Activity> activities = skiddleService.getAllEvents();
        return new ResponseEntity<>(activities, HttpStatus.OK);
}

    //    ticketmaster activities endpoint
@GetMapping("/ticketmaster")
    public ResponseEntity<List<Activity>> getTicketmasterActivities(){
        List<Activity> activities = ticketmasterService.getAllEvents();
        return new ResponseEntity<>(activities, HttpStatus.OK);
}

}
