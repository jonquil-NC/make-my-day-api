package com.northcoders.makemydayapi.controller;


import com.northcoders.makemydayapi.model.skiddle.SkiddleEvent;
import com.northcoders.makemydayapi.service.SkiddleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/skiddle")
public class SkiddedController {

    @Autowired
    private SkiddleService skiddleService;

    @GetMapping("/events")
    public ResponseEntity<List<SkiddleEvent>> getAllEvents() {
        try {
            List<SkiddleEvent> events = skiddleService.getAllEvents();
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}