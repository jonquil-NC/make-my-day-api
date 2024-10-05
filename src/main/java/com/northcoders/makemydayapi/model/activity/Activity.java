package com.northcoders.makemydayapi.model.activity;

import com.northcoders.makemydayapi.dto.ActivityFieldsService;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String address;

    String imageUrl;
    boolean isOutdoor;

    public Activity(String name, String address, String imageUrl, boolean isOutdoor) {
        this.name = name;
        this.address = address;
        this.imageUrl = imageUrl;
        this.isOutdoor = isOutdoor;
    }

}
