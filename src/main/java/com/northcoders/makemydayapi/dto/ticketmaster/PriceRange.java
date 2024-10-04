package com.northcoders.makemydayapi.dto.ticketmaster;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class PriceRange {
    private String type; // "standard"
    private String currency; // "GBP" -> Can be Currency enum value later
    private double min; // 55.0
    private double max; // 170.0
}
