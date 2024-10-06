package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    List<TicketmasterSkiddleActivity> getEventsByActivityTypes(List<OneOffActivityType> activityTypes);

}
