package com.northcoders.makemydayapi.dto.oneoffactivity.skiddle;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class SkiddleEventsResult {
    private int error;
    private int totalcount;
    private int pagecount;
    private List<SkiddleEvent> results;

}
