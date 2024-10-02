package com.northcoders.makemydayapi.dto.skiddle;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@Getter
@NoArgsConstructor
public class SkiddleEvent {

    private String id;
    private String eventname;
    private String description;
    private SkiddleVenue venue;
    private String imageurl;
    private String largeimageurl;
    private String xlargeimageurl;
    private String xlargeimageurlWebP;
    private String link;
    private String EventCode;
    private LocalDate date;
    private ZonedDateTime startdate;
    private ZonedDateTime enddate;

    //    added entryPrice to be able to filter by price
    private String entryPrice;

public String getEventDetails() {

    String venueName = "Venue unavailable";
    String price = "Price unavailable";
    String eventDate = "Date unavailable";

    if (venue != null) {
        venueName = venue.getName();
    }

    if (price != null) {
        price = entryPrice;
    }

    if(eventDate != null) {
        eventDate = date.toString();
    }

    return String.format("Event: %s\nDate: %s\nVenue: %s\nEntry Price: %s",
            eventname,
            eventDate,
            venueName,
            price);

}

}
