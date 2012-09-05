package it.antreem.birretta.service.dto;
import it.antreem.birretta.service.model.*;
import it.antreem.birretta.service.util.DateAdapter;


/**
 *
 * @author gmorlini
 */
public class NotificationDTO extends MongoDBObject
{
    private String jumpTo;
    private String targetName;
    private String idBeer;
    private String beerName;
    private String idFriend;
    private String friendName;
    private String idPlace;
    private String placeName;
    private String image;
    private Integer type;
    private Integer status;
    private String insertedOn;
    private String idUser;
    private String idNotification;

    public NotificationDTO() {
    }

    public NotificationDTO(Notification n) {
        jumpTo = n.getJumpTo();
        targetName = n.getTargetName();
        idBeer = n.getIdBeer();
        beerName = n.getBeerName();
        idFriend = n.getIdFriend();
        friendName = n.getFriendName();
        idPlace = n.getIdPlace();
        placeName = n.getPlaceName();
        image = n.getImage();
        type = n.getType();
        status = n.getStatus();
        DateAdapter dateAdapter = new DateAdapter();
        dateAdapter.setDate(n.getInsertedOn());
        insertedOn = dateAdapter.toString();
        idUser = n.getIdUser();
        idNotification = n.getIdNotification();
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
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

    public String getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(String idNotification) {
        this.idNotification = idNotification;
    }

    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }


    public String getJumpTo() {
        return jumpTo;
    }

    public void setJumpTo(String jumpTo) {
        this.jumpTo = jumpTo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInsertedOn() {
        return insertedOn;
    }

    public void setInsertedOn(String insertedOn) {
        this.insertedOn = insertedOn;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
