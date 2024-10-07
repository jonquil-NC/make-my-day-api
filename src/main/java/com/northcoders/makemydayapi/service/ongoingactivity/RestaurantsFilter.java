package com.northcoders.makemydayapi.service.ongoingactivity;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantsFilter {

    public boolean filter(String type, JsonNode info) {
        String cateringField = info.asText();

        if (cateringField.contains(type)){return true;}
        else if(type.equals("japanese") && (cateringField.contains("sushi") || cateringField.contains("ramen"))) {
            return true;
        } else if (type.equals("steak") && cateringField.contains("steak_house")) {
            return true;
        } else if (type.equals("italian") && cateringField.contains("pizza")){
            return true;
        } else if (type.equals("world_cuisine") && (
                cateringField.contains("mexican") ||
                cateringField.contains("peruvian") ||
                cateringField.contains("german") ||
                cateringField.contains("middle_eastern") ||
                cateringField.contains("lebanese") ||
                cateringField.contains("mediterranean") ||
                cateringField.contains("tapas") ||
                cateringField.contains("french") ||
                cateringField.contains("fish") ||
                cateringField.contains("spanish") ||
                cateringField.contains("seafood"))
        ) return true;
        else if (type.equals("burger") && cateringField.contains("restaurant.burger")) {
            return true;
        }
        else if (type.equals("cafe") && cateringField.contains("coffee_shop")){
            return true;
        }
        return false;
    }

    public List<JsonNode> process(String type, JsonNode jsonRestaurants) {
        System.out.println("Filter 1) Json processing");

        List<JsonNode> filteredRestaurants = new ArrayList<>();

        for (JsonNode jsonRestaurant : jsonRestaurants) {

            JsonNode restaurantBody = jsonRestaurant.get("properties");

            boolean venueIsBar = false;
            JsonNode categoriesField = restaurantBody.get("categories");
            for (JsonNode jsonCategory : categoriesField) {
                if (jsonCategory.asText().equals("category.bar")) {
                    venueIsBar = true;
                }
            }

            if (!venueIsBar) {

                JsonNode cuisineField = null;
                boolean satisfiesSearchQuery = false;
                try {
                    cuisineField = jsonRestaurant.get("catering").get("cuisine");
                } catch (NullPointerException e) {;}
                if (cuisineField == null) {
                    try {
                        cuisineField = jsonRestaurant.get("catering").get("");
                    } catch (NullPointerException e) {;}
                }
                if (cuisineField != null) {
                    satisfiesSearchQuery = filter(type, cuisineField);
                } else {
                    int index = 0;
                    while (!satisfiesSearchQuery && index < categoriesField.size()) {
                        satisfiesSearchQuery = filter(type, categoriesField.get(index));
                        index++;
                    }
                }
                if (satisfiesSearchQuery) filteredRestaurants.add(restaurantBody);
            }
        }
        return filteredRestaurants;
    }
}
