package it.antreem.birretta.service.util;

import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.model.Beer;
import it.antreem.birretta.service.model.Location;
import it.antreem.birretta.service.model.LocationCategory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.lang.Double;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.logging.*;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author gmorlini
 */
public class FoursquareJsonProxy {
    //  private static final Log log = LogFactory.getLog(FoursquareJsonProxy.class);

    final ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
    final static String URL_FOURSQUARE = "https://api.foursquare.com/v2/venues/search";
    final static String CLIENT_ID = "J3UL4ADU34TI55K55BUAKVZORNSCT1P1UO2DLYWR1GSB0Y5Q";
    final static String CLIENT_SECRET = "FDQWFWTR0PX1M4KOBF2UXPFFS4JLVNOTZ1S0W0J11XMJNJOK";

    public static void main(String args[]) throws MalformedURLException, IOException {
        /*try {
        // Create a URL for the desired page
        URL url = new URL(URL_FOURSQUARE + "?ll=44.355263,11.711079&radius=100000&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v=20120823");
        System.out.println("request: "+URL_FOURSQUARE);
        // Read all the text returned by the server
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String str;
        while ((str = in.readLine()) != null) {
        // str is one line of text; readLine() strips the newline character(s)
        System.out.println("response: \n"+str);
        DynaBeen dynaBeen = (DynaBeen) PojoMapper.fromJson(str, DynaBeen.class);
        for (Object obj : ((ArrayList) ((LinkedHashMap) dynaBeen.any().get("response")).get("venues"))) {
        LinkedHashMap venue = (LinkedHashMap) obj;
        System.out.println((String) venue.get("id"));
        System.out.println((String) venue.get("name"));
        System.out.println((String) venue.get("url"));
        LinkedHashMap location = (LinkedHashMap) venue.get("location");
        System.out.println((Double) location.get("lat"));
        System.out.println((Double) location.get("lng"));
        System.out.println((String) location.get("address"));
        System.out.println((String) location.get("city"));
        System.out.println((String) location.get("country"));
        
        for (Object o : ((ArrayList) (venue.get("categories")))) {
        LinkedHashMap category = (LinkedHashMap) o;
        System.out.println("-- " + (String) category.get("name"));
        }
        }
        
        }
        in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }*/
        HashMap<String, Integer> beerStyle = new HashMap<String, Integer>();
        HashMap<String, Integer> beerType = new HashMap<String, Integer>();

        //mapping nazioni
        //   BufferedReader nations = new BufferedReader(new FileReader("Birre.csv"));
        BufferedReader in = new BufferedReader(new FileReader("Birre.csv"));
        BufferedWriter out = new BufferedWriter(new FileWriter("todo.csv"));
        String str;
        int iStyle = 0;
        int iType = 0;
        //read nome colonne
        String str1 = in.readLine();
        out.write(str1 + "\n");
        while ((str = in.readLine()) != null) {
            String[] array = str.split(";");
            Beer b = new Beer();
            if (array[4].length() == 2) {
                b.setNationality(array[4]);

                b.setName(array[0]);
                b.setBrewery(array[1]);
                if (beerStyle.containsKey(array[2])) {
                    b.setBeerstyle(beerStyle.get(array[2]));
                } else if (array[2].length() > 0) {
                    beerStyle.put(array[2], iStyle);
                    b.setBeerstyle(iStyle++);
                }

                if (array[3].length() > 0 && beerType.containsKey(array[3])) {
                    b.setBeertype(beerType.get(array[3]));
                } else if (array[3].length() > 0) {
                    beerType.put(array[3], iType);
                    b.setBeerstyle(iType++);
                }
                beerType.put(array[3], iStyle);
                b.setBeertype(iStyle++);
                //mapping

                b.setGrad(array[5]);
                b.setInsertedOn(new Date());
                System.out.println("inserimento birra nome: " + b.getName() + "\n brewery: " + b.getBrewery() + " \n style: " + b.getBeerstyle() + "\n type: " + b.getBeertype() + "\n grad: " + b.getGrad());
                DaoFactory.getInstance().getBeerDao().saveBeer(b);

            } else {//nazione non standard
                out.write(str + "\n");
            }
        }






        //imola
        //findLocationNear(44.355263,11.711079,10.0);
    }
    /*
     * Invocazione foursquare per avere località e salvataggio in mongoDB
     * @param radius in metri
     */

