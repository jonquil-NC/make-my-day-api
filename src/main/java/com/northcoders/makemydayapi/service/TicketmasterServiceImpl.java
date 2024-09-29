package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketmasterServiceImpl implements TicketmasterService {
    @Override
    public List<Activity> getAllEvents() {
        return List.of();
    }
}
