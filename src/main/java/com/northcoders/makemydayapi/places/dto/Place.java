package com.northcoders.makemydayapi.places.dto;

public abstract class Place {
    String name;
    String openingHours;
    String address;
    Double rating;

    public Place(String name, String openingHours, String address, Double rating) {
        this.name = name;
        this.openingHours = openingHours;
        this.address = address;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
