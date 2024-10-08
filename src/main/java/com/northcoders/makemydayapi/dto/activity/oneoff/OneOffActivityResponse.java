package com.northcoders.makemydayapi.dto.activity.oneoff;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.activity.oneoff.ResourceType;
import com.northcoders.makemydayapi.service.OneOffActivityFieldsService;
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
public class OneOffActivityResponse implements OneOffActivityFieldsService {

//    @JsonProperty(value = "id")
//    private Long id;
    
    @JsonProperty(value = "resource_type")
    private ResourceType resourceType;

    @JsonProperty(value = "activity_type")
    private OneOffActivityType activityType;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description; //if it does not exist for all APIs create a custom one based on the type of activity

//    @JsonProperty(value = "created_date")
//    private Date createdDate;
//
//    @JsonProperty(value = "updated_date")
//    private Date updatedDate;

    @JsonProperty(value = "is_outdoor")
    private boolean isOutdoor;

    @JsonProperty(value = "venue")
    private OneOffActivityResponseVenue venue;

    @JsonProperty(value = "location")
    private OneOffActivityResponseLocation oneOffActivityResponseLocation;

    @JsonProperty(value = "price")
    private String price;

    @JsonProperty(value = "date")
    private LocalDate date;

    @JsonProperty(value = "start_time")
    private LocalTime startTime;

    @JsonProperty(value = "end_time")
    private LocalTime endTime;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public OneOffActivityType getOneOffActivityType() {
        return activityType;
    }

    @Override
    public String getAddress() {
        return venue.getName()
                .concat(" ")
                .concat(venue.getAddress().replaceAll(venue.getName(), ""))
                .concat(" ")
                .concat(venue.getPostalCode())
                .replaceAll("  ", " ");
    }

    @Override
    public String getImageUrl() {
        return null;
    }

    @Override
    public boolean getIsOutdoor() {
        return isOutdoor;
    }

    @Override
    public String getStartTime(){
        return startTime.toString();
    };

    @Override
    public String getDate(){
        return date.toString();
    };

    @Override
    public String getPrice(){
        return price;
    };


}
