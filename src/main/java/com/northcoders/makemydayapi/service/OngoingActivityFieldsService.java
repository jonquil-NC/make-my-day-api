package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.activity.ongoing.OngoingActivityType;

public interface OngoingActivityFieldsService extends ActivityFieldsService {
    OngoingActivityType getOngoingActivityType();
    String getPhoneNumber();
    String getOpeningHours();
    Double getRating();
}
