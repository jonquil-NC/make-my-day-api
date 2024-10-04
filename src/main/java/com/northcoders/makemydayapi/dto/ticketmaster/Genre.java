package com.northcoders.makemydayapi.dto.ticketmaster;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class Genre {
    private String id;
    @JsonProperty(value = "name")
    private String genreName;
}
