package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.Activity;
import com.northcoders.makemydayapi.model.ActivityType;

import java.util.List;

public interface SkiddleService {

    List<Activity> getEventsByActivityType(ActivityType activityType);

}
