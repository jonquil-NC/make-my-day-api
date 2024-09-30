package com.northcoders.makemydayapi.dto.skiddle;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
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
