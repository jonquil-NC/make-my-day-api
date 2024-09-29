package com.northcoders.makemydayapi.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity(name = "events")
@IdClass(EventId.class)
public class Event {

    @Id
    String id;
    @Id
    SourceEvent sourceEvent;

    String name;
    String description;
    EventType type;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    Double price;
    String url;
    String imageUrl;
    Double longitude;
    Double latitude;

}
