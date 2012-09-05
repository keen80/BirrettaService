package it.antreem.birretta.service.dto;
import it.antreem.birretta.service.model.*;

import it.antreem.birretta.service.util.DateAdapter;
import java.util.Date;

/**
 *
 * @author gmorlini
 */
public class ActivityDTO {

    private String idActivity;
    private String image;
    private String avatar;
    private String displayName;
    private String idUser;
    private String idBeer;
    private String idPlace;
    private String idFriend;
    private String beerName;
    private String placeName;
    private String friendName;
    private Integer type;
    private Integer status;
    private Integer like;
    private String date;
    private String jumpTo;
    public ActivityDTO(Activity a) {
        idActivity = a.getIdActivity();
        image = a.getImage();
        avatar = a.getAvatar();
        displayName = a.getDisplayName();
        idUser = a.getIdUser();
        idBeer = a.getIdBeer();
        idPlace = a.getIdPlace();
        idFriend = a.getIdFriend();
        beerName = a.getBeerName();
        placeName = a.getPlaceName();
        friendName = a.getFriendName();
        type = a.getType();
        status = a.getStatus();
        like = a.getLike();
        DateAdapter dateAdapter= new DateAdapter();
        dateAdapter.setDate(a.getDate());
        date=dateAdapter.toString();
        jumpTo = a.getJumpTo();
    }
    public ActivityDTO(){
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(String idActivity) {
        this.idActivity = idActivity;
    }

    public String getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(String idBeer) {
        this.idBeer = idBeer;
    }

    public String getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(String idFriend) {
        this.idFriend = idFriend;
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

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getJumpTo() {
        return jumpTo;
    }

    public void setJumpTo(String jumpTo) {
        this.jumpTo = jumpTo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
}
