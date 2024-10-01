package com.northcoders.makemydayapi.model;

import lombok.Builder;

@Builder
public class GeoapifyRestaurant {

    String name;
    String address;
    String imageUrl;
    String phoneNumber;
    String openingHours;

    public GeoapifyRestaurant(String name, String address, String imageUrl, String phoneNumber, String openingHours) {
        this.name = name;
        this.address = address;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.openingHours = openingHours;
    }
}
