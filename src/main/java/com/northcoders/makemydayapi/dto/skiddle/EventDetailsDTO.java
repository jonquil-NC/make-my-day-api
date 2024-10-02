package com.northcoders.makemydayapi.dto.skiddle;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventDetailsDTO {

        private String eventName;
        private String date;
        private String venueName;
        private String entryPrice;

}
