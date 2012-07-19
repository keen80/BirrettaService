package it.antreem.birretta.service.model;

/**
 *
 * @author alessio
 */
public class Address // NON IMPLEMENTA MONGODBOBJECT PERCHE' E' EMBEDDED!
{
    private String street;
    private Integer num;
    private String cap;
    private String state;

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
