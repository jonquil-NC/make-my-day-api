package com.northcoders.makemydayapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.makemydayapi.model.Activity;
import com.northcoders.makemydayapi.model.ActivityType;
import com.northcoders.makemydayapi.model.Location;
import com.northcoders.makemydayapi.model.ResourceType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TicketmasterServiceImpl implements TicketmasterService {

    private static final String API_KEY = "4Ot8mYGAjvNRHjVdQDkN0bqJxG4BrHGE";
    private static final String API_URL = "https://app.ticketmaster.com/discovery/v2/events";
    private static final String LONDON_LAT = "51.5074";
    private static final String LONDON_LON = "-0.1278";
    private static final Logger LOG = LoggerFactory.getLogger(TicketmasterServiceImpl.class);


//    @Autowired
//    private EventsRepository eventsRepository;

    private final OkHttpClient httpClient = new OkHttpClient();

    @Override
    public List<Activity> getAllEvents() {
        List<Activity> activities = new ArrayList<>();

        //API request URL
        String url = API_URL + "?apikey=" + API_KEY + "&latlong=" + LONDON_LAT + "," + LONDON_LON;

        Request request = new Request.Builder().url(url).build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to call Ticketmaster API");
            }

            //Response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseBody = mapper.readTree(response.body().string());
            JsonNode eventNodes = responseBody.get("_embedded").get("events");

            //Response to Activity object
            for (JsonNode eventNode : eventNodes) {
                Activity activity = new Activity();
                activity.setName(eventNode.get("name").asText());
                activity.setDescription(eventNode.has("info") ? eventNode.get("info").asText() : "No description available");

                if (eventNode.get("dates").has("start")) {
                    LocalDate date = LocalDate.parse(eventNode.get("dates").get("start").get("localDate").asText());
                    LocalTime startTime = LocalTime.parse(eventNode.get("dates").get("start").get("localTime").asText());
                    activity.setDate(date);
                    activity.setStartTime(startTime);
                }

                //Location class
                Location location = new Location();
                location.setLatitude(eventNode.get("_embedded").get("venues").get(0).get("location").get("latitude").asDouble());
                location.setLongitude(eventNode.get("_embedded").get("venues").get(0).get("location").get("longitude").asDouble());
                activity.setLocation(location);

                //Price from Ticketmaster or null
                if (eventNode.has("priceRanges")) {
                    activity.setPrice(eventNode.get("priceRanges").get(0).get("min").asText());
                } else {
                    activity.setPrice(null);  //null if not available
                }

                //Activity type
//                try {
//                    activity.setActivityType(ActivityType.valueOf(keyword.toUpperCase()));
//                } catch (IllegalArgumentException e) {
//                    activity.setActivityType(ActivityType.EVENT); // Default to EVENT if the keyword doesn't match
//                }

                //Resource: Ticketmaster
                activity.setResourceType(ResourceType.TICKETMASTER);

                activities.add(activity);
            }

        } catch (IOException e) {
            LOG.error("Error occurred while fetching events from Ticketmaster", e);
        }

        return activities;
    }
}


//    @Override
//    public void saveToItinerary(Activity activity) {
//        LOG.info("Saving event [{}] to itinerary", activity.getName());
//        itineraryRepository.save(activity);  // Save event to the itinerary in the database
//    }