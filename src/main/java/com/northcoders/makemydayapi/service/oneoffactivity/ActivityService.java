package com.northcoders.makemydayapi.service.oneoffactivity;

import com.northcoders.makemydayapi.model.activity.oneoff.OneOffActivityType;
import com.northcoders.makemydayapi.dto.activity.oneoff.OneOffActivityResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface ActivityService {

    @Async
    CompletableFuture<List<OneOffActivityResponse>> getEventsByActivityTypes(List<OneOffActivityType> activityTypes);

}
