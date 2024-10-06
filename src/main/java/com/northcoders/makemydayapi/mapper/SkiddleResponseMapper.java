package com.northcoders.makemydayapi.mapper;

import com.northcoders.makemydayapi.dto.skiddle.SkiddleEvent;
import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.activity.oneoff.ResourceType;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleLocation;

public class SkiddleResponseMapper {
    public final static TicketmasterSkiddleActivity toEntity(SkiddleEvent skiddleEvent) {
        TicketmasterSkiddleLocation venueTicketmasterSkiddleLocation = TicketmasterSkiddleLocation.builder()
                .latitude(skiddleEvent.getVenue().getLatitude())
                .longitude(skiddleEvent.getVenue().getLongitude())
                .build();

        TicketmasterSkiddleActivity ticketmasterSkiddleActivity = TicketmasterSkiddleActivity.builder()
//                .id()
//                .apiId(skiddleEvent.getId())
                .resourceType(ResourceType.SKIDDLE)
                .activityType(getActivityType(skiddleEvent.getEventCode()))
                .name(skiddleEvent.getEventname())
                .description(skiddleEvent.getDescription())
//                .createdDate()
//                .updatedDate()
                .ticketmasterSkiddleLocation(venueTicketmasterSkiddleLocation)
                .isOutdoor(false)
                .price(null) // nullable
                .date(skiddleEvent.getDate())
                .startTime(skiddleEvent.getStartdate().toLocalTime())
                .endTime(skiddleEvent.getEnddate().toLocalTime())
//                .imageUrl(skiddleEvent.getImageurl())
                .build();

        return ticketmasterSkiddleActivity;
    }

    private static OneOffActivityType getActivityType(String eventCode) {
        OneOffActivityType activityType = null;

        if (eventCode == null) {
            return OneOffActivityType.EVENT;
        }

        switch (eventCode) {
            case "FEST" -> activityType = OneOffActivityType.FEST;
            case "LIVE" -> activityType = OneOffActivityType.LIVE;
            case "CLUB" -> activityType = OneOffActivityType.CLUB;
            case "DATE" -> activityType = OneOffActivityType.DATE;
            case "THEATRE" -> activityType = OneOffActivityType.THEATRE;
            case "COMEDY" -> activityType = OneOffActivityType.COMEDY;
            case "EXHIB" -> activityType = OneOffActivityType.EXHIB;
            case "KIDS" -> activityType = OneOffActivityType.KIDS;
            case "BARPUB" -> activityType = OneOffActivityType.BARPUB;
            case "LGB" -> activityType = OneOffActivityType.LGB;
            case "SPORT" -> activityType = OneOffActivityType.SPORT;
            case "ARTS" -> activityType = OneOffActivityType.ARTS;
            default -> activityType = OneOffActivityType.EVENT;

        }

        return activityType;
    }
}
