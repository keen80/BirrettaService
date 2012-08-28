/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.antreem.birretta.service.util;

/**
 *
 * @author Stefano
 */
public enum NotificationCodes {
    FRIEND_REQUEST(0),
    FRIEND_CONFIRM(1);
    
    private final int type;
    
    NotificationCodes(int type){
        this.type=type;
    }
    
    public int getType(){
        return type;
    }
            
}
