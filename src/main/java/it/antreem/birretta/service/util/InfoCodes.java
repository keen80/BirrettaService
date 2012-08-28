package it.antreem.birretta.service.util;

/**
 *
 * @author gmorlini
 */
public enum InfoCodes 
{  

    OK(100, "Successo"), 
    OK_FRNDREQ_00(101, "Richiesta eseguita con successo"), 
    OK_FRNDCONFIRM_00(102, "Amicizia accettata con successo"),
    OK_FRNDREFUSE_00(103, "Amicizia rimossa con successo"),
    OK_CHECKIN_00(104, "Check-in eseguito con successo"),
    OK_NOTIFICATION_00(105, "Notifica inserita in stato read"),
    OK_INSERTBEER_00(106,"Inserimento eseguito con successo"),
    OK_SAVEUSER_00(107, "Utente inserito con successo"),
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
