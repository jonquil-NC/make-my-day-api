package com.northcoders.makemydayapi.dto.ticketmaster;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class VenueAddress {

    @JsonProperty(value = "line1")
    private String info; // "Cambridge Circus, Shaftesbury Avenue"
}
