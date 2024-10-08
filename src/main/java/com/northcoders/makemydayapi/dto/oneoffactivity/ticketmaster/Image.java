package com.northcoders.makemydayapi.dto.oneoffactivity.ticketmaster;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class Image {
    private String ratio;
    private String url;
    private int width;
    private int height;

}
