package com.northcoders.makemydayapi.model.activity.oneoff;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OneOffActivityType {

    // Generic
    EVENT(ResourceType.GENERIC),

    // Ticketmaster
    SPORTS(ResourceType.TICKETMASTER),      // Sports
    MUSIC(ResourceType.TICKETMASTER),       // Music
    CULTURAL(ResourceType.TICKETMASTER),    // Arts & Theatre

    // Skiddle
    FEST(ResourceType.SKIDDLE),             // Festivals
    LIVE(ResourceType.SKIDDLE),             // Live music
    CLUB(ResourceType.SKIDDLE),             // Clubbing/Dance music
    DATE(ResourceType.SKIDDLE),             // Dating event
    THEATRE(ResourceType.SKIDDLE),          // Theatre/Dance
    COMEDY(ResourceType.SKIDDLE),           // Comedy
    EXHIB(ResourceType.SKIDDLE),            // Exhibitions and Attractions
    KIDS(ResourceType.SKIDDLE),             // Kids/Family event
    BARPUB(ResourceType.SKIDDLE),           // Bar/Pub event
    LGB(ResourceType.SKIDDLE),              // Gay/Lesbian event
    SPORT(ResourceType.SKIDDLE),            // Sporting event
    ARTS(ResourceType.SKIDDLE);             // The Arts

    public final ResourceType resourceType;

    public static boolean isSkiddleActivityType(OneOffActivityType activityType) {
        boolean isSkiddleActivityType = activityType.equals(OneOffActivityType.FEST)
                || activityType.equals(OneOffActivityType.LIVE)
                || activityType.equals(OneOffActivityType.CLUB)
                || activityType.equals(OneOffActivityType.DATE)
                || activityType.equals(OneOffActivityType.THEATRE)
                || activityType.equals(OneOffActivityType.COMEDY)
                || activityType.equals(OneOffActivityType.EXHIB)
                || activityType.equals(OneOffActivityType.KIDS)
                || activityType.equals(OneOffActivityType.BARPUB)
                || activityType.equals(OneOffActivityType.LGB)
                || activityType.equals(OneOffActivityType.SPORT)
                || activityType.equals(OneOffActivityType.ARTS);

        return isSkiddleActivityType;
    }

    public static boolean isTicketmasterActivityType(OneOffActivityType activityType) {
        boolean isTicketmasterActivityType = activityType.equals(OneOffActivityType.SPORTS)
                || activityType.equals(OneOffActivityType.MUSIC)
                || activityType.equals(OneOffActivityType.CULTURAL);

        return isTicketmasterActivityType;
    }

}

