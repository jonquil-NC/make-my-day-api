package com.northcoders.makemydayapi.controller.oneoffactivity;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.dto.oneoffactivity.TicketmasterSkiddleActivity;
import com.northcoders.makemydayapi.service.oneoffactivity.TicketmasterService;
import com.northcoders.makemydayapi.dto.activity.oneoff.OneOffActivityResponse;
import com.northcoders.makemydayapi.service.oneoffactivity.TicketmasterService;
import com.northcoders.makemydayapi.validation.constraints.ValidTicketmasterActivityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/ticketmaster")
public class TicketmasterController {

    @Autowired
    TicketmasterService ticketmasterService;

    @GetMapping("/events")
    public CompletableFuture<ResponseEntity<List<OneOffActivityResponse>>> getEventsByActivityType(@RequestParam @ValidTicketmasterActivityType OneOffActivityType activityType) {

        CompletableFuture<List<OneOffActivityResponse>> filteredTicketmasterEvents = ticketmasterService.getEventsByActivityType(activityType);

        return filteredTicketmasterEvents.thenApply(events ->
                new ResponseEntity<>(events, HttpStatus.OK)
        );
    }
}
