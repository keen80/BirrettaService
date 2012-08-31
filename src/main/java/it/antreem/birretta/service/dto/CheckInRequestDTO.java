package it.antreem.birretta.service.dto;

import javax.ws.rs.FormParam;

/*
{
  "username":"alessio",
  "idBeer":"5007ffc42318893236d354a4",
  "idLocation":"5007df8c23180a65db23b70c",
  "picture":null,
  "idFeedback":9,
  "comment":"prova"
}
 */
/**
 *
 * @author alessio
 */
public class CheckInRequestDTO 
{

    @FormParam("idUser")
    private String idUser;
    @FormParam("idBeer")
    private String idBeer;
    @FormParam("idPlace")
    private String idPlace;
    @FormParam("image")
    private String image;
    @FormParam("rate")
    private String rate;
    @FormParam("rate1")
    private String rate1;
    @FormParam("rate2")
    private String rate2;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(String idBeer) {
        this.idBeer = idBeer;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate1() {
        return rate1;
    }

    public void setRate1(String rate1) {
        this.rate1 = rate1;
    }

    public String getRate2() {
        return rate2;
    }

    public void setRate2(String rate2) {
        this.rate2 = rate2;
    }
    
}
