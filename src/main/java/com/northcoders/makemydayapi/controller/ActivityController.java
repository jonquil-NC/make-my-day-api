package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.dto.activity.oneoff.OneOffActivityResponse;
import com.northcoders.makemydayapi.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/activities")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @GetMapping
    public ResponseEntity<List<OneOffActivityResponse>> getEventsByActivityType(@RequestParam(value = "type") List<OneOffActivityType> activityType) {

        List<OneOffActivityResponse> filteredEvents = activityService.getEventsByActivityTypes(activityType);

        return new ResponseEntity<>(filteredEvents, HttpStatus.OK);

    }

}
