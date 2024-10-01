package com.northcoders.makemydayapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.makemydayapi.model.TicketmasterEvent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketmasterService {

    public List<TicketmasterEvent> getAllEvents() {

        List<TicketmasterEvent> allEvents = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        String endpoint = "https://app.ticketmaster.com/discovery/v2/events.json" +
                "?countryCode=GB&apikey=4Ot8mYGAjvNRHjVdQDkN0bqJxG4BrHGE&locale=en-us";
        Request request = new Request.Builder()
                .url(endpoint)
                .build();

        try (Response response = client.newCall(request).execute()) {

            String name, dateTime, imageUrl, venueName;

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonBody = mapper.readTree(response.body().string());
            JsonNode events = jsonBody.get("_embedded").get("events");
            for (int i = 0; i<events.size(); i++){
                JsonNode currentEvent = events.get(i);

                name = currentEvent.get("name").asText();
                dateTime = currentEvent.get("dates").get("start").get("localDate").asText()
                        .concat("T")
                        .concat(currentEvent.get("dates").get("start").get("localTime").asText())
                        .concat("Z");
                imageUrl = currentEvent.get("images").get(0).get("url").asText();
                venueName = currentEvent.get("_embedded").get("venues").get(0).get("address").get("line1").asText();

                TicketmasterEvent event = new TicketmasterEvent(name, dateTime, imageUrl, venueName);
                allEvents.add(event);
            }
        } catch (IOException e){
            System.err.println("Error making http request.");
        }
        return allEvents;
    }
}
