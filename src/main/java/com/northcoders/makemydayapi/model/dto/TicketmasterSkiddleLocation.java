package com.northcoders.makemydayapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketmasterSkiddleLocation {
    double longitude;
    double latitude;
}
