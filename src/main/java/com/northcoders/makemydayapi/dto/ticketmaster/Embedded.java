package com.northcoders.makemydayapi.dto.ticketmaster;

import java.util.List;

public class Embedded {

    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
