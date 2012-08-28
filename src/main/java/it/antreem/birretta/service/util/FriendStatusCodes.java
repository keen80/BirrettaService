/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.antreem.birretta.service.util;

/**
 *
 * @author Stefano
 */
public enum FriendStatusCodes {
    NOT_FRIEND(0),
    FRIEND(1),
    PENDING(2);
    
    private final int status;
    
    FriendStatusCodes(int status){
        this.status=status;
    }
    
    public int getStatus(){
        return status;
    }
}
