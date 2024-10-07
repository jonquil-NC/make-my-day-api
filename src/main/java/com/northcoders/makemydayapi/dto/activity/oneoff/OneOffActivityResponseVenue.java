package com.northcoders.makemydayapi.dto.activity.oneoff;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OneOffActivityResponseVenue {
    private String name;
    private String address;

    @JsonProperty(value = "postal_code")
    private String postalCode;
}
