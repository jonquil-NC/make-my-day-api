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
import java.util.concurrent.TimeUnit;

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

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.SECONDS)
                .build();

        String endpoint = "https://api.geoapify.com/v2/places?categories=catering&filter=circle:"
                +LONGITUDE+","+LATITUDE+","+radius+"&limit="+resultsLimit+"&apiKey="+API_KEY;

        Request request = new Request.Builder()
                .url(endpoint)
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Content-Type", "application/json")
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
