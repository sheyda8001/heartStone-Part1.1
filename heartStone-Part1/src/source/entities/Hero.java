package source.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import source.*;
public abstract class Hero {
    public abstract String[] getCards();
    public abstract String[] getSpecialCards();
    public abstract boolean setCards(String name);
    public abstract boolean removeCards(String name);
    public abstract boolean isopen();
    public abstract String getHeroName();
    private static ArrayList<String> AllHeros =new ArrayList<String>();
    public static ArrayList<String> getAllHero()
    {
        return AllHeros;
    }
    public static void showHeros()
    {
        try {
            File file = new File("heros\\heros.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while ((line = br.readLine()) != null) {
                AllHeros.add(line);
               System.out.print(line+"   ");
            }
            fr.close();    //closes the stream and release the resources

        } catch (IOException e) {
            e.printStackTrace();
            Log.LOGGER.finest("error "+e.getStackTrace());

        }
    }
    public static ArrayList<String> getallHeros()
    {ArrayList<String> sttt=new ArrayList<>();
        try {
            File file = new File("heros\\heros.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while ((line = br.readLine()) != null) {
                AllHeros.add(line);
              sttt.add(line);
            }
            fr.close();    //closes the stream and release the resources

        } catch (IOException e) {
            e.printStackTrace();
            Log.LOGGER.finest("error "+e.getStackTrace());

        }
        return sttt;
    }
    public static Hero getHero(String name)
    {
        Hero h = null;
        if(name.equals("source.Cards.Mage"))
        {
            h=new Mage();
            return h;
        }
        if(name.equals("source.Cards.Warlock"))
        {
            h=new Warlock();
            return h;
        }
        if(name.equals("source.Cards.Rouge"))
        {
            h=new Rouge();
            return h;
        }
        return h;
    }

}
