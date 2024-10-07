package com.northcoders.makemydayapi.service.ongoingactivity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.northcoders.makemydayapi.dto.ongoingactivity.places.Landmark;
import com.northcoders.makemydayapi.dto.ongoingactivity.places.Park;
import com.northcoders.makemydayapi.dto.ongoingactivity.places.Place;
import com.northcoders.makemydayapi.dto.ongoingactivity.places.Wellness;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

@Service
public class PlacesService {

         String API_KEY;
         Properties properties = new Properties();
         OkHttpClient client;
         ObjectMapper mapper;
         String placeType;

         @Async
        public CompletableFuture<List<Place>> getPlaces(String query) throws Exception{
            placeType = query;
            String search_query = "\""+placeType+" London\"";

            try (InputStream inStr = new FileInputStream("src/main/resources/app.properties")){
                properties.load(inStr);
                API_KEY = properties.getProperty("google.api.key");
            } catch (IOException e){
                System.err.println("No such API key");
            }

            if (API_KEY != null) {
                client = new OkHttpClient();

                RequestBody requestBody = RequestBody.create(
                        MediaType.get("application/json"), "{\"textQuery\": " + search_query + "}"
                );

                Request request = new Request.Builder()
                        .url("https://places.googleapis.com/v1/places:searchText")
                        .post(requestBody)
                        .header("Content-Type", "application/json")
                        .addHeader("X-Goog-Api-Key", API_KEY)
                        .addHeader("X-Goog-FieldMask", "*")
                        .build();

                mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                String jsonResponse = "";
                try (Response response = client.newCall(request).execute()) {
                    jsonResponse = response.body().string();
                } catch (Exception e) {
                    System.err.println("Error getting the places.");
                }

                if (!jsonResponse.equals("")) {
                    return deserialiseJsonResponse(jsonResponse);
                } else {
                    return CompletableFuture.completedFuture(List.of());
                }
            }
            else {
                return readFromCsv();
            }
        }

    @Value("classpath:landmarks.csv")
    Resource landmarksCsv;

    @Value("classpath:parks.csv")
    Resource parksCsv;

    @Value("classpath:wellness.csv")
    Resource wellnessCsv;

    protected CompletableFuture<List<Place>> readFromCsv() {
        List<Place> places = new ArrayList<>();
        Scanner scanner = null;

        try {
            switch (placeType){
                case "landmarks":
                    scanner = new Scanner(landmarksCsv.getInputStream());
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] values = line.split(",");
                        places.add(new Landmark(values[0], values[1], values[2], Double.parseDouble(values[3])));
                    }
                    break;
                case "parks":
                    scanner = new Scanner(parksCsv.getInputStream());
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] values = line.split(",");
                        places.add(new Park(values[0], values[1], values[2], Double.parseDouble(values[3])));
                    }
                    break;
                case "wellness":
                    scanner = new Scanner(wellnessCsv.getInputStream());
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] values = line.split(",");
                        places.add(new Wellness(values[0], values[1], values[2], Double.parseDouble(values[3])));
                    }
                    break;
            }
        } catch (IOException e) {
            System.err.println("Error reading from csv");
            throw new RuntimeException(e);
        }
        return CompletableFuture.supplyAsync(() -> places);
    }

    protected CompletableFuture<List<Place>> deserialiseJsonResponse(String jsonResponse) {
            JsonNode jsonResponseNode = null;
            try {
                jsonResponseNode = mapper.readTree(jsonResponse);
            } catch (JsonProcessingException e) {
                System.err.println("Error deserialising json object");;
            }
            JsonNode placesNode = jsonResponseNode.get("places");
            String name, openingHours, imageUrl, address;
            Double rating;

            List<Place> places = new ArrayList<>();

            for (JsonNode placeNode : placesNode) {
                name = placeNode.get("displayName").get("text").asText();

                StringBuilder openingHoursBuilder = new StringBuilder();

                try {
                    for (int i=0; i<placeNode.get("currentOpeningHours").get("weekdayDescriptions").size()-1;i++) {
                        openingHoursBuilder.append(
                                placeNode.get("regularOpeningHours").get("weekdayDescriptions").get(i).asText()
                                        .replaceAll("day", "")
                                        .replaceAll(":00", "")
                                        .concat(";"));
                    }
                } catch (NullPointerException e) {;}

                if (openingHoursBuilder.length()==0) {

                    try {
                        for (int i = 0; i < placeNode.get("regularOpeningHours").get("weekdayDescriptions").size() - 1; i++) {
                            openingHoursBuilder.append(
                                    placeNode.get("regularOpeningHours").get("weekdayDescriptions").get(i).asText()
                                            .replaceAll("day", "")
                                            .replaceAll(":00", "")
                                            .concat(";"));
                        }
                    } catch (NullPointerException e) {
                        ;
                    }
                }

                if (openingHoursBuilder.length()!=0) {
                    openingHours = openingHoursBuilder.toString().substring(0, openingHoursBuilder.length() - 1);
                } else {openingHours = "not specified";}

                rating = placeNode.get("rating").asDouble();
                address = placeNode.get("formattedAddress").asText()
                        .replaceAll(name+", ","")
                        .replaceAll(", London", "")
                        .replaceAll("London ", "")
                        .replaceAll(", UK", "");

                if (placeType.equals("landmark")){
                    places.add(new Landmark(name, openingHours, address, rating));
                } else if (placeType.equals("park")){
                    places.add(new Park(name, openingHours, address, rating));
                } else {
                    places.add(new Wellness(name, openingHours, address, rating));
                }
            }
            return CompletableFuture.supplyAsync(() -> places);
        }

}
