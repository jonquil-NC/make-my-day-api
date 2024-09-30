package com.northcoders.makemydayapi.mapper;

import com.northcoders.makemydayapi.dto.skiddle.SkiddleEventsResult;
import com.northcoders.makemydayapi.jpa.entities.SourceEvent;
import com.northcoders.makemydayapi.model.Activity;

import java.util.List;

public class SkiddleResponseMapper {
    public final static Activity toEntity(SkiddleEventsResult eventsResult) {
        Activity activity = Activity.builder()
                .build();

        return activity;
    }
}
