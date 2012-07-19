package it.antreem.birretta.service.model;

import java.util.ArrayList;

/**
 *
 * @author alessio
 */
public class Location extends MongoDBObject
{
    private String name;
    private String desc;
    private ArrayList<Double> pos;
    private Address address;
    private LocType type;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Double> getPos() {
        return pos;
    }

    public void setPos(ArrayList<Double> pos) {
        this.pos = pos;
    }

    public LocType getType() {
        return type;
    }

    public void setType(LocType type) {
        this.type = type;
    }
}
