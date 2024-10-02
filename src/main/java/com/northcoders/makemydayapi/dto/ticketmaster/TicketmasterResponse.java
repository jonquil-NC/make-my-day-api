package com.northcoders.makemydayapi.dto.ticketmaster;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.util.List;

public class TicketmasterResponse {

    private Embedded _embedded;

    public Embedded get_embedded() {
        return _embedded;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }

}
