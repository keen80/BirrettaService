package it.antreem.birretta.service.dto;

import javax.ws.rs.FormParam;

/*
{
  "username":"alessio"
}
 */
public class LogoutRequestDTO 
{
    @FormParam("username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
