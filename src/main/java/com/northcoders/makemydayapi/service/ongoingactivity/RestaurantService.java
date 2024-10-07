package com.northcoders.makemydayapi.service.ongoingactivity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.makemydayapi.dto.ongoingactivity.geoapify.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class RestaurantService {

    @Value("classpath:geoapify-restaurants.json")
    Resource geoapifyRestaurants;

    @Autowired
    RestaurantsFilter restaurantsFilter;

    @Async
    public CompletableFuture<List<Restaurant>> getRestaurants(String type){

        List<Restaurant> restaurants = new ArrayList<>();

        String jsonBodyString = null;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonBody = null;
        try {
            jsonBody = mapper.readTree(geoapifyRestaurants.getInputStream());
        } catch (Exception e) {
            System.err.println("Error mapping Json response: " + e.getMessage());
            return CompletableFuture.completedFuture(restaurants);
        }

        if (jsonBody != null) {
            JsonNode jsonRestaurants = jsonBody.get("features");

            List<JsonNode> filteredRestaurants = restaurantsFilter.process(type, jsonRestaurants);
            String name, address, imageUrl, phoneNumber, openingHours;

            for (JsonNode restaurant : filteredRestaurants) {
                address=null;
                name = restaurant.get("name").asText();
                JsonNode auxiliaryData = restaurant.get("datasource").get("raw");

                // The address may be found at different locations in the JSON
                try {
                    address = restaurant.get("formatted").asText()
                            .replace(", United Kingdom", "")
                            .replace("London, ", "");
                    if (address.contains(name)) address = address.replace(name+", ", "");
                } catch (NullPointerException e) {;}
                if (address==null){
                    try {
                        address = restaurant.get("street").asText()+", "+restaurant.get("postcode").asText();
                    } catch (NullPointerException e) {;}
                if (address==null){
                    try {
                        address = auxiliaryData.get("addr:street").asText()+", "+auxiliaryData.get("addr:postcode").asText();
                    } catch (NullPointerException e){;}
                }
                }

                try {
                    imageUrl = auxiliaryData.get("image").asText();
                } catch (NullPointerException e) {
                    imageUrl = null;
                }
                try {
                    phoneNumber = auxiliaryData.get("phone").asText();
                } catch (NullPointerException e) {
                    phoneNumber = null;
                }
                try {
                    openingHours = auxiliaryData.get("opening_hours").asText();
                } catch (NullPointerException e) {
                    openingHours = null;
                }
                Restaurant geoapifyRestaurant = new Restaurant(
                        name, address, imageUrl, phoneNumber, openingHours
                );
                restaurants.add(geoapifyRestaurant);
            }
        }
        return CompletableFuture.completedFuture(restaurants);
    }
}
