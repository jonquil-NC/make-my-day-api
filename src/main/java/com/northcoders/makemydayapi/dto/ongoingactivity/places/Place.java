package com.northcoders.makemydayapi.dto.ongoingactivity.places;

import com.northcoders.makemydayapi.service.OngoingActivityFieldsService;
import com.northcoders.makemydayapi.model.activity.ongoing.OngoingActivityType;

public abstract class Place implements OngoingActivityFieldsService {
    String name;
    String openingHours;
    String address;
    Double rating;
    OngoingActivityType ongoingActivityType;

    public Place(String name, String openingHours, String address, Double rating) {
        this.name = name;
        this.openingHours = openingHours;
        this.address = address;
        this.rating = rating;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public OngoingActivityType getOngoingActivityType() {
        return ongoingActivityType;
    }

    public void setOngoingActivityType(OngoingActivityType ongoingActivityType) {
        this.ongoingActivityType = ongoingActivityType;
    }

    @Override
    public String getImageUrl(){
        return null;
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }

}