    public static ArrayList<Location> findLocationNear(Double lat, Double lon, Double radius) throws MalformedURLException, IOException {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String v = formatter.format(today);
        ArrayList<Location> list = new ArrayList<Location>();
        String urlStr = URL_FOURSQUARE + "?ll=" + lat + "," + lon + "&radius=" + radius + "&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v=" + v;
        // Create a URL for the desired page
        System.out.println(urlStr);
        URL url = new URL(urlStr);
        System.out.println("request: " + URL_FOURSQUARE);
        // Read all the text returned by the server
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String str;
        while ((str = in.readLine()) != null) {
            // str is one line of text; readLine() strips the newline character(s)
          //  System.out.println("response: \n" + str);
            DynaBeen dynaBeen = (DynaBeen) PojoMapper.fromJson(str, DynaBeen.class);
            for (Object obj : ((ArrayList) ((LinkedHashMap) dynaBeen.any().get("response")).get("venues"))) {
                LinkedHashMap venue = (LinkedHashMap) obj;
                Location l = new Location();
                l.setIdLocation((String) venue.get("id").toString());
                l.setName((String) venue.get("name").toString());
                l.setUrl((String) venue.get("url"));
                LinkedHashMap location = (LinkedHashMap) venue.get("location");
                ArrayList<Double> pos = new ArrayList<Double>();
                pos.add((Double) location.get("lat"));
                pos.add((Double) location.get("lng"));
                l.setPos(pos);
                l.setAddress((String) location.get("address"));
                l.setCity((String) location.get("city"));
                l.setCountry((String) location.get("country"));
                l.setCc((String) location.get("cc"));
                ArrayList<String> cats = new ArrayList<String>();
                for (Object o : ((ArrayList) (venue.get("categories")))) {
                    LinkedHashMap category = (LinkedHashMap) o;
                    String idCategory = (String) category.get("id");
                    LocationCategory cat = DaoFactory.getInstance().getLocationCategoryDao().findLocationCategoryByIdCategory(idCategory);
                    if (cat == null) {
                        //nuova categoria, inserimento nel db
                        LocationCategory locCat = new LocationCategory();
                        locCat.setIdCategory(idCategory);
                        locCat.setName(category.get("name").toString());
                        locCat.setPluralName((String) category.get("pluralName"));
                        locCat.setShortName((String) category.get("shortName"));
                        DaoFactory.getInstance().getLocationCategoryDao().saveLocationCategory(locCat);

                    }
                    cats.add(idCategory);
                    //TOTO: aggiungere gestione categorie
                }
                l.setCategories(cats);

                //attenzione ai duplicati:to do usare id
                if (DaoFactory.getInstance().getLocationDao().findByIdLocation(l.getIdLocation()) == null) {
                    DaoFactory.getInstance().getLocationDao().saveLocation(l);
                } else {
                    DaoFactory.getInstance().getLocationDao().updateLocation(l);
                }

                list.add(l);
            }

        }
        in.close();
        //scrittura file con associazioni id-nome categoria
        File fileOut=new File("LocationCategory.txt");
                
        FileWriter file=new FileWriter(fileOut);
        BufferedWriter writer = new BufferedWriter(file);
        List<LocationCategory> findAll = DaoFactory.getInstance().getLocationCategoryDao().findAll();
        writer.write("\"locationCategory\": [\n");
       // System.out.print("\"locationCategory\": [\n");
        int i = 0;
        for (LocationCategory loc : findAll) {

            //{ "text": "parco", "value": 0},
            writer.write("{\"text\": \"" + loc.getName() + "\", \"value\": \"" + loc.getIdCategory()+ "\" },\n");
        //    System.out.print("{\"text\": \"" + loc.getName() + "\", \"value\":" + loc.getIdCategory() + "},\n");
            //rimuovere virgola da ultima category
        }
        writer.write("],\n");
      //  System.out.print("],\n");
        System.out.println("save file to "+fileOut.getAbsolutePath());
        writer.close();
        return list;
    }
}