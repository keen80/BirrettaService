package it.antreem.birretta.service.model;

import java.util.Date;

public class User extends MongoDBObject {

    private String idUser;
    private String username;
    private String displayName;
    private String firstName;
    private String lastName;
    private String description;
    private String email;
    private Integer gender = 0;
    private String nationality;
    private Date birthDate;
    private String avatar = "resources/images/defaultAvatar.jpg";
    private boolean shareFacebook = false;
    private boolean shareTwitter = false;
    private boolean enableNotification = false;
    private Integer role;
    private Integer status;
    private String pwdHash;
    private Date activatedOn;
    private Date lastLoginOn;
    private String badges = "";
    private String favorites = "";
    private String liked = "";
    private Integer counterCheckIns = 0;
    private Integer counterFriends = 0;
    private Integer counterBadges = 0;
    private String hashBeerlist;
    private String hashFriendlist;
    private String hashNotificationlist;

    public Date getActivatedOn() {
        return activatedOn;
    }

    public void setActivatedOn(Date activatedOn) {
        this.activatedOn = activatedOn;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBadges() {
        return badges;
    }

    public void setBadges(String badges) {
        this.badges = badges;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getCounterBadges() {
        return counterBadges;
    }

    public void setCounterBadges(Integer counterBadges) {
        this.counterBadges = counterBadges;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnableNotification() {
        return enableNotification;
    }

    public void setEnableNotification(boolean enableNotification) {
        this.enableNotification = enableNotification;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Date getLastLoginOn() {
        return lastLoginOn;
    }

    public void setLastLoginOn(Date lastLoginOn) {
        this.lastLoginOn = lastLoginOn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public boolean isShareFacebook() {
        return shareFacebook;
    }

    public void setShareFacebook(boolean shareFacebook) {
        this.shareFacebook = shareFacebook;
    }

    public boolean isShareTwitter() {
        return shareTwitter;
    }

    public void setShareTwitter(boolean shareTwitter) {
        this.shareTwitter = shareTwitter;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
