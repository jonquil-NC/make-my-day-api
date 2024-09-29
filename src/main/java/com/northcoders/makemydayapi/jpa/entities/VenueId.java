package com.northcoders.makemydayapi.jpa.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class VenueId implements Serializable {
    private String id;
    private SourceEvent sourceEvent;
}
