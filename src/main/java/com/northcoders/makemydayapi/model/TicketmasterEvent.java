package com.northcoders.makemydayapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketmasterEvent {

    @JsonProperty
    String name;

    @JsonProperty
    String dateTime;

    @JsonProperty
    String imageUrl;

    @JsonProperty
    String venueName;

    public TicketmasterEvent(String name, String dateTime, String imageUrl, String venueName) {
        this.name = name;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
        this.venueName = venueName;
    }
}
