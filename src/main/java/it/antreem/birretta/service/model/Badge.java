package it.antreem.birretta.service.model;

/**
 * La logica di acquisizione dei badge per ora &egrave; esterna; in futuro si
 * pu&ograve; rendere necessario costruire una grammatica e un micro-parser per
 * la stessa in grado di definire i requisiti (anche complessi) per la vincita
 * dei badge.
 * 
 * @author alessio
 */
public class Badge extends MongoDBObject
{
    public final static String COD_10_BEERS = "bdg_10_beers";
    
    private String cod;
    private String message;
    // private Integer score; ??
    // private Object prerequisito; ??

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getIdBadge(){
        return super.getId().toString();
    }
}
