package it.antreem.birretta.service.dto;

import java.util.ArrayList;

/**
 * versione semplificata location
 * @author gmorlini
 */
public class Place {
    private String idPlace;
    private String placeName;
    private String lat;
    private String lng;
    private String url;
    private String category;
    private Integer drinkedIn=0;
    private String cc;
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idplace) {
        this.idPlace = idplace;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDrinkedIn() {
        return drinkedIn;
    }

    public void setDrinkedIn(Integer drinkedIn) {
        this.drinkedIn = drinkedIn;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }
    
}
