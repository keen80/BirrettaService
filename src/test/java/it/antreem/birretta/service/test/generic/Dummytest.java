package it.antreem.birretta.service.test.generic;

import it.antreem.birretta.service.BirrettaService;
import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.dto.ErrorDTO;
import it.antreem.birretta.service.dto.GenericResultDTO;
import it.antreem.birretta.service.model.Address;
import it.antreem.birretta.service.model.Beer;
import it.antreem.birretta.service.model.LocType;
import it.antreem.birretta.service.model.Location;
import it.antreem.birretta.service.util.Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 *
 * @author gmorlini
 */
public class Dummytest 
{
    private final static Logger log = Logger.getLogger(Dummytest.class);
    
    @Test
    public void dummytest() throws FileNotFoundException, IOException
    {
      /*  LocType lt=new LocType();
        lt.setCod("MorlinsCode");
        lt.setDesc("Morlins Pub  and Grill for all");
        if(DaoFactory.getInstance().getLocTypeDao().findLocTypeByCod(lt.getCod())==null)
          DaoFactory.getInstance().getLocTypeDao().saveLocType(lt);
        BirrettaService service= new BirrettaService();
        Location l= new Location();
        Address a= new Address();
        a.setCap("54345");
        a.setNum(new Integer("46"));
        a.setState("IT");
        a.setStreet("Via della vittoria");
      //  l.setAddress(a);
        l.setUrl("mio località");
        //l.setIdLocType(lt.getCod());
        ArrayList<Double> pos=new ArrayList<Double>();
        pos.add(new Double("10"));
        pos.add(new Double("10"));
        l.setPos(pos);
        l.setName("MorlinsRumGrill");
        Response resp= service.insertLoc(l);
        log.debug("inserito: "+ l + "response. " + (resp.getEntity()));
    //    log.debug("inserito: "+ l + "response. " + ((ErrorDTO)resp.getEntity()).getError().getTitle()+" : " +((ErrorDTO)resp.getEntity()).getError().getDesc());
        log.debug("inserito: "+ l + "response. " + ((GenericResultDTO)resp.getEntity()).getMessage());
    */
          HashMap<String,Integer> beerStyle = new  HashMap<String,Integer>();
        HashMap<String,Integer> beerType = new  HashMap<String,Integer>();
  //      HashMap<String,Integer> beerColor = new HashMap<String, Integer>();
        //mapping nazioni
     //   BufferedReader nations = new BufferedReader(new FileReader("Birre.csv"));
        BufferedReader in = new BufferedReader(new FileReader("Birre.csv"));
        BufferedWriter out = new BufferedWriter(new FileWriter("todo.csv"));
            String str;
            int iStyle=0;
            int iType=0;
            //read nome colonne
            String str1= in.readLine();
            out.write(str1+"\n");
            while ((str = in.readLine()) != null) {
                String[] array=str.split(";");
                Beer b = new Beer();
                if(array[4].length()==0 || array[4].equals("SCO") || array[4].equals("WAL")|| array[4].equals("NIRL")||array[4].equals("YUG") || array[4].length()==2 )
                {
                b.setNationality(array[4]);
               
                b.setName(array[0]);
                b.setBrewery(array[1]);
                if(beerStyle.containsKey(array[2]))
                {
                    b.setBeerstyle(beerStyle.get(array[2]));
                }else if(array[2].length()>0)
                {
                     beerStyle.put(array[2], iStyle);
                     b.setBeerstyle(iStyle++);
                }
                
                if(array[3].length()>0 && beerType.containsKey(array[3]))
                {
                    b.setBeertype(beerType.get(array[3]));
                }else if(array[3].length()>0)
                {
                     beerType.put(array[3], iType);
                     b.setBeertype(iType++);
                }
                //mapping
                //gradazione non obbligatoria
               if(array.length==6)
                b.setGrad(array[5]);
                b.setInsertedOn(new Date());
                System.out.println("inserimento birra nome: "+b.getName()+ "\n brewery: "+b.getBrewery()+" \n style: "+b.getBeerstyle()+"\n type: "+b.getBeertype()+"\n grad: "+b.getGrad());   
                DaoFactory.getInstance().getBeerDao().saveBeer(b);
                
                }
                else
                {//nazione non standard
                    out.write(str+"\n");
                }
             }
              
        generateFile("BeerStyle.txt", beerStyle,"beerstyles");
        generateFile("BeerType.txt", beerType,"beertype");
     //   generateFile("BeerColor.txt", beerStyle, "beercolor");
        in.close();
        out.close();
    }

    protected void generateFile(String filename, HashMap<String, Integer> beerStyle,String name) throws IOException {
        File fileStyle = new File(filename);
      
        FileWriter file = new FileWriter(fileStyle);
        BufferedWriter writer = new BufferedWriter(file);
       
        writer.write("\""+name+"\": [\n");
        // System.out.print("\"locationCategory\": [\n");
        for (Entry<String, Integer> entry : beerStyle.entrySet()) {

            //{ "text": "parco", "value": 0},
            writer.write("{\"text\": \"" + entry.getKey()+ "\", \"value\": \"" + entry.getValue() + "\" },\n");
            //    System.out.print("{\"text\": \"" + loc.getName() + "\", \"value\":" + loc.getIdCategory() + "},\n");
            //rimuovere virgola da ultima category
        }
        writer.write("],\n");
        //  System.out.print("],\n");
        System.out.println("save file to " + fileStyle.getAbsolutePath());
        writer.close();
    }
}
