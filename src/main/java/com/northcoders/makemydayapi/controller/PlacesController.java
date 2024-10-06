package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.dto.places.Place;
import com.northcoders.makemydayapi.service.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<List<Place>> getPlaces(@PathVariable String type) throws Exception {
        return new ResponseEntity<>(placesService.getPlaces(type), HttpStatus.OK);
    }



    @GetMapping("")
    public ResponseEntity<List<Place>> getAllPlacesByTypes(@RequestParam("types") List<String> placesTypeList) throws Exception {
        List<Place> placesList = new ArrayList<>();

        for(String type : placesTypeList) {
            List<Place> tmpList = placesService.getPlaces(type);
            placesList.addAll(tmpList);
        }

        return ResponseEntity.ok(placesList);
    }

}
