package com.northcoders.makemydayapi.dto.oneoffactivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.activity.oneoff.ResourceType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketmasterSkiddleActivity {

    @JsonProperty(value = "id")
    Long id;
    
    @JsonProperty(value = "resource_type")
    ResourceType resourceType;

    @JsonProperty(value = "activity_type")
    OneOffActivityType activityType;

    @JsonProperty(value = "name")
    String name;

    @JsonProperty(value = "description")
    String description; //if it does not exist for all APIs create a custom one based on the type of activity

    @JsonProperty(value = "created_date")
    Date createdDate;

    @JsonProperty(value = "updated_date")
    Date updatedDate;

    @JsonProperty(value = "location")
    TicketmasterSkiddleLocation ticketmasterSkiddleLocation;

    @JsonProperty(value = "is_outdoor")
    boolean isOutdoor;

    @JsonProperty(value = "price")
    String price;

    @JsonProperty(value = "date")
    LocalDate date;

    @JsonProperty(value = "start_time")
    LocalTime startTime;

    @JsonProperty(value = "end_time")
    LocalTime endTime;

}
