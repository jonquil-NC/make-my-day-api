package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.dto.activity.oneoff.OneOffActivityResponse;
import com.northcoders.makemydayapi.dto.ongoingactivity.geoapify.Restaurant;
import com.northcoders.makemydayapi.dto.ongoingactivity.places.Place;
import com.northcoders.makemydayapi.dto.user.SearchParams;
import com.northcoders.makemydayapi.model.activity.Activity;
import com.northcoders.makemydayapi.model.activity.ongoing.OngoingActivity;
import com.northcoders.makemydayapi.service.oneoffactivity.ActivityServiceImpl;
import com.northcoders.makemydayapi.service.ongoingactivity.PlacesService;
import com.northcoders.makemydayapi.service.ongoingactivity.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MainService {

    @Autowired
    PlacesService placesService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    ActivityServiceImpl activityServiceImpl;

    public OngoingActivity parseOngoingActivity(OngoingActivityFieldsService ongoingActivity){
        return new OngoingActivity(
                ongoingActivity.getName(),
                ongoingActivity.getAddress(),
                ongoingActivity.getImageUrl(),
                ongoingActivity.getIsOutdoor(),
                ongoingActivity.getOngoingActivityType(),
                ongoingActivity.getPhoneNumber(),
                ongoingActivity.getOpeningHours(),
                ongoingActivity.getRating()
        );
    }

    public List<Activity> getActivities(SearchParams searchParams) throws Exception {
        List<Activity> activities = new ArrayList<>();

        List<CompletableFuture<List<Place>>> futurePlaces = new ArrayList<>();
        List<CompletableFuture<List<Restaurant>>> futureRestaurants = new ArrayList<>();
        List<CompletableFuture<List<OneOffActivityResponse>>> futureEvents = new ArrayList<>();

        if (searchParams.parks){
            CompletableFuture<List<Place>> parks = placesService.getPlaces("parks");
            futurePlaces.add(parks);
        }
        if (searchParams.landmarks){
            CompletableFuture<List<Place>> landmarks = placesService.getPlaces("landmarks");
            futurePlaces.add(landmarks);
        }
        if (searchParams.wellness){
            CompletableFuture<List<Place>> wellness = placesService.getPlaces("wellness");
            futurePlaces.add(wellness);
        }
        if (searchParams.restaurants != null) {
            for (int i = 0; i < searchParams.restaurants.size(); i++) {
                futureRestaurants.add(restaurantService.getRestaurants(searchParams.restaurants.get(i)));
            }
        }
        if (searchParams.liveEvents != null){
            for (int i = 0; i < searchParams.liveEvents.size(); i++) {
                // TODO: create OneOffActivityType (enum) out of the string
                futureEvents.add(activityServiceImpl.getEventsByActivityTypes(null));
            }
        }

        List<Place> places = futurePlaces.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .toList();
        for (Place pl : places){
            activities.add(parseOngoingActivity(pl));
        }

        List<Restaurant> restaurants = futureRestaurants.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .toList();
        for (Restaurant r : restaurants){
            activities.add(parseOngoingActivity(r));
        }

        // TODO: make OneOffActivity interface and parse method

        return activities;
    }
}
