package it.antreem.birretta.service.model;

import javax.ws.rs.FormParam;

/**
 *
 * @author alessio
 */
public class Beer extends MongoDBObject
{
    @FormParam("name")
    private String name;
    @FormParam("brewery")
    private String brewery;
     @FormParam("beerstyle")
    private int beerstyle;
    private int beertype;
    @FormParam("nationality")
    private String nationality;
    @FormParam("grad")
    private String grad;
    private String idBeer;
    private String idUser;
    private String image;
    private String status;
    private String username;
    private String insertedOn;
    @FormParam("description")
    private String param1;
    private String param2;
    private String param3;

    public int getBeerstyle() {
        return beerstyle;
    }

    public void setBeerstyle(int beerstyle) {
        this.beerstyle = beerstyle;
    }

    public int getBeertype() {
        return beertype;
    }

    public void setBeertype(int beertype) {
        this.beertype = beertype;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getInsertedOn() {
        return insertedOn;
    }

    public void setInsertedOn(String insertedOn) {
        this.insertedOn = insertedOn;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }
    
    public String getIdBeer(){
        return idBeer;
    }

    public void setIdBeer(String idBeer) {
        this.idBeer = idBeer;
    }

}
