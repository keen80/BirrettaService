package it.antreem.birretta.service.util;

import it.antreem.birretta.service.model.Activity;
import java.util.Comparator;

/**
 *
 * @author gmorlini
 */
public class ActivityComparator implements Comparator{
/* long n1 = s1.getTime();
        long n2 = s2.getTime();
        if (n1 &lt; n2) return -1;
        else if (n1 &gt; n2) return 1;
        else return 0;
 */
    @Override
    public int compare(Object o1, Object o2) {
        Activity a1=(Activity) o1;
        Activity a2=(Activity) o2;
        int result=a1.getDate().compareTo(a2.getDate());
        if(result==0)
            return 0;
        if(result>0)
            return -1;
        if(result<0)
            return 1;
        else //non arrva mai qui 
            return a1.getDate().compareTo(a2.getDate());
    }
    
}
