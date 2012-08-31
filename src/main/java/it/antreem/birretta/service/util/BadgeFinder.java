package it.antreem.birretta.service.util;

import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.model.Badge;
import it.antreem.birretta.service.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gmorlini
 */
public class BadgeFinder {
    public List<Badge> checkNewBadge(User user)
    {
        ArrayList<Badge> list= new ArrayList<Badge>();
        List<Integer> oldBadges = user.getBadges();
        List<Integer> newBadges = new ArrayList<Integer>();
        newBadges.addAll(oldBadges);
        
        
        
        //BADGE_NAME = 0 - DRINK_NUM
        
        if(!oldBadges.contains(BadgeEnum.DRINK_NUM_100.getIdBadge())) 
            
            // se non ho già sbloccato il livello massimo controllo se con questo check si è raggiunto qualcosa di nuovo
           
        //si suppone che ad ogni checkIn venga effetuato un controllo, quindi non è necessario controllare valori maggiori di ma solo
        //il raggiungimento con questo check in del valore di soglia superiore
        {
            int countDrinksByUsername = DaoFactory.getInstance().getDrinkDao().countDrinksByUsername(user.getUsername());
            if (countDrinksByUsername == BadgeEnum.DRINK_NUM_1.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINK_NUM_1);
            } else if (countDrinksByUsername == BadgeEnum.DRINK_NUM_5.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINK_NUM_5);
            } else if (countDrinksByUsername == BadgeEnum.DRINK_NUM_10.getQuantity()) {
               updateListBadgeLocally(list, newBadges,BadgeEnum.DRINK_NUM_10);
            } else if (countDrinksByUsername == BadgeEnum.DRINK_NUM_50.getQuantity()) {
               updateListBadgeLocally(list, newBadges,BadgeEnum.DRINK_NUM_50);
            } else if (countDrinksByUsername == BadgeEnum.DRINK_NUM_100.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINK_NUM_100);
            }
        }   
        
        //BADGE_NAME = 1 - DRINK_PUB
        
        //finiti i controlli update dell'user
        user.setBadges(newBadges);
        user.setCounterBadges(newBadges.size());
        DaoFactory.getInstance().getUserDao().updateUser(user);
        //  ^- si potrebbe creare un metodo che aggiorni sono il campo badges per maggiore efficienza
        
        return list;
    }

    private void updateListBadgeLocally(ArrayList<Badge> list, List<Integer> newBadges,BadgeEnum e) {
        //update lista da restituire per indicare nuovi badge sbloccati
        list.add(getBadgeFromEnum(e));
        //aggiornamento lista badge posseduti da un utente
        newBadges.add(e.getIdBadge());
        //salvataggio badge
        if(DaoFactory.getInstance().getBadgeDao().findByIdBadge(e.getIdBadge())==null)
            DaoFactory.getInstance().getBadgeDao().saveBadge(getBadgeFromEnum(e));
    }
    
    private Badge getBadgeFromEnum(BadgeEnum e)
    {
        Badge b=new Badge();
        b.setCategory(e.getCategory());
        b.setCod(e.getCod());
        b.setIdBadge(e.getIdBadge());
        b.setImage(e.getImage());
        b.setName(e.getName());
        return b;
    }
}
