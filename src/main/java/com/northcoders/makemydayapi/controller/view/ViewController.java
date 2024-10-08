package com.northcoders.makemydayapi.controller.view;

import com.northcoders.makemydayapi.dto.user.SearchParams;
import com.northcoders.makemydayapi.model.activity.Activity;
import com.northcoders.makemydayapi.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("api/v1")
public class ViewController {

    @Autowired
    MainService mainService;

    @GetMapping("/query")
    public String queryForm(Model model){
        model.addAttribute("searchParams", new SearchParams());

        model.addAttribute("restaurants", List.of(
                "caribbean", "chinese", "indian", "japanese", "steak", "turkish", "thai", "vietnamese",
                "burger", "fish_and_chips", "italian", "world_cuisine", "cafe", "pub"
                ));

        model.addAttribute("events", List.of(
                "MUSIC", "CULTURAL", "FEST", "LIVE", "CLUB", "THEATRE",
                "COMEDY", "EXHIB", "KIDS", "BARPUB", "LGB", "SPORT", "ARTS"
        ));

        return "query.html";
    }

    @PostMapping("/query")
    public String submitUserQuery(@ModelAttribute SearchParams searchParams, Model model) throws Exception {
        List<Activity> activities = mainService.getActivities(searchParams);
        if (activities.size() > 0) {
            Collections.sort(activities, new Comparator<Activity>() {
                @Override
                public int compare(final Activity o1, final Activity o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        model.addAttribute("activities", activities);
        return "results.html";
    }
}

