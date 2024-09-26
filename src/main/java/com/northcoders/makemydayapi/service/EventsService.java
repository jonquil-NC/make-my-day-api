package com.northcoders.makemydayapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.makemydayapi.model.Events;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class EventsService {

    public List<Events> getAllEvents() {
        List<Events> allEvents = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        int totalPages = 49; // 49 pages in total.

        for (int page = 0; page < totalPages; page++) {
            String endpoint = "https://app.ticketmaster.com/discovery/v2/events" +
                    "?apikey=4Ot8mYGAjvNRHjVdQDkN0bqJxG4BrHGE&city=London&page=" + page;

            Request request = new Request.Builder()
                    .url(endpoint)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonNode jsonBody = mapper.readTree(response.body().string());

                    JsonNode embeddedNode = jsonBody.get("_embedded");
                    if (embeddedNode != null) {
                        JsonNode events = embeddedNode.get("events");

                        for (JsonNode currentEvent : events) {
                            String name = currentEvent.get("name").asText();
                            String dateTime = currentEvent.get("dates").get("start").get("localDate").asText()
                                    .concat("T")
                                    .concat(currentEvent.get("dates").get("start").get("localTime").asText())
                                    .concat("Z");
                            String imageUrl = currentEvent.get("images").get(0).get("url").asText();
                            String venueName = currentEvent.get("_embedded").get("venues").get(0).get("name").asText();

                            Events event = new Events(name, dateTime, imageUrl, venueName);
                            allEvents.add(event);
                        }
                    } else {
                        break;
                    }
                } else {
                    throw new IOException("Unexpected response: " + response);
                }
            } catch (IOException e) {
                System.err.println("Error making HTTP request: " + e.getMessage());
                break;
            }
        }

        return allEvents;
    }
}