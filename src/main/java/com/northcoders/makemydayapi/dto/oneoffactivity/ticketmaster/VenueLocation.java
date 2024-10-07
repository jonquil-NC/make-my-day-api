package com.northcoders.makemydayapi.dto.oneoffactivity.ticketmaster;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class VenueLocation {
    private String latitude;
    private String longitude;
}