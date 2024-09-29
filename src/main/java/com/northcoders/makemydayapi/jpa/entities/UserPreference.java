package com.northcoders.makemydayapi.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="users_preferences")
public class UserPreference {

    @Id
    @GeneratedValue
    Long id;

    EventType eventType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
