package com.northcoders.makemydayapi.dto.user;

import java.util.List;

public class SearchParams {
    public boolean parks;
    public boolean wellness;
    public boolean landmarks;
    public List<String> restaurants;
    public List<String> liveEvents;

    public SearchParams() {
    }

    public SearchParams(
            boolean parks, boolean wellness, boolean landmarks,  List<String> restaurants, List<String> liveEvents
    ) {
        this.parks = parks;
        this.wellness = wellness;
        this.landmarks = landmarks;
        this.restaurants = restaurants;
        this.liveEvents = liveEvents;
    }

    public boolean isParks() {
        return parks;
    }

    public void setParks(boolean parks) {
        this.parks = parks;
    }

    public boolean isWellness() {
        return wellness;
    }

    public void setWellness(boolean wellness) {
        this.wellness = wellness;
    }

    public boolean isLandmarks() {
        return landmarks;
    }

    public void setLandmarks(boolean landmarks) {
        this.landmarks = landmarks;
    }

    public List<String> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<String> restaurants) {
        this.restaurants = restaurants;
    }

    public List<String> getLiveEvents() {
        return liveEvents;
    }

    public void setLiveEvents(List<String> liveEvents) {
        this.liveEvents = liveEvents;
    }
}
