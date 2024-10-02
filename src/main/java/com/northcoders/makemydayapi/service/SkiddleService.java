package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.Activity;

import java.time.LocalDate;
import java.util.List;

public interface SkiddleService {

    List<Activity> getAllEvents();
    List<Activity> getEventsByDate(LocalDate date);
    List<Activity> getEventsByVenue(String venueName);
    List<Activity> getEventsByPrice(String price);


}
