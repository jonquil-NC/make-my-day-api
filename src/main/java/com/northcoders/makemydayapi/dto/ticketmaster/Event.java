package com.northcoders.makemydayapi.dto.ticketmaster;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event {

    private String name;
    private String type;
    private String id;
    private List<Image> images;
//    may not need Sales as we have dates
//    private Sales sales;
    private Dates dates;

   @SerializedName("info")
    private String info;

//   add PriceRanges class
    private String priceRanges;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

//    public Sales getSales() {
//        return sales;
//    }
//
//    public void setSales(Sales sales) {
//        this.sales = sales;
//    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPriceRanges() {
        return priceRanges;
    }

    public void setPriceRanges(String priceRanges) {
        this.priceRanges = priceRanges;
    }
}
