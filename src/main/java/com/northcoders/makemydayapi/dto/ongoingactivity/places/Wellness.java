package com.northcoders.makemydayapi.dto.ongoingactivity.places;

import com.northcoders.makemydayapi.model.activity.ongoing.OngoingActivityType;

public class Wellness extends Place {
    public Wellness(String name, String openingHours, String address, Double rating) {
        super(name, openingHours, address, rating);
        super.setOngoingActivityType(OngoingActivityType.WELLNESS);

    }
    @Override
    public boolean getIsOutdoor(){
        return false;
    }
}
