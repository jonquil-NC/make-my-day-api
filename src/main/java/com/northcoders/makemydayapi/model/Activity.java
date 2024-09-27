package com.northcoders.makemydayapi.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Activity {

    Long id;
    String name;
    String description; //if it does not exist for all APIs create a custom one based on the type of activity
    Date createdDate;
    Date updatedDate;
    Location location;
    boolean isOutdoor;
    ActivityType activityType;
    String price;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    ResourceType resourceType;
}
