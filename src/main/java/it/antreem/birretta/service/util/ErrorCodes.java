package it.antreem.birretta.service.util;

/**
 *
 * @author alessio
 */
public enum ErrorCodes 
{   
    LOGIN_FAILED("Login failed", "Error logging in..."),
    PUPPA_CODE("PuppaTitle", "PuppaMessage");
    
    private String title;
    private String message;
    
    ErrorCodes(String title, String message)
    {
        this.title = title;
        this.message = message;
    }
    
    public String getCode() {
        return name().replace("_", ".");
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getMessage(){
        return message;
    }
}
