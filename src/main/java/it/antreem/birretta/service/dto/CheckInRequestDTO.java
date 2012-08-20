package it.antreem.birretta.service.dto;

/*
{
  "username":"alessio",
  "idBeer":"5007ffc42318893236d354a4",
  "idLocation":"5007df8c23180a65db23b70c",
  "picture":null,
  "score":9,
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
    private String comment;
    private Integer score;
    private byte[] picture;

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}