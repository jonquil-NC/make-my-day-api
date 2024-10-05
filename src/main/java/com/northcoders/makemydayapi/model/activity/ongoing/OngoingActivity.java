package com.northcoders.makemydayapi.model.activity.ongoing;

import com.northcoders.makemydayapi.dto.OngoingActivityFieldsService;
import com.northcoders.makemydayapi.model.activity.Activity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OngoingActivity extends Activity  {

    @Column(nullable = false)
    OngoingActivityType activityType;

    String phoneNumber;
    String openingHours;
    Double rating;

    public OngoingActivity(String name, String address, String imageUrl, boolean isOutdoor,
                   OngoingActivityType activityType, String phoneNumber, String openingHours, Double rating) {
        super(name, address, imageUrl, isOutdoor);
        this.activityType = activityType;
        this.phoneNumber = phoneNumber;
        this.openingHours = openingHours;
        this.rating = rating;
    }
}
