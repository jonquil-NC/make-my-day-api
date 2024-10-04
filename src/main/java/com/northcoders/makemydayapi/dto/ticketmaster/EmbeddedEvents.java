package com.northcoders.makemydayapi.dto.ticketmaster;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class EmbeddedEvents {

    private List<Event> events;

}
