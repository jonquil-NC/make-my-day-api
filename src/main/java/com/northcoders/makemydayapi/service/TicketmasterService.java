package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.dto.activity.oneoff.OneOffActivityResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface TicketmasterService {

    CompletableFuture<List<OneOffActivityResponse>> getEventsByActivityType(OneOffActivityType activityType);

    CompletableFuture<List<OneOffActivityResponse>> getEventsByActivityTypes(List<OneOffActivityType> activityTypes);

}
