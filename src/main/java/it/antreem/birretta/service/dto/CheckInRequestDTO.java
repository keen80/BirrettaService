package it.antreem.birretta.service.dto;

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
    private String username;
    private String idBeer;
    private String idLocation;
    private String idFeedback;
    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    public String getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(String idBeer) {
        this.idBeer = idBeer;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(String idFeedback) {
        this.idFeedback = idFeedback;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
