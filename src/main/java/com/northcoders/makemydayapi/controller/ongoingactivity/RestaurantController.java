package com.northcoders.makemydayapi.controller.ongoingactivity;

import com.northcoders.makemydayapi.dto.ongoingactivity.geoapify.Restaurant;
import com.northcoders.makemydayapi.service.ongoingactivity.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/geoapify/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("{type}")
    public CompletableFuture<ResponseEntity<List<Restaurant>>> getGeoapifyRestaurants(@PathVariable String type) {
        CompletableFuture<List<Restaurant>> restaurants = restaurantService.getRestaurants(type);
        return restaurants.thenApply(r -> new ResponseEntity<>(r, HttpStatus.OK));
    }

}
