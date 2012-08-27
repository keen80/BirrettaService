package it.antreem.birretta.service.model;

import java.util.Date;

/**
 *
 * @author gmorlini
 */
public class Friend {
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
    private Integer role;
    private Integer status;//valorizzato dinamicamente in base a friendrelations(non amico, pending, amico)
    private Date activatedOn;
    private Date lastLoginOn;
    private String badges = "";
    private String favorites = "";
    private String liked = "";
    private Integer counter_checkIns = 0;
    private Integer counter_friends = 0;
    private Integer counter_badges = 0;

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

    public Integer getCounter_badges() {
        return counter_badges;
    }

    public void setCounter_badges(Integer counter_badges) {
        this.counter_badges = counter_badges;
    }

    public Integer getCounter_checkIns() {
        return counter_checkIns;
    }

    public void setCounter_checkIns(Integer counter_checkIns) {
        this.counter_checkIns = counter_checkIns;
    }

    public Integer getCounter_friends() {
        return counter_friends;
    }

    public void setCounter_friends(Integer counter_friends) {
        this.counter_friends = counter_friends;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
