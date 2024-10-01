package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.model.GeoapifyRestaurant;
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
public class GeoapifyController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("{type}")
    public ResponseEntity<List<GeoapifyRestaurant>> getGeoapifyRestaurants(@PathVariable String type) {
        return new ResponseEntity<>(restaurantService.getRestaurants(type), HttpStatus.OK);
    }

}
