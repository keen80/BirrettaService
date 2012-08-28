package it.antreem.birretta.service.util;

/**
 *
 * @author gmorlini
 */
public enum InfoCodes 
{  
    OK_FRNDREQ_00("", "Richiesta eseguita con successo"), 
    OK_FRNDCONFIRM_00("", "Amicizia accettata con successo"),
    OK_FRNDREFUSE_00("", "Amicizia rimossa con successo"),
    OK_CHECKIN_00("", "Check-in eseguito con successo"),
    OK_NOTIFICATION_00("", "Notifica inserita in stato read"),
    PUPPA_CODE("PuppaTitle", "PuppaMessage");
    
    private String title;
    private String message;
    
    InfoCodes(String title, String message)
    {
        this.title = title;
        this.message = message;
    }
    
    public String getCode() {
        return name();//.replace("_", ".");
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getMessage(){
        return message;
    }
}
