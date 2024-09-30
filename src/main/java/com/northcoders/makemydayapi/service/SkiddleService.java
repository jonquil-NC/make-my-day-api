package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.dto.skiddle.SkiddleEvent;

import java.util.List;

public interface SkiddleService {

    List<SkiddleEvent> getAllEvents();

    void populateDatabase();
}
