package com.northcoders.makemydayapi.mapper.activity;

import com.northcoders.makemydayapi.dto.oneoffactivity.ticketmaster.Event;
import com.northcoders.makemydayapi.dto.oneoffactivity.ticketmaster.PriceRange;
import com.northcoders.makemydayapi.dto.oneoffactivity.ticketmaster.VenueLocation;
import com.northcoders.makemydayapi.dto.oneoffactivity.ticketmaster.enums.TicketmasterSegment;
import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.activity.oneoff.ResourceType;
import com.northcoders.makemydayapi.dto.oneoffactivity.TicketmasterSkiddleActivity;
import com.northcoders.makemydayapi.dto.oneoffactivity.TicketmasterSkiddleLocation;

import java.time.LocalDate;
import java.util.List;

public class TicketmasterResponseMapper {
    private static final String LONDON_LAT = "51.5074";
    private static final String LONDON_LON = "-0.1278";

    public final static TicketmasterSkiddleActivity toEntity(Event ticketmasterEvent) {
        VenueLocation venueLocation = ticketmasterEvent.getEmbeddedVenues().getVenues().getFirst().getVenueLocation();

        TicketmasterSkiddleLocation eventLocation = TicketmasterSkiddleLocation.builder()
                .latitude(venueLocation == null ? Double.parseDouble(LONDON_LAT) : Double.parseDouble(venueLocation.getLatitude()))
                .longitude(venueLocation == null ? Double.parseDouble(LONDON_LON) : Double.parseDouble(venueLocation.getLongitude()))
                .build();

        TicketmasterSkiddleActivity activity = TicketmasterSkiddleActivity.builder()
//                .id()
                .resourceType(ResourceType.TICKETMASTER)
                .activityType(getActivityType(ticketmasterEvent.getClassifications().getFirst().getSegment().getSegmentName()))
                .name(ticketmasterEvent.getName())
                .description(null)
//                .createdDate()
//                .updatedDate()
                .ticketmasterSkiddleLocation(eventLocation)
                .isOutdoor(false) // ??
                .price(null) // nullable
                .date(LocalDate.parse(ticketmasterEvent.getDates().getStart().getLocalDate()))
//                .startTime(LocalTime.parse(ticketmasterEvent.getDates().getStart().getLocalTime()))
//                .endTime(LocalTime.parse(ticketmasterEvent.getDates().getEnd().getLocalTime()))
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
