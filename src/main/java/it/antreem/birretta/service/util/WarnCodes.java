package it.antreem.birretta.service.util;

/**
 *
 * @author alessio
 */
public enum WarnCodes 
{   
    WARN_FRNDREQ_01(0, "Richiesta eseguita gia' eseguita e pendente"),
    WARN_FRNDREQ_02(1, "Amicizia gia' confermata"),
    WARN_FRNDCONFIRM_00(2,"Richiesta di amicizia non presente"),
    PUPPA_CODE(9999, "PuppaMessage");
    
    private int code;
    private String message;
    
    WarnCodes(int code, String message)
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
