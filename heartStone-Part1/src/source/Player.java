package source;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import source.entities.Hero;
import source.entities.Mage;
import source.entities.Rouge;
import source.entities.Warlock;

public  class Player {
    private long coins;
    private long id;
    private Store st=new Store(this);
    private ArrayList<String> wholeCard=new ArrayList<>();
    private ArrayList<String> nowCard=new ArrayList<>();
    private String user,pass;
    private int mana=0;
    private Hero selectedHero;
    private ArrayList<String> openedHeros=new ArrayList<String>();
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Player(String user,String pass)
    {
        this.user=user;
        this.pass=pass;
        readFile();
        LOGGER.setLevel(Level.FINEST);
        LOGGER.fine("source.SignIn:"+user);

    }
    public Store getSt()
    {
        return this.st;
    }
    public void addWholeCards(String name)
    {
        this.wholeCard.add(name);
    }
public void setCoins(int coin)
{
    this.coins=coin;
}
    public int getCoins()
    {
        return (int)this.coins;
    }
//    public String getNowCardsToString()
//    {
//        String st="";
//for(int i=0;i<getNowCards().size();i++)
//{
//    st+=getNowCards().get(i)+"    ";
//}
//return st;
//    }
    public int getNowCardsSize()
    {int num=0;try{
        for(int i=0;i<getNowCards().size();i++)
        {
            if(getNowCards().get(i)!=null) num++;
        }}
        catch(Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
      return num;
    }
    public ArrayList<String> getNowCards()
    {
        return this.nowCard;
    }
    public ArrayList<String> getOpenedHeros()
    {
        return  this.openedHeros;
    }
    public Hero getSelectedHero()
    {
        return this.selectedHero;
    }
    public boolean setHero(String hero)
    {
if(hero.equals("source.Cards.Mage")&&this.openedHeros.contains(hero))
{try{
            this.selectedHero=new Mage();

           setNowCard();}
           catch(Exception e)
           {
               Log.LOGGER.finest("error  "+e.getStackTrace() );
           }
           return true;
}
if(hero.equals("source.Cards.Warlock")&&this.openedHeros.contains(hero))
{try{
    this.selectedHero=new Warlock();
   setNowCard();}
   catch (Exception e)
   {
       Log.LOGGER.finest("error  "+e.getStackTrace() );

   }
    return true;
}
if(hero.equals("source.Cards.Rouge")&&this.openedHeros.contains(hero))
{try{
   this. selectedHero=new Rouge();
    setNowCard();}
    catch (Exception e)
    {
        Log.LOGGER.finest("error  "+e.getStackTrace() );

    }
    return true;
}
return false;
    }

public String getNowCardsToString()
{String st="";
try{
    for(int i=0;i<getNowCards().size();i++)
    {
        if(getNowCards().get(i)!=null)
        {
            st+=getNowCards().get(i)+"    ";
        }
    }}
catch(Exception e)
{
    Log.LOGGER.finest("error  "+e.getStackTrace() );
}
    return st;
}

    private void setNowCard() {
        for(int i=0;i<15;i++)
        {
            this.nowCard.add(this.selectedHero.getCards()[i]);
        }
    }

    public String getSelectedHeroName()
    {
        if(selectedHero!=null)
        return selectedHero.getHeroName();
        else{
            return null;
        }
    }
    public ArrayList<String> getrWholeCards()
    {
        return this.wholeCard;
    }
    public String wholeCardsToString()
    {
        return "your wholecards are"+wholeCard.toString();
    }
    public String getUser()
    {
        return this.user;
    }
    public  Player getPlayer()
    {
        return this;
    }
public void setMana(int y)
{
    mana-=y;
}
public int getMana()
{
    return mana;
}
public int getid()
{
    return (int)this.id;
}
private void readFile() {
    try (FileReader reader = new FileReader("usersFile\\" + user + ".json")) {
        FileReader reade=   new FileReader("usersFile\\" + user + ".json");
            //Parsing the contents of the JSON file
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(reade);
// parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(reader);
        reader.close();
        reade.close();
        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;
//      String st= (String) jsonObject.get("ID");
      this.id=(long)jsonObject.get("ID");
            JSONArray jsonArray = (JSONArray) jsonObject.get("WholeCards");
      // JSONArray jsonArray = (JSONArray) jo.get("WholeCards");
//        for(int i=0;i<jsonArray.size();i++)
//        {
//            this.wholeCard.add((String) jsonArray.get(i));
//        }
            Iterator<String> iterator = jsonArray.iterator();
            while(iterator.hasNext()) {
                this.wholeCard.add((iterator.next()));
            }
        jsonArray = (JSONArray) jsonObject.get("OpenedHeros");
       iterator = jsonArray.iterator();
        while(iterator.hasNext()) {
            this.openedHeros.add((iterator.next()));
        }
        jsonArray=(JSONArray) jo.get("NowCards");
        for(int i=0;i<jsonArray.size();i++)
        {
            this.nowCard.add((String) jsonArray.get(i));
        }
    } catch (ParseException e) {
        e.printStackTrace();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    catch (Exception e)
    {
        Log.LOGGER.finest("error  "+e.getStackTrace() );

    }
}

            }
