package com.northcoders.makemydayapi.dto.geoapify;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.northcoders.makemydayapi.dto.ActivityFieldsService;
import com.northcoders.makemydayapi.dto.OngoingActivityFieldsService;
import com.northcoders.makemydayapi.model.activity.ongoing.OngoingActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant implements OngoingActivityFieldsService {

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

    OngoingActivityType ongoingActivityType;

    public Restaurant(String name, String address, String imageUrl, String phoneNumber, String openingHours) {
        this.name = name;
        this.address = address;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.openingHours = openingHours;
        this.ongoingActivityType = OngoingActivityType.RESTAURANT;
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean getIsOutdoor() {
        return false;
    }

    @Override
    public OngoingActivityType getOngoingActivityType() {
        return ongoingActivityType;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getOpeningHours() {
        return openingHours;
    }

    @Override
    public Double getRating() {
        return null;
    }
}
