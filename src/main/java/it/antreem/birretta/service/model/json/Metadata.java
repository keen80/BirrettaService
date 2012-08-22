package it.antreem.birretta.service.model.json;

/**
 * classe metadata realizzata a partire dal mock di hh
 * @author gmorlini
 */
public class Metadata {
    private Notification notification;
    private Badge badge;

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
    public void setBadge(String code,int type,String msg) {
        Badge b=new Badge();
        b.setCode(code);
        b.setType(type);
        b.setMsg(msg);
        this.badge = b;
    }
    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
    public void setNotification(String code,int type,String msg) {
        Notification n=new Notification();
        n.setCode(code);
        n.setType(type);
        n.setMsg(msg);
        this.notification = n;
    }
}
