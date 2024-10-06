package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.dto.geoapify.Restaurant;
import com.northcoders.makemydayapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurantByCuisineRestaurants(@RequestParam("types") List<String> cuisineTypeList){
        List<Restaurant> restaurantList = new ArrayList<>();

        for(String type : cuisineTypeList) {
            List<Restaurant> tmpList = restaurantService.getRestaurants(type);
            restaurantList.addAll(tmpList);
        }

        return ResponseEntity.ok(restaurantList);
    }

}
