package it.antreem.birretta.service.util;

/**
 *
 * @author alessio
 */
public enum ErrorCodes 
{   
    LOGIN_FAILED("Login failed", "Error logging in..."),
    REG_FAILED("Registration failed", "Registration failed"),
    REG_U01("Formato username errato", "Lo username deve essere lungo almeno 5 caratteri e contenere solamente caratteri alfanumerici e underscore"),
    REG_P01("Formato password errato", "La password deve essere lunga almeno 5 caratteri e contenere solamente caratteri alfanumerici e underscore"),
    REG_USER_DUP("Username gia presente", "Lo username scelto e' gia' presente. Occorre fare una scelta alternativa"),
    REG_INVALID_EMAIL("Email non valida", "Email non valida"),
    REG_INVALID_SEX("Sesso non valido", "Deve essere M o F"),
    REG_INVALID_FIRST("First name non valido", "First name non valido"),
    REG_INVALID_LAST("Last name non valido", "Last name non valido"),
    REG_INVALID_AGE("Eta non valida", "Eta non valida"),
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
