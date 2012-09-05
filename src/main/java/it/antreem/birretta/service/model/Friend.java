package it.antreem.birretta.service.model;

import it.antreem.birretta.service.util.DateAdapter;
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
    private String birthDate;
    private String avatar = "resources/images/defaultAvatar.jpg";
    private Integer role;
    private Integer status;//valorizzato dinamicamente in base a friendrelations(non amico, pending, amico)
    private String activatedOn;
    private String lastLoginOn;
//    private String badges = "";
    private String favorites = "";
    private String liked = "";
    private Integer counter_checkIns = 0;
    private Integer counter_friends = 0;
    private Integer counter_badges = 0;
    
        public Friend(User u) {
        idUser = u.getIdUser();
        username = u.getUsername();
        displayName = u.getDisplayName();
        firstName = u.getFirstName();
        lastName = u.getLastName();
        description = u.getDescription();
        email = u.getEmail();
        gender = u.getGender();
        nationality = u.getNationality();
        DateAdapter date = new DateAdapter();
        date.setDate(u.getBirthDate());
        birthDate = date.toString();
        avatar = u.getAvatar();
        role = u.getRole();
        status = u.getStatus();//valorizzato dinamicamente in base a friendrelations(non amico, pending, amico)
        date.setDate(u.getActivatedOn());
        activatedOn = date.toString();
        date.setDate(u.getLastLoginOn());
        lastLoginOn = date.toString();
//   badges = "";
        favorites = u.getFavorites();
        liked = u.getLiked();
        counter_checkIns = u.getCounterCheckIns();
        counter_friends = u.getCounterFriends();
        counter_badges = u.getCounterBadges();

    }
    public Friend(){
    }
    public String getActivatedOn() {
        return activatedOn;
    }

    public void setActivatedOn(String activatedOn) {
        this.activatedOn = activatedOn;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

 /*   public String getBadges() {
        return badges;
    }

    public void setBadges(String badges) {
        this.badges = badges;
    }
*/
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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

    public String getLastLoginOn() {
        return lastLoginOn;
    }

    public void setLastLoginOn(String lastLoginOn) {
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
