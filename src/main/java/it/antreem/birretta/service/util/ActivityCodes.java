/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.antreem.birretta.service.util;

/**
 *
 * @author Stefano
 */
public enum ActivityCodes {
    FRIEND_CONFIRM(0),
    BEER_CREATED(1),
    CHECKIN(2);
    
    private final int type;
    
    ActivityCodes(int type){
        this.type=type;
    }
    
    public int getType(){
        return type;
    }
}
