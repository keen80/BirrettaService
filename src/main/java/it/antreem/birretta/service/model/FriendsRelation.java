package it.antreem.birretta.service.model;

/**
 * indica una relazione d'amicizia, viene creato ogni volta che viene fatta una richiesta
 * il campo friend valorizzato a true se il rapporto è stato accettato
 * @author gmorlini
 */
public class FriendsRelation {
    private String id_user1;
    private String id_user2;
    private boolean friend;

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }

    public String getId_user1() {
        return id_user1;
    }

    public void setId_user1(String id_user1) {
        this.id_user1 = id_user1;
    }

    public String getId_user2() {
        return id_user2;
    }

    public void setId_user2(String id_user2) {
        this.id_user2 = id_user2;
    }
    
}
