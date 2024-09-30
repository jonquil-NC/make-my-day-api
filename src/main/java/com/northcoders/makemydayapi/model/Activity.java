package com.northcoders.makemydayapi.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Activity {

    @SerializedName("id")
    Long id;

    @SerializedName("name")
    String name;

    @SerializedName("description")
    String description; //if it does not exist for all APIs create a custom one based on the type of activity

    @SerializedName("created_date")
    Date createdDate;

    @SerializedName("updated_date")
    Date updatedDate;

    @SerializedName("location")
    Location location;

    @SerializedName("is_outdoor")
    boolean isOutdoor;

    @SerializedName("activity_type")
    ActivityType activityType;

    @SerializedName("price")
    String price;

    @SerializedName("date")
    LocalDate date;

    @SerializedName("start_time")
    LocalTime startTime;

    @SerializedName("end_time")
    LocalTime endTime;

    @SerializedName("resource_type")
    ResourceType resourceType;
}
