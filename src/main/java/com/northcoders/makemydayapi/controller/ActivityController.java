package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.dto.geoapify.Restaurant;
import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.model.dto.TicketmasterSkiddleActivity;
import com.northcoders.makemydayapi.service.ActivityService;
import com.northcoders.makemydayapi.service.RestaurantService;
import com.northcoders.makemydayapi.service.SkiddleService;
import com.northcoders.makemydayapi.service.TicketmasterService;
import com.northcoders.makemydayapi.validation.constraints.ValidSkiddleActivityType;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/makemyday")
@Validated
public class ActivityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private SkiddleService skiddleService;

    @Autowired
    private TicketmasterService ticketmasterService;


    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/events")
    public ResponseEntity<List<TicketmasterSkiddleActivity>> getAllEventsByActivityType(@RequestParam("types") List<OneOffActivityType> activityTypeList) {

        LOGGER.info("Get all Events ticket master and skiddle, by type [{}]", activityTypeList);

        List<TicketmasterSkiddleActivity> bigList = new ArrayList<>();
        for(OneOffActivityType activityType : activityTypeList) {
            if(isSkiddleEvent(activityType)) {
                LOGGER.info("Get all Events from Skiddle by type [{}]", activityType);
                List<TicketmasterSkiddleActivity> eventTypeList = skiddleService.getEventsByActivityType(activityType);
                bigList.addAll(eventTypeList);
            }
        }

        //for(OneOffActivityType activityType : activityTypeList) {
        List<TicketmasterSkiddleActivity> ticketMasterList = ticketmasterService.getAllEvents();
        bigList.addAll(ticketMasterList);
        //}

        //List<TicketmasterSkiddleActivity> filteredSkiddleEvents = skiddleService.getEventsByActivityType(activityTypeList.getFirst());

        return ResponseEntity.ok(bigList);

    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurantByCuisineRestaurants(@RequestParam("types") List<String> cuisineTypeList){
        List<Restaurant> restaurantList = new ArrayList<>();

        for(String type : cuisineTypeList) {
            List<Restaurant> tmpList = restaurantService.getRestaurants(type);
            restaurantList.addAll(tmpList);
        }

        return ResponseEntity.ok(restaurantList);
    }


    private boolean isSkiddleEvent(OneOffActivityType activityType) {
        switch(activityType) {
            case SPORTS, MUSIC, CULTURAL, EVENT: return false;  //TicketMaster types
            default: return true;
        }
    }

}
