package com.northcoders.makemydayapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
