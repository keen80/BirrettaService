package it.antreem.birretta.service.dto;
import it.antreem.birretta.service.model.*;

import it.antreem.birretta.service.util.DateAdapter;
import java.util.Date;

/**
 * Oggetto di modello che rappresenta una bevuta (check-in).
 * 
 * @author alessio
 */
public class DrinkDTO extends MongoDBObject
{
    private String idDrink;
    private String displayName;
    private String image;
    private String idUser;
    private String idBeer;
    private String idPlace;
    private String beerName;
    private String placeName;
    private String insertedOn;
    private Integer rate;
    private Integer rate2;
    private Integer rate3;

    public DrinkDTO() {
    }

    public DrinkDTO(Drink d) {
        idDrink=d.getIdDrink();
        displayName = d.getDisplayName();
        image = d.getImage();
        idUser = d.getIdUser();
        idBeer = d.getIdBeer();
        idPlace = d.getIdPlace();
        beerName = d.getBeerName();
        placeName = d.getPlaceName();
        DateAdapter date = new DateAdapter();
        date.setDate(d.getInsertedOn());
        insertedOn = date.toString();
        rate = d.getRate();
        rate2 = d.getRate2();
        rate3 = d.getRate3();
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }
    
    public String getInsertedOn() {
        return insertedOn;
    }

    public void setInsertedOn(String insertedOn) {
        this.insertedOn = insertedOn;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(String idBeer) {
        this.idBeer = idBeer;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

     public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getRate2() {
        return rate2;
    }

    public void setRate2(Integer rate2) {
        this.rate2 = rate2;
    }

    public Integer getRate3() {
        return rate3;
    }

    public void setRate3(Integer rate3) {
        this.rate3 = rate3;
    }
    
}
