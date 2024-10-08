package com.northcoders.makemydayapi.dto.oneoffactivity.ticketmaster;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@Getter
@NoArgsConstructor
public class TicketmasterResponse {

    @JsonProperty(value = "_embedded")
    private EmbeddedEvents embeddedEvents;

}
