package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.dto.geoapify.Restaurant;
import com.northcoders.makemydayapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/geoapify/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("{type}")
    public ResponseEntity<List<Restaurant>> getGeoapifyRestaurants(@PathVariable String type) {
        return new ResponseEntity<>(restaurantService.getRestaurants(type), HttpStatus.OK);
    }

}
