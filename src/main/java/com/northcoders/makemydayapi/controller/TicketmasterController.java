package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.model.Event;
import com.northcoders.makemydayapi.service.TicketmasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/ticketmaster")
public class TicketmasterController {

    @Autowired
    TicketmasterService service;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents(){
        return new ResponseEntity<>(service.getAllEvents(), HttpStatus.OK);
    }

}
