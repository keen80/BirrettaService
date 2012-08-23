package it.antreem.birretta.service.util;

import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.model.Location;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.lang.Double;
import java.util.Date;
import java.util.LinkedHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
/**
 *
 * @author gmorlini
 */
public class JsonHandler
{
 //   private static final Log log = LogFactory.getLog(JsonHandler.class);
    final ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
    final static String URL_FOURSQUARE="https://api.foursquare.com/v2/venues/search";
    final static String CLIENT_ID="J3UL4ADU34TI55K55BUAKVZORNSCT1P1UO2DLYWR1GSB0Y5Q";
    final static String CLIENT_SECRET="FDQWFWTR0PX1M4KOBF2UXPFFS4JLVNOTZ1S0W0J11XMJNJOK";
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
        findLocationNear(44.355263,11.711079,10.0);
    }
    /*
     * Invocazione foursquare per avere località e salvataggio in mongoDB
     * @param radius in metri
     */
    public static ArrayList<Location> findLocationNear(Double lat, Double lon, Double radius) throws MalformedURLException, IOException
    {
        Date today=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String v = formatter.format(today);
        ArrayList<Location> list=new ArrayList<Location>();
            String urlStr=URL_FOURSQUARE + "?ll="+lat+","+lon+"&radius="+radius+"&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v="+v;
            // Create a URL for the desired page
            System.out.println(urlStr);
            URL url = new URL(urlStr);
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
                    Location l=new Location();
                    l.setIdLocation((String) venue.get("id"));
                    l.setName((String) venue.get("name"));
                    l.setUrl((String) venue.get("url"));
                    LinkedHashMap location = (LinkedHashMap) venue.get("location");
                    ArrayList<Double> pos=new ArrayList<Double>();
                    pos.add((Double) location.get("lat"));
                    pos.add((Double) location.get("lng"));
                    l.setPos(pos);
                    l.setAddress((String) location.get("address"));
                    l.setCity((String) location.get("city"));
                    l.setCountry((String) location.get("country"));
                    ArrayList<String> cats=new ArrayList<String>();
                    for (Object o : ((ArrayList) (venue.get("categories")))) {
                        LinkedHashMap category = (LinkedHashMap) o;
                        cats.add((String) category.get("name"));
                    }
                    l.setCategories(cats);
                   
                    //attenzione ai duplicati:to do usare id
                    if(DaoFactory.getInstance().getLocationDao().findLocationsByNameLike(l.getName()).isEmpty())
                         DaoFactory.getInstance().getLocationDao().saveLocation(l);
                         
                    list.add(l);
                }
            
            }
            in.close();
       
        return list;
    }
    
}