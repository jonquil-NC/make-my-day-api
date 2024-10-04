package com.northcoders.makemydayapi.dto.skiddle;


import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(value = "EventCode")
    private String eventCode;
    private LocalDate date;
    private ZonedDateTime startdate;
    private ZonedDateTime enddate;

}
