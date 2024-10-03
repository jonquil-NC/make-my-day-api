package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.Activity;
import com.northcoders.makemydayapi.model.ActivityType;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TicketmasterService {

//    List<Activity> getAllEvents();

    List<Activity> getEventsByActivityType(ActivityType activityType);

}
