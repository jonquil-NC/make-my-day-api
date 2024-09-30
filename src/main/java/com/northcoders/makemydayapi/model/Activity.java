package com.northcoders.makemydayapi.model;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Builder
public class Activity {

    Long id;
    String apiId;
    String name;
    String description; //if it does not exist for all APIs create a custom one based on the type of activity
    Date createdDate;
    Date updatedDate;
    Location location;
    boolean isOutdoor;
    ActivityType activityType;
    String price; // nullable
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    ResourceType resourceType;
    String imageUrl;
}
