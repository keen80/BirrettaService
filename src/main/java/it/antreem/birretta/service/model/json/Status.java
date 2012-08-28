package it.antreem.birretta.service.model.json;

/**
 * classe status realizzata a partire dal mock di hh
 *
 * @author gmorlini
 */
public class Status {

    private boolean success = true;
    private int code = 0;
    private String msg = "Status OK";

    public Status() {
    }

    public Status(int code, String msg, Boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
