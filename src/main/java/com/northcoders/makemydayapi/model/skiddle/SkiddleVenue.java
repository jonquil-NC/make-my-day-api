package com.northcoders.makemydayapi.model.skiddle;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkiddleVenue {

    private Long id;
    private String name;
    private String address;
    private String town;
    private String postcode;
    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String type;

}
