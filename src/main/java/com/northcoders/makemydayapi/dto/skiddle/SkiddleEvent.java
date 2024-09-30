package com.northcoders.makemydayapi.model.skiddle;


import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
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

}
