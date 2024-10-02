package com.northcoders.makemydayapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.makemydayapi.model.GeoapifyRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    @Value("classpath:geoapify-restaurants.json")
    Resource geoapifyRestaurants;

    @Autowired
    RestaurantsFilter restaurantsFilter;

    public List<GeoapifyRestaurant> getRestaurants(String type){

        List<GeoapifyRestaurant> restaurants = new ArrayList<>();

        String jsonBodyString = null;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonBody = null;
        try {
            jsonBody = mapper.readTree(geoapifyRestaurants.getInputStream());
        } catch (Exception e) {
            System.err.println("Error mapping Json response: " + e.getMessage());
            return restaurants;
        }

        if (jsonBody != null) {
            JsonNode jsonRestaurants = jsonBody.get("features");

            List<JsonNode> filteredRestaurants = restaurantsFilter.process(type, jsonRestaurants);
            String name, address, imageUrl, phoneNumber, openingHours;

            System.out.println("Service 2) Instantiating the restaurants");

            for (JsonNode restaurant : filteredRestaurants) {
                address=null;
                name = restaurant.get("name").asText();
                JsonNode auxiliaryData = restaurant.get("datasource").get("raw");

                System.out.println("Service 3) For filter " + type + " got " + name);

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
                GeoapifyRestaurant geoapifyRestaurant = new GeoapifyRestaurant(
                        name, address, imageUrl, phoneNumber, openingHours
                );
                System.out.println(geoapifyRestaurant);
                restaurants.add(geoapifyRestaurant);
            }
        }
        return restaurants;
    }
}
