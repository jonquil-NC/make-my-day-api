package com.northcoders.makemydayapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.makemydayapi.model.GeoapifyRestaurant;
import com.northcoders.makemydayapi.model.RestaurantType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    @Value("${api.key}")
    private String API_KEY;

    // St Paul's Cathedral
    private final String LATITUDE = "51.5138438";
    private final String LONGITUDE = "-0.13955";

    // 10 kilometre radius
    private String radius = "10000";
    private String resultsLimit = "300";

    @Autowired
    RestaurantsFilter restaurantsFilter;

    public List<GeoapifyRestaurant> getRestaurants(String type){

        List<GeoapifyRestaurant> restaurants = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        String endpoint = "https://api.geoapify.com/v2/places?categories=catering&filter=circle:"
                +LONGITUDE+","+LATITUDE+","+radius+"&limit="+resultsLimit+"&apiKey="+API_KEY;

        Request request = new Request.Builder()
                .url(endpoint)
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Content-Type", "application/json")
//                .addHeader("Accept", "application/json; q=0.5")
                .build();

        String jsonBodyString = null;

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Service 1) Http request executed");
            jsonBodyString = response.body().string();
        } catch (IOException e) {
            System.err.println("Error making http request " + e.getMessage());
            return restaurants;
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonBody = null;
        try {
            jsonBody = mapper.readTree(jsonBodyString);
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
                name = restaurant.get("name").asText();
                address = restaurant.get("formatted").asText();
                imageUrl = restaurant.get("datasource").get("raw").get("image").asText();
                phoneNumber = restaurant.get("datasource").get("raw").get("phone").asText();
                openingHours = restaurant.get("datasource").get("raw").get("openingHours").asText();
                restaurants.add(new GeoapifyRestaurant(name, address, imageUrl, phoneNumber, openingHours));
            }
        }
        return restaurants;
    }
}
