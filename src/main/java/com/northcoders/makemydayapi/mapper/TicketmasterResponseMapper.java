package com.northcoders.makemydayapi.mapper;

import com.northcoders.makemydayapi.dto.ticketmaster.Event;
import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleLocation;
import com.northcoders.makemydayapi.model.ResourceType;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketmasterResponseMapper {
    private static final String LONDON_LAT = "51.5074";
    private static final String LONDON_LON = "-0.1278";

    public final static TicketmasterSkiddleActivity toEntity(Event ticketmasterEvent) {
        TicketmasterSkiddleLocation venueTicketmasterSkiddleLocation = TicketmasterSkiddleLocation.builder()
                .latitude(Double.parseDouble(LONDON_LAT))
                .longitude(Double.parseDouble(LONDON_LON))
                .build();

        TicketmasterSkiddleActivity ticketmasterSkiddleActivity = TicketmasterSkiddleActivity.builder()
//                .id()
//                .apiId(ticketmasterEvent.getId())
                .name(ticketmasterEvent.getName())
                .description(null)
//                .createdDate()
//                .updatedDate()
                .ticketmasterSkiddleLocation(venueTicketmasterSkiddleLocation)
                .isOutdoor(false) // ??
                .activityType(getActivityType())
                .price(null) // nullable
                .date(LocalDate.parse(ticketmasterEvent.getDates().getStart().getLocalDate()))
                .startTime(LocalTime.parse(ticketmasterEvent.getDates().getStart().getLocalTime()))
//                .endTime(LocalTime.parse(ticketmasterEvent.getDates().getEnd().getLocalTime()))
                .resourceType(ResourceType.TICKETMASTER)
//                .imageUrl(skiddleEvent.getImageurl())
                .build();

        return ticketmasterSkiddleActivity;
    }

    private static OneOffActivityType getActivityType() {
        return OneOffActivityType.EVENT;
    }
}
