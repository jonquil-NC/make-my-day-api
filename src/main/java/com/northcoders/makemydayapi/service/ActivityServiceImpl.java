package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.mapper.SkiddleResponseMapper;
import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.activity.oneoff.ResourceType;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    SkiddleService skiddleService;

    @Autowired
    TicketmasterService ticketmasterService;

    @Override
    public List<TicketmasterSkiddleActivity> getEventsByActivityTypes(List<OneOffActivityType> activityTypes) {
        if (activityTypes.isEmpty()) {
            return List.of();
        }

        List<TicketmasterSkiddleActivity> oneOffEvents = new ArrayList<>();

        activityTypes.forEach(oneOffActivityType -> {
            if (oneOffActivityType.resourceType.equals(ResourceType.SKIDDLE)) {
                List<TicketmasterSkiddleActivity> skiddleEvents = skiddleService.getEventsByActivityType(oneOffActivityType);
                oneOffEvents.addAll(skiddleEvents);
            } else if (oneOffActivityType.resourceType.equals(ResourceType.TICKETMASTER)) {
                List<TicketmasterSkiddleActivity> ticketmasterEvents = ticketmasterService.getEventsByActivityType(oneOffActivityType);
                oneOffEvents.addAll(ticketmasterEvents);
            }
        });

        log.info("Fetched {} one-off activities", oneOffEvents.size());

        if (oneOffEvents.isEmpty()) {
            return List.of();
        }

        return oneOffEvents;
    }
}
