package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface TicketmasterService {

    CompletableFuture<List<TicketmasterSkiddleActivity>> getEventsByActivityType(OneOffActivityType activityType);

    CompletableFuture<List<TicketmasterSkiddleActivity>> getEventsByActivityTypes(List<OneOffActivityType> activityTypes);

}
