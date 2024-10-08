package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;

public interface OneOffActivityFieldsService extends ActivityFieldsService {
    OneOffActivityType getOneOffActivityType();
    String getStartTime();
    String getDate();
    String getPrice();
}
