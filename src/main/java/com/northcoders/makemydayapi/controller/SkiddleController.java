package com.northcoders.makemydayapi.controller;


import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;
import com.northcoders.makemydayapi.model.ActivityType;
import com.northcoders.makemydayapi.service.SkiddleService;
import com.northcoders.makemydayapi.validation.constraints.ValidSkiddleActivityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/skiddle")
@Validated
public class SkiddleController {

    @Autowired
    private SkiddleService skiddleService;

    @GetMapping("/events")
    public ResponseEntity<List<TicketmasterSkiddleActivity>> getEventsByActivityType(@RequestParam @ValidSkiddleActivityType ActivityType activityType) {

        List<TicketmasterSkiddleActivity> filteredSkiddleEvents = skiddleService.getEventsByActivityType(activityType);

        return new ResponseEntity<>(filteredSkiddleEvents, HttpStatus.OK);

    }

}
