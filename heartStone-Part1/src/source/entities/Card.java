package source.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import source.*;
import java.io.*;
import java.lang.reflect.Type;

public abstract class Card  {
    private String description = "";
    private final CardType cardType;
    private final Rarity rarity;
    private HeroClass heroClass;
    private boolean collectible = true;

    private final String name;
   private int manaCost;
   private int cardCost;
    public Card(String name, String description, Rarity rarity, CardType type, HeroClass heroClass, int manaCost, int cardCost) {

        this.name=name;

        setDescription(description);
        setCollectible(collectible);
        cardType = type;
        this.rarity = rarity;
        this.heroClass = heroClass;
        setManaCost(manaCost);
        this.cardCost=cardCost;}



    public static Card getCard(String name)  {
        Card c=null;

           // Gson gson = new Gson();
            try {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(Card.class, new CourseCreator());
                Gson gson = gsonBuilder.create();
               Card object = gson.fromJson(new FileReader("cards\\" + name + ".json"), MinionCard.class);

               return object;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch(Exception e)
            {
                Log.LOGGER.finest("error "+e.getStackTrace());
            }


        return c;
    }
    private void readFile(String name)
    {

    }
    public int getCardCost(){return cardCost;}
    public void setManaCost(int manaCost)
    {
        this.manaCost=manaCost;
    }
    public CardType getCardType() {
        return cardType;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }
    public String getDescription() {
        return description;
    }
    public Rarity getRarity() {
        return rarity;
    }
    public boolean isCollectible() {
        return collectible;
    }
    public void setCollectible(boolean collectible) {
        this.collectible = collectible;
    }

    public void setDescription(String description) {
        this.description = description;
    }

public  String getName()
{
    return this.name;
}
//public static source.Cards.Card getCard(String name)
//{
//    Gson gson = new Gson();
//    source.Cards.Card card;
//    try (Reader reader = new FileReader("cards\\"+name+".json")) {
//
//        // Convert JSON File to Java Object
//        card = gson.fromJson(reader, source.Cards.Card.class);
//        return card;
//
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//
//}
}
class CourseCreator implements InstanceCreator {



    @Override
    public Object createInstance(Type type) {
        return null;
    }
}