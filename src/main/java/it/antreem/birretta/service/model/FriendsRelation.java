package it.antreem.birretta.service.model;

/**
 * indica una relazione d'amicizia, viene creato ogni volta che viene fatta una richiesta
 * il campo friend valorizzato a true se il rapporto è stato accettato
 * @author gmorlini
 */
public class FriendsRelation extends MongoDBObject {
    private String idUser1;
    private String idUser2;
    private boolean friend;
    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }

    public String getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(String idUser1) {
        this.idUser1 = idUser1;
    }

    public String getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(String idUser2) {
        this.idUser2 = idUser2;
    }
    
}
