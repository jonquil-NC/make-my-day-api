package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.activity.oneoff.ResourceType;
import com.northcoders.makemydayapi.dto.activity.oneoff.OneOffActivityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    SkiddleService skiddleService;

    @Autowired
    TicketmasterService ticketmasterService;

    @Override
    public List<OneOffActivityResponse> getEventsByActivityTypes(List<OneOffActivityType> activityTypes) {
        if (activityTypes.isEmpty()) {
            return List.of();
        }

        Map<ResourceType, List<OneOffActivityType>> activityTypesByResourceType = activityTypes.stream()
                .collect(Collectors.groupingBy(oneOffActivityType -> oneOffActivityType.resourceType));

        List<OneOffActivityType> skiddleActivityTypes = activityTypesByResourceType.get(ResourceType.SKIDDLE);

        List<OneOffActivityType> ticketmasterActivityTypes = activityTypesByResourceType.get(ResourceType.TICKETMASTER);
        log.info("Received {} Skiddle, {} Ticketmaster activity types",
                skiddleActivityTypes.size(),
                ticketmasterActivityTypes.size()
        );

        // Async
        List<CompletableFuture<List<OneOffActivityResponse>>> futureEventLists = new ArrayList<>();

        if (!skiddleActivityTypes.isEmpty()) {
            // Multiple Requests with 1 activity type
            skiddleActivityTypes.forEach(oneOffActivityType -> {
                CompletableFuture<List<OneOffActivityResponse>> skiddleEvents = skiddleService.getEventsByActivityType(oneOffActivityType);

                List<CompletableFuture<List<OneOffActivityResponse>>> skiddleFutures =
                        List.of(skiddleEvents);

                futureEventLists.addAll(skiddleFutures);
            });
        }

        if (!ticketmasterActivityTypes.isEmpty()) {
            // One Request with N activity types
            CompletableFuture<List<OneOffActivityResponse>> ticketmasterEvents = ticketmasterService.getEventsByActivityTypes(ticketmasterActivityTypes);

            // Wrap the CompletableFuture into a collection
            List<CompletableFuture<List<OneOffActivityResponse>>> ticketmasterFutures =
                    List.of(ticketmasterEvents);

            futureEventLists.addAll(ticketmasterFutures);
        }

        // Wait for all async processes to complete
        List<OneOffActivityResponse> oneOffEvents = futureEventLists.stream()
                .map(CompletableFuture::join) // Waits for the result of each async call
                .flatMap(List::stream) // Combines all lists into a single stream
                .toList();

        log.info("Fetched {} one-off activities", oneOffEvents.size());

        if (oneOffEvents.isEmpty()) {
            return List.of();
        }

        return oneOffEvents;
    }
}
