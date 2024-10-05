package com.northcoders.makemydayapi.model.dto;

import com.google.gson.annotations.SerializedName;
import com.northcoders.makemydayapi.model.ResourceType;
import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
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
public class TicketmasterSkiddleActivity {

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
    TicketmasterSkiddleLocation ticketmasterSkiddleLocation;

    @SerializedName("is_outdoor")
    boolean isOutdoor;

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

    @SerializedName("activity_type")
    OneOffActivityType activityType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public TicketmasterSkiddleLocation getTicketmasterSkiddleLocation() {
        return ticketmasterSkiddleLocation;
    }

    public void setTicketmasterSkiddleLocation(TicketmasterSkiddleLocation ticketmasterSkiddleLocation) {
        this.ticketmasterSkiddleLocation = ticketmasterSkiddleLocation;
    }

    public boolean isOutdoor() {
        return isOutdoor;
    }

    public void setOutdoor(boolean outdoor) {
        isOutdoor = outdoor;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
}
