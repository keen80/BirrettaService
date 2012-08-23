package it.antreem.birretta.service.model;

import java.util.ArrayList;

/**
 *
 * @author alessio
 */
public class Location extends MongoDBObject
{
    private String idLocation;
    private String name;
    private String url;
    private ArrayList<Double> pos;
    private ArrayList<String> categories;
    private String address;
    private String country;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public ArrayList<Double> getPos() {
        return pos;
    }

    public void setPos(ArrayList<Double> pos) {
        this.pos = pos;
    }
    
    public Location(){ 
        pos = new ArrayList<Double>();
        pos.add(0.0);
        pos.add(0.0);
    }

    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> category) {
        this.categories = category;
    }

    public String getIdLocation() {
        return idLocation;
    }
      /*
    public String getIdLocation(){
        return super.getId().toString();
    }*/
    public void setIdLocation(String id) {
        this.idLocation = id;
    }

    
}
