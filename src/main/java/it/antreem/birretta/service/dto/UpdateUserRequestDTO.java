/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.antreem.birretta.service.dto;

import it.antreem.birretta.service.util.DateAdapter;
import java.util.Date;
import javax.ws.rs.FormParam;

/**
 *
 * @author Stefano
 */
public class UpdateUserRequestDTO {
    @FormParam("idUser")
    private String idUser;
    @FormParam("username")
    private String username;
    @FormParam("displayName")
    private String displayName;
    @FormParam("firstName")
    private String firstName;
    @FormParam("lastName")
    private String lastName;
    @FormParam("description")
    private String description;
    @FormParam("email")
    private String email;
    @FormParam("gender")
    private Integer gender = 0;
    @FormParam("nationality")
    private String nationality;
    @FormParam("birthDate")
    private DateAdapter birthDate;
    @FormParam("avatar")
    private String avatar = "resources/images/defaultAvatar.jpg";
    @FormParam("shareFacebook")
    private Boolean shareFacebook = false;
    @FormParam("shareTwitter")
    private Boolean shareTwitter = false;
    @FormParam("enableNotification")
    private Boolean enableNotification = false;
    @FormParam("role")
    private Integer role;
    @FormParam("status")
    private Integer status;
    @FormParam("pwdHash")
    private String pwdHash;
    @FormParam("activatedOn")
    private Date activatedOn;
    @FormParam("lastLoginOn")
    private Date lastLoginOn;
    @FormParam("badges")
    private String badges = "";
    @FormParam("favorites")
    private String favorites = "";
    @FormParam("liked")
    private String liked = "";
    @FormParam("counterCheckIns")
    private Integer counterCheckIns = 0;
    @FormParam("counterFriends")
    private Integer counterFriends = 0;
    @FormParam("counterBadges")
    private Integer counterBadges = 0;
    @FormParam("hashBeerlist")
    private String hashBeerlist;
    @FormParam("hashFriendlist")
    private String hashFriendlist;
    @FormParam("hashNotificationlist")
    private String hashNotificationlist;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean isShareFacebook() {
        return shareFacebook;
    }

    public void setShareFacebook(Boolean shareFacebook) {
        this.shareFacebook = shareFacebook;
    }

    public Boolean isShareTwitter() {
        return shareTwitter;
    }

    public void setShareTwitter(Boolean shareTwitter) {
        this.shareTwitter = shareTwitter;
    }

    public Boolean isEnableNotification() {
        return enableNotification;
    }

    public void setEnableNotification(Boolean enableNotification) {
        this.enableNotification = enableNotification;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public Date getActivatedOn() {
        return activatedOn;
    }

    public void setActivatedOn(Date activatedOn) {
        this.activatedOn = activatedOn;
    }

    public Date getLastLoginOn() {
        return lastLoginOn;
    }

    public void setLastLoginOn(Date lastLoginOn) {
        this.lastLoginOn = lastLoginOn;
    }

    public String getBadges() {
        return badges;
    }

    public void setBadges(String badges) {
        this.badges = badges;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public Integer getCounterCheckIns() {
        return counterCheckIns;
    }

    public void setCounterCheckIns(Integer counterCheckIns) {
        this.counterCheckIns = counterCheckIns;
    }

    public Integer getCounterFriends() {
        return counterFriends;
    }

    public void setCounterFriends(Integer counterFriends) {
        this.counterFriends = counterFriends;
    }

    public Integer getCounterBadges() {
        return counterBadges;
    }

    public void setCounterBadges(Integer counterBadges) {
        this.counterBadges = counterBadges;
    }

    public String getHashBeerlist() {
        return hashBeerlist;
    }

    public void setHashBeerlist(String hashBeerlist) {
        this.hashBeerlist = hashBeerlist;
    }

    public String getHashFriendlist() {
        return hashFriendlist;
    }

    public void setHashFriendlist(String hashFriendlist) {
        this.hashFriendlist = hashFriendlist;
    }

    public String getHashNotificationlist() {
        return hashNotificationlist;
    }

    public void setHashNotificationlist(String hashNotificationlist) {
        this.hashNotificationlist = hashNotificationlist;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public DateAdapter getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateAdapter birthDate) {
        this.birthDate = birthDate;
    }
    
    
}
