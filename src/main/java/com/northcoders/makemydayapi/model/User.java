package com.northcoders.makemydayapi.model;

import com.northcoders.makemydayapi.service.ActivityFieldsService;
import com.northcoders.makemydayapi.service.OngoingActivityFieldsService;
import com.northcoders.makemydayapi.model.activity.Activity;
import com.northcoders.makemydayapi.model.activity.ongoing.OngoingActivity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Activity> itinerary;

    public void addActivity(Activity activity){
        itinerary.add(activity);
    }

    public void deleteActivity(Long id){
        itinerary.removeIf(activity -> activity.getId().equals(id));
    }
}


