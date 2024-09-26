package com.northcoders.makemydayapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Event {
    @JsonProperty
    String name;

    @JsonProperty
    String dateTime;

    @JsonProperty
    String imageUrl;

    @JsonProperty
    String venueName;

    public Event(String name, String dateTime, String imageUrl, String venueName) {
        this.name = name;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
        this.venueName = venueName;
    }
}
