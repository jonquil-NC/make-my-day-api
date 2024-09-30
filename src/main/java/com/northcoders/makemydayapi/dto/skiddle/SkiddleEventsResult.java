package com.northcoders.makemydayapi.model.skiddle;

import lombok.Data;

import java.util.List;

@Data
public class SkiddleEventsResult {
    private int error;
    private int totalcount;
    private int pagecount;
    private List<SkiddleEvent> results;


}
