package com.northcoders.makemydayapi.model.skiddle;

import lombok.Data;

@Data
public class SkiddleVenue {

    private Long id;
    private String name;
    private String address;
    private String town;
    private String postcode;
    private String country;
    private Double latitude;
    private Double longitude;
    private String type;

}
