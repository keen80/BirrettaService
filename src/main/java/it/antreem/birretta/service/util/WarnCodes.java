package it.antreem.birretta.service.util;

/**
 *
 * @author alessio
 */
public enum WarnCodes 
{   
    WARN_FRNDREQ_01("", "Richiesta eseguita gia' eseguita e pendente"),
    WARN_FRNDREQ_02("", "Amicizia gia' confermata"),
    WARN_FRNDCONFIRM_00("","Richiesta di amicizia non presente"),
    PUPPA_CODE("PuppaTitle", "PuppaMessage");
    
    private String title;
    private String message;
    
    WarnCodes(String title, String message)
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
