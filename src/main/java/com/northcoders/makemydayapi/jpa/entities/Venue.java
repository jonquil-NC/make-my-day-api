package com.northcoders.makemydayapi.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data
@Entity(name = "venues")
@IdClass(VenueId.class)
public class Venue {

    @Id
    private String id;
    @Id
    private SourceEvent sourceEvent;

    private String postCode;
    private String name;
    private String address;

}
