package com.northcoders.makemydayapi.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity(name = "events")
@IdClass(EventId.class)
public class Event {

    @Id
    private String id;
    @Id
    private SourceEvent sourceEvent;

    private String name;
    private String description;
    private EventType type;    //EventCode in skiddle
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double price;
    private String url;
    private String imageUrl;
    private Double longitude;
    private Double latitude;

}
