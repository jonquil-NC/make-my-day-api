package com.northcoders.makemydayapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PopulateDatabaseScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(PopulateDatabaseScheduler.class);

    @Autowired
    private SkiddleService skiddleService;

    @Autowired
    private TicketmasterService ticketmasterService;

    // https://www.baeldung.com/cron-expressions
    // https://www.freeformatter.com/cron-expression-generator-quartz.html
    @Scheduled(cron = "0 0/20 * * * * ")
    public void populateDatabaseForSkiddle() {
        LOG.info("Populating database from skiddle...");
        this.skiddleService.populateDatabase();
        LOG.info("... populating database from skiddle ended.");

    }

    @Scheduled(cron = "0 0/20 * * * * ")
    public void populateDatabaseForTicketMaster() {
        LOG.info("Populating database from ticketmaster...");
        // this.ticketmasterService.populateDatabase();
        LOG.info("... populating database from ticketmaster ended.");
    }

}
