package it.antreem.birretta.service.util;

import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.dao.LocationCategoryDao;
import it.antreem.birretta.service.model.Badge;
import it.antreem.birretta.service.model.Drink;
import it.antreem.birretta.service.model.Location;
import it.antreem.birretta.service.model.LocationCategory;
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
        List<Drink> myDrinks = DaoFactory.getInstance().getDrinkDao().findDrinksByUsername(user.getUsername(), null);
        
        //BADGE_NAME = 0 - DRINK_NUM
        
        if(!oldBadges.contains(BadgeEnum.DRINK_NUM_100.getIdBadge())) 
            
            // se non ho già sbloccato il livello massimo controllo se con questo check si è raggiunto qualcosa di nuovo
           
        //si suppone che ad ogni checkIn venga effetuato un controllo, quindi non è necessario controllare valori maggiori di ma solo
        //il raggiungimento con questo check in del valore di soglia superiore
        {
            int countDrinksByUsername = myDrinks.size();
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
       
        //BADGE_NAME = 1 - DRINKER_PUB
         if(!oldBadges.contains(BadgeEnum.DRINKER_PUB_25))
         {
             int count=0;
             //Esempio individuazione in base a idLocationCategory
             for(Drink d: myDrinks)
             {
                 Location l=DaoFactory.getInstance().getLocationDao().findByIdLocation(d.getIdPlace());
                 if(l.getCategories().contains(LocationCategoryDao.ID_PUB))
                 {
                     count++;
                 }
             }
             if (count == BadgeEnum.DRINKER_PUB_1.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_PUB_1);
            } else if (count == BadgeEnum.DRINKER_PUB_3.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_PUB_3);
            } else if (count == BadgeEnum.DRINKER_PUB_5.getQuantity()) {
               updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_PUB_5);
            } else if (count == BadgeEnum.DRINKER_PUB_10.getQuantity()) {
               updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_PUB_10);
            } else if (count == BadgeEnum.DRINKER_PUB_25.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_PUB_25);
            }
         }
         //BADGE_NAME = 2 - DRINKER_PIZZA
         if(!oldBadges.contains(BadgeEnum.DRINKER_PIZZA_25))
         {
             int count=0;
             //Esempio individuazione in base a idLocationCategory
             for(Drink d: myDrinks)
             {
                 Location l=DaoFactory.getInstance().getLocationDao().findByIdLocation(d.getIdPlace());
                 if(l.getCategories().contains(LocationCategoryDao.ID_PIZZA))
                 {
                     count++;
                 }
             }
             if (count == BadgeEnum.DRINKER_PIZZA_1.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_PIZZA_1);
            } else if (count == BadgeEnum.DRINKER_PIZZA_3.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_PIZZA_3);
            } else if (count == BadgeEnum.DRINKER_PIZZA_5.getQuantity()) {
               updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_PIZZA_5);
            } else if (count == BadgeEnum.DRINKER_PIZZA_10.getQuantity()) {
               updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_PIZZA_10);
            } else if (count == BadgeEnum.DRINKER_PIZZA_25.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_PIZZA_25);
            }
         }
         
         //BADGE_NAME = 3 - DRINKER_RISTO
         if(!oldBadges.contains(BadgeEnum.DRINKER_RISTO_25))
         {
             //esempio di ricerca per "name like" in futuro da implementare attraverso sub-category
             String name="Restaurant";
             int count=0;
             count = countDrinkWithLocationCategoryNameLike(myDrinks, name);
             if (count == BadgeEnum.DRINKER_RISTO_1.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_RISTO_1);
            } else if (count == BadgeEnum.DRINKER_RISTO_3.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_RISTO_3);
            } else if (count == BadgeEnum.DRINKER_RISTO_5.getQuantity()) {
               updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_RISTO_5);
            } else if (count == BadgeEnum.DRINKER_RISTO_10.getQuantity()) {
               updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_RISTO_10);
            } else if (count == BadgeEnum.DRINKER_RISTO_25.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_RISTO_25);
            }
         }
        
         
         //BADGE_NAME = 4 - DRINKER_BAR    
         if(!oldBadges.contains(BadgeEnum.DRINKER_BAR_25))
         {
             //esempio di ricerca per "name like" in futuro da implementare attraverso sub-category
             String name="Bar";
             int count=0;
             count = countDrinkWithLocationCategoryNameLike(myDrinks, name);
             if (count == BadgeEnum.DRINKER_BAR_1.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_BAR_1);
            } else if (count == BadgeEnum.DRINKER_BAR_3.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_BAR_3);
            } else if (count == BadgeEnum.DRINKER_BAR_5.getQuantity()) {
               updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_BAR_5);
            } else if (count == BadgeEnum.DRINKER_BAR_10.getQuantity()) {
               updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_BAR_10);
            } else if (count == BadgeEnum.DRINKER_BAR_25.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_BAR_25);
            }
         }
        
         //BADGE_NAME = 5 - DRINKER_OPENSPACE
         
         //BADGE_NAME = 6 - DRINKER_HOME
         
         //BADGE_NAME = 5 - DRINKER_SPORT
         
         if(!oldBadges.contains(BadgeEnum.DRINKER_SPORT_25))
         {
             //esempio di ricerca per "name like" in futuro da implementare attraverso sub-category
             String name="Stadium";
             int count=0;
             count = countDrinkWithLocationCategoryNameLike(myDrinks, name);
             if (count == BadgeEnum.DRINKER_SPORT_1.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_SPORT_1);
            } else if (count == BadgeEnum.DRINKER_SPORT_3.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_SPORT_3);
            } else if (count == BadgeEnum.DRINKER_SPORT_5.getQuantity()) {
               updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_SPORT_5);
            } else if (count == BadgeEnum.DRINKER_SPORT_10.getQuantity()) {
               updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_SPORT_10);
            } else if (count == BadgeEnum.DRINKER_SPORT_25.getQuantity()) {
                updateListBadgeLocally(list, newBadges,BadgeEnum.DRINKER_SPORT_25);
            }
         }
         //
         
         //finiti i controlli update dell'user
        user.setBadges(newBadges);
        user.setCounterBadges(newBadges.size());
        DaoFactory.getInstance().getUserDao().updateUser(user);
        //  ^- si potrebbe creare un metodo che aggiorni sono il campo badges per maggiore efficienza
        
        return list;
    }

    protected int countDrinkWithLocationCategoryNameLike(List<Drink> myDrinks, String name) throws DaoException {
        //Esempio individuazione in base a idLocationCategory
        int count=0;
        for(Drink d: myDrinks)
        {
            Location l=DaoFactory.getInstance().getLocationDao().findByIdLocation(d.getIdPlace());
            for(String idCategory : l.getCategories())
            {
               LocationCategory lc = DaoFactory.getInstance().getLocationCategoryDao().findLocationCategoryByIdCategory(idCategory);
               if(lc.getName().contains(name))
               count++;
            }
        }
        return count;
    }
   /**
     * Questo metodo aggiorna le liste dei badge di un utente, dei nuovi badges sbloccati e salva su mongoDB
     * se non presente, per facilitare succesive operazioni di ricerca
     * @param e BadgeEnum badge in input da aggiungere alle liste
     * @param list lista di badge che dovrà poi essere restituita nella response del checkIn
     * @param newBadges lista di idBadges da abbinare all'user
     */
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
