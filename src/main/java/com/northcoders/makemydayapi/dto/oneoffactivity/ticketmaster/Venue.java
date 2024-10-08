package com.northcoders.makemydayapi.dto.oneoffactivity.ticketmaster;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Data
@Getter
@NoArgsConstructor
public class Venue {
    private String name; // "Copper Box Arena, Queen Elizabeth Olympic Park"
    //    private String type; // "venue"
//    private String id; // "KovZ9177tC7"
//    private String locale; // "en-de"
    private String postalCode; // "E20 2ST"
    //    private String timezone; // "Europe/London"
//    private VenueCity city;
    @JsonProperty(value = "address")
    private VenueAddress venueAddress;

    @JsonProperty(value = "location")
    private VenueLocation venueLocation;

    @Data
    @Getter
    @NoArgsConstructor
    public static class VenueCity {
        private String name; // "London"
    }
}
