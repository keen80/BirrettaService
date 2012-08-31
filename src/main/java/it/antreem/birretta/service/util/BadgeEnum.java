package it.antreem.birretta.service.util;

import java.util.HashMap;

/**
 *
 * @author gmorlini
 */
public enum BadgeEnum {
    //nameconvetion
            //dove utile l'ultimo elemento è valore di soglia per sbloccare il  badge
            //nome_QNT
    //      cod, name, category,idBadge, image
    DRINK_NUM_1(0,"Drinker",0,0,"i/m/a/g/e"),
    DRINK_NUM_5(0,"Drinker",0,1,"i/m/a/g/e"),
    DRINK_NUM_10(0,"Drinker",0,2,"i/m/a/g/e"),
    DRINK_NUM_50(0,"Drinker",0,3,"i/m/a/g/e"),
    DRINK_NUM_100(0,"Drinker",0,4,"i/m/a/g/e"),
    DRINKER_PUB_1(1,"PubDrinker",1,5,"i/m/a/g/e"),
    DRINKER_PUB_3(1,"PubDrinker",1,6,"i/m/a/g/e"),
    DRINKER_PUB_5(1,"PubDrinker",1,7,"i/m/a/g/e"),
    DRINKER_PUB_10(1,"PubDrinker",1,8,"i/m/a/g/e"),
    DRINKER_PUB_25(1,"PubDrinker",1,9,"i/m/a/g/e"),
    DRINKER_PIZZA_1(2,"PizzaDrinker",1,10,"i/m/a/g/e"),
    DRINKER_PIZZA_3(2,"PizzaDrinker",1,11,"i/m/a/g/e"),
    DRINKER_PIZZA_5(2,"PizzaDrinker",1,12,"i/m/a/g/e"),
    DRINKER_PIZZA_10(2,"PizzaDrinker",1,13,"i/m/a/g/e"),
    DRINKER_PIZZA_25(2,"PizzaDrinker",1,14,"i/m/a/g/e"),
    DRINKER_RISTO_1(3,"RistoDrinker",1,15,"i/m/a/g/e"),
    DRINKER_RISTO_3(3,"RistoDrinker",1,16,"i/m/a/g/e"),
    DRINKER_RISTO_5(3,"RistoDrinker",1,17,"i/m/a/g/e"),
    DRINKER_RISTO_10(3,"RistoDrinker",1,18,"i/m/a/g/e"),
    DRINKER_RISTO_25(3,"RistoDrinker",1,19,"i/m/a/g/e"),
    DRINKER_BAR_1(4,"BarDrinker",1,20,"i/m/a/g/e"),
    DRINKER_BAR_3(4,"BarDrinker",1,21,"i/m/a/g/e"),
    DRINKER_BAR_5(4,"BarDrinker",1,22,"i/m/a/g/e"),
    DRINKER_BAR_10(4,"BarDrinker",1,23,"i/m/a/g/e"),
    DRINKER_BAR_25(4,"BarDrinker",1,24,"i/m/a/g/e"),
    DRINKER_OPENSPACE_1(5,"OpenSpaceDrinker",2,25,"i/m/a/g/e"),
    DRINKER_OPENSPACE_3(5,"OpenSpaceDrinker",2,26,"i/m/a/g/e"),
    DRINKER_OPENSPACE_5(5,"OpenSpaceDrinker",2,27,"i/m/a/g/e"),
    DRINKER_OPENSPACE_10(5,"OpenSpaceDrinker",2,28,"i/m/a/g/e"),
    DRINKER_OPENSPACE_25(5,"OpenSpaceDrinker",2,29,"i/m/a/g/e"),
    DRINKER_HOME_1(6,"HomeDrinker",2,30,"i/m/a/g/e"),
    DRINKER_HOME_3(6,"HomeDrinker",2,31,"i/m/a/g/e"),
    DRINKER_HOME_5(6,"HomeDrinker",2,32,"i/m/a/g/e"),
    DRINKER_HOME_10(6,"HomeDrinker",2,33,"i/m/a/g/e"),
    DRINKER_HOME_25(6,"HomeDrinker",2,34,"i/m/a/g/e"),
    DRINKER_SPORT_1(7,"SportDrinker",2,35,"i/m/a/g/e"),
    DRINKER_SPORT_3(7,"SportDrinker",2,36,"i/m/a/g/e"),
    DRINKER_SPORT_5(7,"SportDrinker",2,37,"i/m/a/g/e"),
    DRINKER_SPORT_10(7,"SportDrinker",2,38,"i/m/a/g/e"),
    DRINKER_SPORT_25(7,"SportDrinker",2,39,"i/m/a/g/e"),
    DRINKER_TRAVELER(8,"TravelerDrinker",3,40,"i/m/a/g/e"),
    DRINK_NIGHT_2(9,"NightDrink",4,41,"i/m/a/g/e"),
    DRINK_NIGHT_3(9,"NightDrink",4,42,"i/m/a/g/e"),
    DRINK_NIGHT_4(9,"NightDrink",4,43,"i/m/a/g/e"),
    DRINK_NIGHT_5(9,"NightDrink",4,44,"i/m/a/g/e"),
    DRINK_NIGHT_10(9,"NightDrink",4,45,"i/m/a/g/e");
    
    private final int idBadge;
    private final int cod;
    private final int category;
    private final String name;
    private final String image;
    
    BadgeEnum(int cod,String name,int category,int idBadge,String image){
        this.idBadge=idBadge;
        this.cod=cod;
        this.category=category;
        this.image=image;
        this.name=name;
    }

    public int getCategory() {
        return category;
    }

    public int getCod() {
        return cod;
    }

    public int getIdBadge() {
        return idBadge;
    }

    public String getImage() {
        return image;
    }
    /*
    public String getName() {
        return name();//.replace("_", ".");
    }*/

    public String getName() {
        return name;
    }
    
    public int getQuantity() {
        try {//per essere flessibili a variazioni sul valore di soglia
            //nameconvetion
            //dove utile l'ultimo elemento è valore di soglia per sbloccare il  badge
            //nome_QNT
            int lenght =name().split("_").length;
            Integer num = new Integer(name().split("_")[lenght-1]);
            return num;
        } catch (Throwable t) {
            return 99999999;
        }
    }
}
