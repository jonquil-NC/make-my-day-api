package com.northcoders.makemydayapi.mapper;

import com.northcoders.makemydayapi.dto.ticketmaster.Event;
import com.northcoders.makemydayapi.dto.ticketmaster.PriceRange;
import com.northcoders.makemydayapi.dto.ticketmaster.enums.TicketmasterSegment;
import com.northcoders.makemydayapi.model.ResourceType;
import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.activity.ongoing.OngoingActivityType;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleLocation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TicketmasterResponseMapper {
//    private static final String LONDON_LAT = "51.5074";
//    private static final String LONDON_LON = "-0.1278";

    public final static TicketmasterSkiddleActivity toEntity(Event ticketmasterEvent) {
        TicketmasterSkiddleLocation venueLocation = TicketmasterSkiddleLocation.builder()
                .latitude(Double.parseDouble(ticketmasterEvent.getEmbeddedVenues().getVenues().getFirst().getVenueLocation().getLatitude()))
                .longitude(Double.parseDouble(ticketmasterEvent.getEmbeddedVenues().getVenues().getFirst().getVenueLocation().getLongitude()))
                .build();

        TicketmasterSkiddleActivity activity = TicketmasterSkiddleActivity.builder()
//                .id()
                .name(ticketmasterEvent.getName())
                .description(null)
//                .createdDate()
//                .updatedDate()
                .ticketmasterSkiddleLocation(venueLocation)
                .isOutdoor(false) // ??
                .activityType(getActivityType(ticketmasterEvent.getClassifications().getFirst().getSegment().getSegmentName()))
                .price(null) // nullable
                .date(LocalDate.parse(ticketmasterEvent.getDates().getStart().getLocalDate()))
//                .startTime(LocalTime.parse(ticketmasterEvent.getDates().getStart().getLocalTime()))
//                .endTime(LocalTime.parse(ticketmasterEvent.getDates().getEnd().getLocalTime()))
                .resourceType(ResourceType.TICKETMASTER)
//                .imageUrl(skiddleEvent.getImageurl())
                .build();

        return activity;
    }

    private static OneOffActivityType getActivityType(String segmentName) {
        OneOffActivityType activityType = null;


        if (segmentName.equals(TicketmasterSegment.Sports.getValue())) {
            activityType = OneOffActivityType.SPORTS;
        } else if (segmentName.equals(TicketmasterSegment.Music.getValue())) {
            activityType = OneOffActivityType.MUSIC;
        } else if (segmentName.equals(TicketmasterSegment.Arts_N_Theatre.getValue())) {
            activityType = OneOffActivityType.CULTURAL;
        } else {
            activityType = OneOffActivityType.EVENT;
        }

        return activityType;
    }

    private static String getAveragePrice(List<PriceRange> priceRanges) {
        if (priceRanges == null || priceRanges.isEmpty()) {
            return null;
        }

        return String.valueOf((priceRanges.getFirst().getMax() - priceRanges.getFirst().getMin()) / 2);
    }
}
