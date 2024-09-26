package com.northcoders.makemydayapi.model;


import lombok.Data;

import java.time.LocalDate;

@Data
public class SkiddleEvent {
    private Long id;
    private String eventname;
    private SkiddleVenue venue;
    private String imageurl;
    private String largeimageurl;
    private String xlargeimageurl;
    private String xlargeimageurlWebP;
    private String link;
    private LocalDate date;

}
