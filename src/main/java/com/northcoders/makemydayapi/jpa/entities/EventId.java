package com.northcoders.makemydayapi.jpa.entities;

// I use this class for a composite key as explained in here:
// https://www.baeldung.com/jpa-composite-primary-keys

public class EventId {
    SourceEvent sourceEvent;

    Long id;
}
