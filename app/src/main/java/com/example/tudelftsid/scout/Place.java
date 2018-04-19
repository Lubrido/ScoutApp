package com.example.tudelftsid.scout;

/**
 * Created by davidvanrijn on 15/04/2018.
 */

public class Place {
    private String image;
    private String name;
    private String LocationLong;
    private String LocationLat;

    public Place(String image, String name, String LocationLong, String LocationLat)  {
        this.image = image;
        this.name = name;
        this.LocationLong = LocationLong;
        this.LocationLat = LocationLat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationLong() {
        return LocationLong;
    }

    public void setLocationLong(String LocationLong) {
        this.LocationLong = LocationLong;
    }

    public String getLocationLat() {
        return LocationLat;
    }

    public void setLocationLat(String LocationLat) {
        this.LocationLat = LocationLat;
    }
}

