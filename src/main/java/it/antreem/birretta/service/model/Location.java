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
    private String idLocType; // link al campo _id di LocType
    private Address address;
    
    public Location(){ // Assicuro size == 2
        pos = new ArrayList<Double>();
        pos.add(0.0);
        pos.add(0.0);
    }
    
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

    public String getIdLocType() {
        return idLocType;
    }

    public void setIdLocType(String idLocType) {
        this.idLocType = idLocType;
    }
    
    public String getIdLocation(){
        return super.getId().toString();
    }
}
