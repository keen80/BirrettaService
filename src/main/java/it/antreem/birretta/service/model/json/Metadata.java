package it.antreem.birretta.service.model.json;

import it.antreem.birretta.service.model.Badge;
import it.antreem.birretta.service.model.Notification;
import java.util.ArrayList;
import java.util.List;

/**
 * classe metadata realizzata a partire dal mock di hh
 * @author gmorlini
 */
public class Metadata {
    //TODO: occorrerà utilizzare
    private List<Notification> notification=new ArrayList<Notification>();
    private List<Badge> badge=new ArrayList<Badge>();

    public List<Badge> getBadge() {
        return badge;
    }

    public void setBadge(List<Badge> badge) {
        this.badge = badge;
    }
  /*  public void setBadge(String code,int type,String msg) {
        Badge b=new Badge();
        b.setCode(code);
        b.setType(type);
        b.setMsg(msg);
        getBadge().add(b);
    }*/
    public List<Notification> getNotification() {
        return notification;
    }

    public void setNotification(List<Notification> notification) {
        this.notification = notification;
    }
    /*public void setNotification(String code,int type,String msg) {
        SimpleNotification n=new SimpleNotification();
        n.setCode(code);
        n.setType(type);
        n.setMsg(msg);
        getNotification().add(n);
    }*/
}
