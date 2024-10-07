package com.northcoders.makemydayapi.controller.ongoingactivity;

import com.northcoders.makemydayapi.dto.ongoingactivity.places.Place;
import com.northcoders.makemydayapi.service.ongoingactivity.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/places")
public class PlacesController {

    @Autowired
    private PlacesService placesService;

    /*
    Accepts the following path variables:
        •"park"
        •"landmark"
        •"wellness"
     */

    @GetMapping("{type}")
    public CompletableFuture<ResponseEntity<List<Place>>> getPlaces(@PathVariable String type) throws Exception {
        CompletableFuture<List<Place>> places = placesService.getPlaces(type);
        return places.thenApply(p -> new ResponseEntity<>(p, HttpStatus.OK));
    }

}
