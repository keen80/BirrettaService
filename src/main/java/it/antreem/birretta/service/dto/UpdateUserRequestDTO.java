/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.antreem.birretta.service.dto;

import java.util.Date;

/**
 *
 * @author Stefano
 */
public class UpdateUserRequestDTO {
    private String displayName;
    //private String firstName;
    //private String lastName;
    //private String description;
    private String email;
    private Integer gender = 0;
    private String nationality;
    private Date birthDate;
    private String idUser;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    
}
