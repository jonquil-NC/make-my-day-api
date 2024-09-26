package com.northcoders.makemydayapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonProperty
    String name;

    @JsonProperty
    String dateTime;

    @JsonProperty
    String imageUrl;

    @JsonProperty
    String venueName;

    public Events(String name, String dateTime, String imageUrl, String venueName) {
        this.name = name;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
        this.venueName = venueName;
    }

    public Events() {}
}