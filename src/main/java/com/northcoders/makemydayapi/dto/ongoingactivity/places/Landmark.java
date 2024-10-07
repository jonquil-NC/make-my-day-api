package com.northcoders.makemydayapi.dto.ongoingactivity.places;

import com.northcoders.makemydayapi.model.activity.ongoing.OngoingActivityType;

public class Landmark extends Place {

    public Landmark(String name, String openingHours, String address, Double rating) {
        super(name, openingHours, address, rating);
        super.setOngoingActivityType(OngoingActivityType.LANDMARK);
    }
    @Override
    public boolean getIsOutdoor(){
        return true;
    }
}
