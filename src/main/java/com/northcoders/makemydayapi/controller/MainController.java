package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.dto.user.SearchParams;
import com.northcoders.makemydayapi.model.activity.Activity;
import com.northcoders.makemydayapi.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MainController {

    @Autowired
    MainService mainService;

    @PostMapping
    public ResponseEntity<List<Activity>> mainSearch(@RequestBody SearchParams searchParams) throws Exception {
        return new ResponseEntity<>(mainService.getActivities(searchParams), HttpStatus.OK);
    }

}
