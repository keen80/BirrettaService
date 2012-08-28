/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.antreem.birretta.service.util;

/**
 *
 * @author Stefano
 */
public enum NotificationStatusCodes {
    READ(0),
    UNREAD(1);
    
    private final int status;
    
    NotificationStatusCodes(int status){
        this.status=status;
    }
    
    public int getStatus(){
        return status;
    }
}
