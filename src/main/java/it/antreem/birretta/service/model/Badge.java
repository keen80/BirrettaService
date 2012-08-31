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
    private int idBadge;
    private int cod;
    private String name;
    private int category;
    private String image;
    // private Integer score; ??
    // private Object prerequisito; ??

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    /*
    public String getIdBadge(){
        return super.getId().toString();
    }*/

    public int getIdBadge() {
        return idBadge;
    }

    public void setIdBadge(int idBadge) {
        this.idBadge = idBadge;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
