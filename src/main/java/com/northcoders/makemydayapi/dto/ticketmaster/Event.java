package com.northcoders.makemydayapi.dto.ticketmaster;


import java.util.List;

public class Event {

    private String name;
    private List<Image> images;

    private Dates dates;

    private String priceRanges;

    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }


    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }


    public String getPriceRanges() {
        return priceRanges;
    }

    public void setPriceRanges(String priceRanges) {
        this.priceRanges = priceRanges;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
