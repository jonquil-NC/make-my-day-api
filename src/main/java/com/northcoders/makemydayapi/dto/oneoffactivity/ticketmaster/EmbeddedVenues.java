package com.northcoders.makemydayapi.dto.oneoffactivity.ticketmaster;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class EmbeddedVenues {
    private List<Venue> venues;

}
