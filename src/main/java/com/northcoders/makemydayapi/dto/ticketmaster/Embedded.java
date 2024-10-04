package com.northcoders.makemydayapi.dto.ticketmaster;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class Embedded {

    private List<Event> events;

}
