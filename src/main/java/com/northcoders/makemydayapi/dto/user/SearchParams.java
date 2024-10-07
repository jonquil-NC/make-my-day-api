package com.northcoders.makemydayapi.dto.user;

import java.util.List;

public class SearchParams {
    public boolean parks;
    public boolean wellness;
    public boolean landmarks;
    public List<String> restaurants;
    public List<String> liveEvents;

    public SearchParams(
            boolean parks, boolean wellness, boolean landmarks,  List<String> restaurants, List<String> liveEvents
    ) {
        this.parks = parks;
        this.wellness = wellness;
        this.landmarks = landmarks;
        this.restaurants = restaurants;
        this.liveEvents = liveEvents;
    }
}
