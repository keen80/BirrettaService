package it.antreem.birretta.service.model;

/**
 *
 * @author alessio
 */
public class LocType extends MongoDBObject
{
    private String cod; // unique, nome parlante
    private String desc;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
