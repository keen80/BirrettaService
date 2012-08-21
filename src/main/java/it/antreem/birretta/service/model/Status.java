package it.antreem.birretta.service.model;

/**
 * classe status realizzata a partire dal mock di hh
 * @author gmorlini
 */
public class Status {
      private boolean success= true;
      private String code="OK";
      private String msg="Status OK";

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
      
}
