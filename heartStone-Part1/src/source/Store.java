package source;

import source.entities.Card;
import Game.Game;

import java.io.*;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import source.entities.Hero;

public class Store {
    private Player player;
public Store(Player player)
{
    this.player =player;
}



    public boolean buyCard(String name)
    {try{
if(Game.player.getCoins()>=Card.getCard(name).getCardCost())
        {
            Game.player.addWholeCards(name);
            Game.player.setCoins(Game.player.getCoins()-Card.getCard(name).getCardCost());
            return true;
        }}
        catch(Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
return false;
    }
    public boolean sellCard(String name)
    {try{
       if(!cansell(name))
       {
           return false;
       }
        if(this.player.getrWholeCards().contains(name))
        {
            this.player.getrWholeCards().remove(name);
            this.player.setCoins(this.player.getCoins()+Card.getCard(name).getCardCost());
        }}
        catch (Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );
return false;
        }
return true;
    }
    public  String toStringNotExist()
    {

        String st="";
        for(int i=0;i< notExist().size();i++)
        {
            try (FileReader reader = new FileReader("cards\\"+ notExist().get(i)+".json"))
            {
                Object obj = new JSONParser().parse(reader);

                reader.close();
                JSONObject jo = (JSONObject) obj;
                st+="----------------- "+notExist().get(i)+" -----------------------\n";
                st+=makeDes(jo);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
catch (Exception e)
{
    Log.LOGGER.finest("error  "+e.getStackTrace() );

}
        }
        return st;
    }
    private boolean cansell(String name)
    {try{
        for(int i=0;i<this.player.getOpenedHeros().size();i++ )
        {
            for(int j=0;j<15;j++)
            {
                if(Hero.getHero(this.player.getOpenedHeros().get(i)).getCards()[j]!=null){
                if(Hero.getHero(this.player.getOpenedHeros().get(i)).getCards()[j].equals(name))
                    return false;}
            }
        }}
        catch(Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
        return true;
    }
    private ArrayList<String> notExist()
    {
        ArrayList<String> notexist =new ArrayList<String>();

for(int i=0;i< cards().size();i++)
{
    try (FileReader reader = new FileReader("cards\\"+cards().get(i)+".json"))
    {
        Object obj = new JSONParser().parse(reader);

        reader.close();
        JSONObject jo = (JSONObject) obj;
      if(!this.player.getrWholeCards().contains(cards().get(i)))
         notexist.add(cards().get(i));
    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
    catch (Exception e)
    {
        Log.LOGGER.finest("error  "+e.getStackTrace());

    }
    }
return notexist;
    }
    public ArrayList<String> getCards()
    {
        return  cards();
    }
    private  ArrayList<String> cards() {
        ArrayList<String> cards = new ArrayList<String>();
        try {
            File file = new File("cards\\cards.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
//            StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
            String line;
            while ((line = br.readLine()) != null) {
                cards.add(line);
//                sb.append(line);      //appends line to string buffer
//                sb.append("\n");     //line feed
            }
            fr.close();    //closes the stream and release the resources
//            System.out.println("Contents of File: ");
//            System.out.println(sb.toString());   //returns a string that textually represents the object
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
        return cards;
    }
    private String makeDes( JSONObject jo)
    {
        String dec="";
        try {
            if (((String) jo.get("cardType")).equals("MINION")) {
                dec = dec + "type:" + ((String) jo.get("cardType")) + "\n";
                dec += "name:" + ((String) jo.get("name")) + "\n";
                dec += "cardCost:" + (jo.get("cardCost")) + "\n";
                dec += "manaCost:" + (jo.get("manaCost")) + "\n";
                dec += "rarity:" + ((String) jo.get("rarity")) + "\n";
                dec += "description:" + ((String) jo.get("description")) + "\n";
                dec += "Attack:" + (jo.get("Attack")) + "\n";
                dec += "HP:" + (jo.get("HP")) + "\n";
            }
            if (((String) jo.get("cardType")).equals("SPELL")) {
                dec = dec + "name:" + ((String) jo.get("name")) + "\n";
                dec = dec + "description:" + ((String) jo.get("description")) + "\n";
                dec = dec + "cardType:" + ((String) jo.get("cardType")) + "\n";
                dec = dec + "rarity:" + ((String) jo.get("rarity")) + "\n";
                dec = dec + "heroClass:" + ((String) jo.get("heroClass")) + "\n";
                dec = dec + "manaCost:" + (jo.get("manaCost")) + "\n";
                dec = dec + "cardCost:" + (jo.get("cardCost")) + "\n";

            }
        }
        catch (Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
return dec;
    }
public ArrayList<String> sellableCards()
{
    ArrayList<String> sell=new ArrayList<>();try{
    for(int i=0;i<this.player.getrWholeCards().size();i++)
    {
        if(cansell(this.player.getrWholeCards().get(i)))
            sell.add(this.player.getrWholeCards().get(i));
    }}
    catch (Exception e)
    {
        Log.LOGGER.finest("error  "+e.getStackTrace() );

    }
    return sell;
}
public ArrayList<String> canBuy()
{
    ArrayList<String> can=new ArrayList<>();
    try{
for(int i=0;i<cards().size();i++)
{
    if(this.player.getCoins()>= Card.getCard(cards().get(i)).getCardCost())
    {
        can.add(cards().get(i));
    }
}}
    catch (Exception e)
    {
        Log.LOGGER.finest("error  "+e.getStackTrace() );

    }
    return can;
}
}
