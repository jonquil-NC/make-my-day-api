package com.northcoders.makemydayapi.dto.geoapify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class Restaurant {

    @JsonProperty
    String name;
    @JsonProperty
    String address;
    @JsonProperty
    String imageUrl;
    @JsonProperty
    String phoneNumber;
    @JsonProperty
    String openingHours;

    public Restaurant(String name, String address, String imageUrl, String phoneNumber, String openingHours) {
        this.name = name;
        this.address = address;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.openingHours = openingHours;
    }

    @Override
    public String toString() {
        return "GeoapifyRestaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", openingHours='" + openingHours + '\'' +
                '}';
    }
}
