package it.antreem.birretta.service.model;

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
        this.badge = badge;
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
        this.notification = notification;
    }
    class Notification {
        private String code;
        private int type;
        private String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
        
    }

    class Badge {
        private String code;
        private int type;
        private String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
        
    }
}
