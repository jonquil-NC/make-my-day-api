package com.northcoders.makemydayapi.model.activity.oneoff;

import com.northcoders.makemydayapi.model.activity.Activity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;

@Entity
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OneOffActivity extends Activity {

    @Column(nullable = false)
    OneOffActivityType activityType;

    String startTime;
    String date;
    String price;

}
