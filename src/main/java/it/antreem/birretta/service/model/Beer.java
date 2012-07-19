package it.antreem.birretta.service.model;

/**
 *
 * @author alessio
 */
public class Beer extends MongoDBObject
{
    private String name;
    private String desc;
    private String category;
    private String subcategory;
    private byte[] picture;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
    
    public String getIdBeer(){
        return super.getId().toString();
    }
}
