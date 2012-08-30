package it.antreem.birretta.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jboss.resteasy.annotations.providers.*;

/**
 *
 * @author gmorlini
 */
public class DateAdapter {
    @NoJackson
    private Date date;
    
     public DateAdapter()
     {}
    public DateAdapter(String str) throws ParseException {
        this.date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(str);
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        if (date != null) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date);
        } else {
            return null;
        }
    }
    
}
