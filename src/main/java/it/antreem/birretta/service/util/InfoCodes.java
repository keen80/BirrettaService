package it.antreem.birretta.service.util;

/**
 *
 * @author gmorlini
 */
public enum InfoCodes 
{  
    OK_FRNDREQ_00(0, "Richiesta eseguita con successo"), 
    OK_FRNDCONFIRM_00(1, "Amicizia accettata con successo"),
    OK_FRNDREFUSE_00(2, "Amicizia rimossa con successo"),
    OK_CHECKIN_00(3, "Check-in eseguito con successo"),
    OK_NOTIFICATION_00(4, "Notifica inserita in stato read"),
    PUPPA_CODE(9999, "PuppaMessage");
    
    private int code;
    private String message;
    
    InfoCodes(int code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getTitle(){
        return name();//.replace("_", ".");
    }
    
    public String getMessage(){
        return message;
    }
}
