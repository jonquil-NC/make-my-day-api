package com.northcoders.makemydayapi.dto.oneoffactivity.ticketmaster.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TicketmasterSegment {

    Sports("KZFzniwnSyZfZ7v7nE", "Sports"),
    Music("KZFzniwnSyZfZ7v7nJ", "Music"),
    Arts_N_Theatre("KZFzniwnSyZfZ7v7na", "Arts & Theatre");

    private String id;
    private String value;

}