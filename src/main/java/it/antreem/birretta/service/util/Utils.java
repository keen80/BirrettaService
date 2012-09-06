package it.antreem.birretta.service.util;

import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.dao.impl.AbstractMongoDao;
import it.antreem.birretta.service.dto.ErrorDTO;
import it.antreem.birretta.service.model.Badge;
import it.antreem.birretta.service.model.Beer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Varie statiche ed eventuali.
 * 
 * @author alessio
 */
public class Utils 
{
    private static final Log log = LogFactory.getLog(Utils.class);
    
    public final static String SALT = "BirrettaServiceSaltXXX";
    
    public static String SHAsum(byte[] convertme)
    {
        MessageDigest md;
        
        try
        {
            md = MessageDigest.getInstance("SHA-1"); 
        }
        catch(NoSuchAlgorithmException ex)
        {
            log.error("Impossibile applicare algoritmo di hashing" + ex.getLocalizedMessage(), ex);
            return null;
        }
        
        return byteArray2Hex(md.digest(convertme));
    }

    private static String byteArray2Hex(final byte[] hash) 
    {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
    
    public static ErrorDTO createError (ErrorCodes err, Object... actionType)
    {
        ErrorDTO error = new ErrorDTO();
        ErrorDTO.Error _e = new ErrorDTO.Error();
        _e.setCode(err.getTitle());
        _e.setDesc(err.getMessage());
        _e.setTitle(err.getTitle());
        _e.setActionType((actionType != null && actionType.length > 0) ? actionType[0] : null);
        error.setError(_e);
        
        return error;
    }
    public static void parseBirreCsv() throws FileNotFoundException, IOException
    {
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
    
    protected static void generateFile(String filename, HashMap<String, Integer> beerStyle,String name) throws IOException {
        File fileStyle = new File(filename);
      
        FileWriter file = new FileWriter(fileStyle);
        BufferedWriter writer = new BufferedWriter(file);
       
        writer.write("\""+name+"\": [\n");
        // System.out.print("\"locationCategory\": [\n");
        for (Entry<String, Integer> entry : beerStyle.entrySet()) {

            //{ "text": "parco", "value": 0},
            writer.write("{\"text\": \"" + entry.getKey()+ "\", \"value\": " + entry.getValue() + " },\n");
            //    System.out.print("{\"text\": \"" + loc.getName() + "\", \"value\":" + loc.getIdCategory() + "},\n");
            //rimuovere virgola da ultima category
        }
        writer.write("],\n");
        //  System.out.print("],\n");
        System.out.println("save file to " + fileStyle.getAbsolutePath());
        writer.close();
    }
}
