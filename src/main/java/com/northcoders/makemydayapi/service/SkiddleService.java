package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;

import java.util.List;

public interface SkiddleService {

    List<TicketmasterSkiddleActivity> getEventsByActivityType(OneOffActivityType activityType);

}
