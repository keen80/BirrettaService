package it.antreem.birretta.service.dto;

/**
 *
 * @author alessio
 */
public class GenericResultDTO 
{
    private boolean success = true;
    private String message;
    
    public GenericResultDTO(){}
    
    public GenericResultDTO(boolean success, String message){
        this.success = success;
        this.message = message;
    }
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
