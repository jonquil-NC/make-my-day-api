package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.dto.activity.oneoff.OneOffActivityResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    List<OneOffActivityResponse> getEventsByActivityTypes(List<OneOffActivityType> activityTypes);

}
