package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SkiddleService {

    CompletableFuture<List<TicketmasterSkiddleActivity>> getEventsByActivityType(OneOffActivityType activityType);

}
