package it.antreem.birretta.service.dto;

/**
 *
 * @author alessio
 */
public class ErrorDTO 
{
    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
     
    public static class Error
    {
        private String code;
        private String title;
        private String desc;
        private Object actionType;

        public Object getActionType() {
            return actionType;
        }

        public void setActionType(Object actionType) {
            this.actionType = actionType;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
