package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.dto.skiddle.SkiddleEvent;
import com.northcoders.makemydayapi.model.Activity;

import java.util.List;

public interface SkiddleService {

    List<Activity> getAllEvents();

//    void populateDatabase();
}
