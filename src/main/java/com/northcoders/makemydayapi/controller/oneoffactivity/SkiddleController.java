package com.northcoders.makemydayapi.controller.oneoffactivity;


import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.dto.activity.oneoff.OneOffActivityResponse;
import com.northcoders.makemydayapi.service.oneoffactivity.SkiddleService;
import com.northcoders.makemydayapi.dto.oneoffactivity.TicketmasterSkiddleActivity;
import com.northcoders.makemydayapi.service.oneoffactivity.SkiddleService;
import com.northcoders.makemydayapi.validation.constraints.ValidSkiddleActivityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/skiddle")
@Validated
public class SkiddleController {

    @Autowired
    private SkiddleService skiddleService;

    @GetMapping("/events")
    public CompletableFuture<ResponseEntity<List<OneOffActivityResponse>>> getEventsByActivityType(@RequestParam @ValidSkiddleActivityType OneOffActivityType activityType) {

        CompletableFuture<List<OneOffActivityResponse>> filteredSkiddleEvents = skiddleService.getEventsByActivityType(activityType);

        return filteredSkiddleEvents.thenApply(events -> new ResponseEntity<>(events, HttpStatus.OK));

    }

}
