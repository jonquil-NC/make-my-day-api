package com.northcoders.makemydayapi.dto.skiddle;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class SkiddleEventsResult {
    private int error;
    private int totalcount;
    private int pagecount;
    private List<SkiddleEvent> results;

}
