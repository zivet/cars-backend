package com.ioverlap.dojo.carservice.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Location {

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;

    private String address;

    private String city;

    private String state;

    private String zip;

    public Location() {
    }

    public Location(Double lat, Double lon, String address, String city, String state, String zip) {
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Location{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
