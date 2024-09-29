package com.northcoders.makemydayapi.service;

import com.northcoders.makemydayapi.model.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketmasterService {

    List<Activity> getAllEvents();
}
