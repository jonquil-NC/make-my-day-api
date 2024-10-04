package com.northcoders.makemydayapi.controller;


import com.northcoders.makemydayapi.model.Activity;
import com.northcoders.makemydayapi.model.ActivityType;
import com.northcoders.makemydayapi.service.SkiddleService;
import com.northcoders.makemydayapi.validation.constraints.ValidSkiddleActivityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/skiddle")
@Validated
public class SkiddleController {

    @Autowired
    private SkiddleService skiddleService;

    @GetMapping("/events")
    public ResponseEntity<List<Activity>> getEventsByActivityType(@RequestParam @ValidSkiddleActivityType ActivityType activityType) {

        List<Activity> filteredSkiddleEvents = skiddleService.getEventsByActivityType(activityType);

        return new ResponseEntity<>(filteredSkiddleEvents, HttpStatus.OK);

    }

}
