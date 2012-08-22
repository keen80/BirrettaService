package it.antreem.birretta.service.model;

import java.util.ArrayList;

/**
 *
 * @author gmorlini
 */
public class Body<T> {
    private ArrayList<T> list;

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }
    
   
}
