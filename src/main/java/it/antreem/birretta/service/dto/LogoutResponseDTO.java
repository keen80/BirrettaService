package it.antreem.birretta.service.dto;

/**
 *
 * @author alessio
 */
public class LogoutResponseDTO 
{
    private boolean logout;
    private String messaggio;

    public boolean isLogout() {
        return logout;
    }

    public void setLogout(boolean logout) {
        this.logout = logout;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    } 
}
