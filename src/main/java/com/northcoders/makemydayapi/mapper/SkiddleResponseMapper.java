package com.northcoders.makemydayapi.mapper;

import com.northcoders.makemydayapi.dto.skiddle.SkiddleEvent;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;
import com.northcoders.makemydayapi.model.ActivityType;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleLocation;
import com.northcoders.makemydayapi.model.ResourceType;

public class SkiddleResponseMapper {
    public final static TicketmasterSkiddleActivity toEntity(SkiddleEvent skiddleEvent) {
        TicketmasterSkiddleLocation venueTicketmasterSkiddleLocation = TicketmasterSkiddleLocation.builder()
                .latitude(skiddleEvent.getVenue().getLatitude())
                .longitude(skiddleEvent.getVenue().getLongitude())
                .build();

        TicketmasterSkiddleActivity ticketmasterSkiddleActivity = TicketmasterSkiddleActivity.builder()
//                .id()
//                .apiId(skiddleEvent.getId())
                .name(skiddleEvent.getEventname())
                .description(skiddleEvent.getDescription())
//                .createdDate()
//                .updatedDate()
                .ticketmasterSkiddleLocation(venueTicketmasterSkiddleLocation)
                .isOutdoor(false)
                .activityType(skiddleEvent.getEventCode() == null
                        ? ActivityType.EVENT // true
                        : getActivityType(skiddleEvent.getEventCode()) // false
                )
                .price(null) // nullable
                .date(skiddleEvent.getDate())
                .startTime(skiddleEvent.getStartdate().toLocalTime())
                .endTime(skiddleEvent.getEnddate().toLocalTime())
                .resourceType(ResourceType.SKIDDLE)
//                .imageUrl(skiddleEvent.getImageurl())
                .build();

        return ticketmasterSkiddleActivity;
    }

    private static ActivityType getActivityType(String eventCode) {
        ActivityType activityType = null;

        switch (eventCode) {
            case "FEST" -> activityType = ActivityType.FEST;
            case "LIVE" -> activityType = ActivityType.LIVE;
            case "CLUB" -> activityType = ActivityType.CLUB;
            case "DATE" -> activityType = ActivityType.DATE;
            case "THEATRE" -> activityType = ActivityType.THEATRE;
            case "COMEDY" -> activityType = ActivityType.COMEDY;
            case "EXHIB" -> activityType = ActivityType.EXHIB;
            case "KIDS" -> activityType = ActivityType.KIDS;
            case "BARPUB" -> activityType = ActivityType.BARPUB;
            case "LGB" -> activityType = ActivityType.LGB;
            case "SPORT" -> activityType = ActivityType.SPORT;
            case "ARTS" -> activityType = ActivityType.ARTS;
            default -> activityType = ActivityType.EVENT;

        }

        return activityType;
    }
}
