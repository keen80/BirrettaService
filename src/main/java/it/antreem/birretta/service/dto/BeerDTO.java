package it.antreem.birretta.service.dto;
import it.antreem.birretta.service.model.*;

import it.antreem.birretta.service.util.DateAdapter;
import java.util.Date;
import javax.ws.rs.FormParam;

/**
 *
 * @author alessio
 */
public class BeerDTO 
{

    private String name;

    private String brewery;

    private int beerstyle;
    private int beertype;

    private String nationality;

    private String grad;

    private int color;
    private String idBeer;
    private String idUser;
    private String image;
    private String status;
    private String username;
    private String insertedOn;

    private String param1;
    private String param2;
    private String param3;
    public BeerDTO()
    {}
    public BeerDTO(Beer b)
    {
    name=b.getName();

    brewery=b.getBrewery();

   beerstyle=b.getBeerstyle();
   beertype=b.getBeertype();

    nationality=b.getNationality();

    grad=b.getGrad();

   color=b.getColor();
    idBeer=b.getIdBeer();
    idUser=b.getIdUser();
    image=b.getImage();
    status=b.getStatus();
    username=b.getUsername();
    DateAdapter date=new DateAdapter();
    date.setDate(b.getInsertedOn());
    insertedOn=date.toString();

    param1=b.getParam1();
    param2=b.getParam2();
    param3=b.getParam3();
    }
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
}
