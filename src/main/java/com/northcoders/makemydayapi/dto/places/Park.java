package com.northcoders.makemydayapi.dto.places;

import com.northcoders.makemydayapi.dto.ActivityFieldsService;
import com.northcoders.makemydayapi.model.activity.ongoing.OngoingActivityType;

public class Park extends Place {
    public Park(String name, String openingHours, String address, Double rating) {
        super(name, openingHours, address, rating);
        super.setOngoingActivityType(OngoingActivityType.PARK);
    }
    @Override
    public boolean getIsOutdoor(){
        return true;
    }
}
