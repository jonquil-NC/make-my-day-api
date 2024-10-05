package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;
import com.northcoders.makemydayapi.model.ActivityType;

import java.util.List;

public interface SkiddleService {

    List<TicketmasterSkiddleActivity> getEventsByActivityType(ActivityType activityType);

}
