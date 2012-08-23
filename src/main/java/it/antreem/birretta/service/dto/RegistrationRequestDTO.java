package it.antreem.birretta.service.dto;

/*
{
  "username":"alessio",
  "displayName" : "alessio"
  "password": "puppa",
  "firstName": "Alessio",
  "lastName": "DM85",
  "birthDate": "",
  "email": "user@server.com",
  "gender": 1
}
 */
/**
 *
 * @author alessio
 */
public class RegistrationRequestDTO 
{
    private String username;
    private String displayName;
    private String password;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private Integer gender;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
