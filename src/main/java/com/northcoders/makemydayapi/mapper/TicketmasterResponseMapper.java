package com.northcoders.makemydayapi.mapper;

import com.northcoders.makemydayapi.dto.ticketmaster.Event;
import com.northcoders.makemydayapi.dto.ticketmaster.enums.TicketmasterSegment;
import com.northcoders.makemydayapi.model.Activity;
import com.northcoders.makemydayapi.model.ActivityType;
import com.northcoders.makemydayapi.model.Location;
import com.northcoders.makemydayapi.model.ResourceType;

import java.time.LocalDate;
import java.time.LocalTime;

//import static com.northcoders.makemydayapi.mapper.SkiddleResponseMapper.getActivityType;

public class TicketmasterResponseMapper {
//    private static final String LONDON_LAT = "51.5074";
//    private static final String LONDON_LON = "-0.1278";

    public final static Activity toEntity(Event ticketmasterEvent) {
        Location venueLocation = Location.builder()
                .latitude(ticketmasterEvent.getEmbeddedVenues().getVenues().getFirst().getLocation().getLatitude())
                .latitude(ticketmasterEvent.getEmbeddedVenues().getVenues().getFirst().getLocation().getLongitude())
                .build();

        Activity activity = Activity.builder()
//                .id()
                .name(ticketmasterEvent.getName())
                .description(null)
//                .createdDate()
//                .updatedDate()
                .location(venueLocation)
                .isOutdoor(false) // ??
                .activityType(getActivityType(ticketmasterEvent.getClassifications().getFirst().getSegment().getSegmentName()))
                .price(null) // nullable
                .date(LocalDate.parse(ticketmasterEvent.getDates().getStart().getLocalDate()))
                .startTime(LocalTime.parse(ticketmasterEvent.getDates().getStart().getLocalTime()))
//                .endTime(LocalTime.parse(ticketmasterEvent.getDates().getEnd().getLocalTime()))
                .resourceType(ResourceType.TICKETMASTER)
                .build();

        return activity;
    }

    private static ActivityType getActivityType(String segmentName) {
        ActivityType activityType = null;


        if (segmentName.equals(TicketmasterSegment.Sports.getValue())) {
            activityType = ActivityType.Sports;
        } else if (segmentName.equals(TicketmasterSegment.Music.getValue())) {
            activityType = ActivityType.Music;
        } else if (segmentName.equals(TicketmasterSegment.Arts_N_Theatre.getValue())) {
            activityType = ActivityType.Arts_N_Theatre;
        } else {
            activityType = ActivityType.EVENT;
        }

        return activityType;
    }
}
