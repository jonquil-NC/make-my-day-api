package com.northcoders.makemydayapi.dto.activity.oneoff;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OneOffActivityResponseLocation {
    private double longitude;
    private double latitude;
}
