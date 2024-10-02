package com.northcoders.makemydayapi.mapper;

import com.northcoders.makemydayapi.dto.skiddle.SkiddleEvent;
import com.northcoders.makemydayapi.dto.skiddle.SkiddleEventsResult;
import com.northcoders.makemydayapi.model.Activity;
import com.northcoders.makemydayapi.model.ActivityType;
import com.northcoders.makemydayapi.model.Location;
import com.northcoders.makemydayapi.model.ResourceType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class SkiddleResponseMapper {
    public final static Activity toEntity(SkiddleEvent skiddleEvent) {
        Location venueLocation = Location.builder()
                .latitude(skiddleEvent.getVenue().getLatitude())
                .longitude(skiddleEvent.getVenue().getLongitude())
                .build();

        Activity activity = Activity.builder()
//                .id()
//                .apiId(skiddleEvent.getId())
                .name(skiddleEvent.getEventname())
                .description(skiddleEvent.getDescription())
//                .createdDate()
//                .updatedDate()
                .location(venueLocation)
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

        return activity;
    }

    private static ActivityType getActivityType(String eventCode) {
        ActivityType activityType = null;

        switch (eventCode) {
            case "SPORT" -> activityType = ActivityType.SPORTS;
            case "ARTS" -> activityType = ActivityType.ARTS;
            case "FEST" -> activityType = ActivityType.FESTIVAL;
            case "LIVE" -> activityType = ActivityType.LIVE;
            case "CLUB" -> activityType = ActivityType.CLUB;
            case "DATE" -> activityType = ActivityType.DATING;
            case "THEATRE" -> activityType = ActivityType.THEATRE;
            case "COMEDY" -> activityType = ActivityType.COMEDY;
            case "EXHIB" -> activityType = ActivityType.EXHIBITION;
            case "KIDS" -> activityType = ActivityType.KIDS;
            case "BARPUB" -> activityType = ActivityType.BAR;
            case "LGB" -> activityType = ActivityType.LGBT;
            default -> activityType = ActivityType.EVENT;
        }

        return activityType;
    }
}
