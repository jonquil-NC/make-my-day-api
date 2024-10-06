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
import java.util.Map;
import java.util.stream.Collectors;

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

        Map<ResourceType, List<OneOffActivityType>> activityTypesByResourceType = activityTypes.stream()
                .collect(Collectors.groupingBy(oneOffActivityType -> oneOffActivityType.resourceType));

        List<OneOffActivityType> skiddleActivityTypes = activityTypesByResourceType.get(ResourceType.SKIDDLE);


        List<OneOffActivityType> ticketmasterActivityTypes = activityTypesByResourceType.get(ResourceType.TICKETMASTER);
        log.info("Received {} Skiddle, {} Ticketmaster activity types",
                skiddleActivityTypes.size(),
                ticketmasterActivityTypes.size()
        );

        if (!skiddleActivityTypes.isEmpty()) {
            // Multiple Requests with 1 activity type
            skiddleActivityTypes.forEach(oneOffActivityType -> {
                List<TicketmasterSkiddleActivity> skiddleEvents = skiddleService.getEventsByActivityType(oneOffActivityType);
                oneOffEvents.addAll(skiddleEvents);
            });
        }

        if (!ticketmasterActivityTypes.isEmpty()) {
            // One Request with N activity types
            List<TicketmasterSkiddleActivity> ticketmasterEvents = ticketmasterService.getEventsByActivityTypes(ticketmasterActivityTypes);
            oneOffEvents.addAll(ticketmasterEvents);
        }


        log.info("Fetched {} one-off activities", oneOffEvents.size());

        if (oneOffEvents.isEmpty()) {
            return List.of();
        }

        return oneOffEvents;
    }
}
