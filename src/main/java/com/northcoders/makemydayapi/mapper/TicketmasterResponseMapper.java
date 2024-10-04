package com.northcoders.makemydayapi.mapper;

import com.northcoders.makemydayapi.dto.ticketmaster.Event;
import com.northcoders.makemydayapi.model.Activity;
import com.northcoders.makemydayapi.model.ActivityType;
import com.northcoders.makemydayapi.model.Location;
import com.northcoders.makemydayapi.model.ResourceType;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketmasterResponseMapper {
    private static final String LONDON_LAT = "51.5074";
    private static final String LONDON_LON = "-0.1278";

    public final static Activity toEntity(Event ticketmasterEvent) {
        Location venueLocation = Location.builder()
                .latitude(Double.parseDouble(LONDON_LAT))
                .longitude(Double.parseDouble(LONDON_LON))
                .build();

        Activity activity = Activity.builder()
//                .id()
//                .apiId(ticketmasterEvent.getId())
                .name(ticketmasterEvent.getName())
                .description(null)
//                .createdDate()
//                .updatedDate()
                .location(venueLocation)
                .isOutdoor(false) // ??
                .activityType(getActivityType())
                .price(null) // nullable
                .date(LocalDate.parse(ticketmasterEvent.getDates().getStart().getLocalDate()))
                .startTime(LocalTime.parse(ticketmasterEvent.getDates().getStart().getLocalTime()))
//                .endTime(LocalTime.parse(ticketmasterEvent.getDates().getEnd().getLocalTime()))
                .resourceType(ResourceType.TICKETMASTER)
//                .imageUrl(skiddleEvent.getImageurl())
                .build();

        return activity;
    }

    private static ActivityType getActivityType() {
        return ActivityType.EVENT;
    }
}
