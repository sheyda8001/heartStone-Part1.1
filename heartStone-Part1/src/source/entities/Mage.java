package source.entities;

import Game.Game;
import source.*;

import java.util.ArrayList;

public class Mage extends Hero {
    private  int HP=30;
    private int heroPowerUsed=0;
    private String[] cards=new String[15];
    private boolean isopen=true;
    private String[] specialCards=new String[2];
    private String heroName="source.Cards.Mage";
    public Mage()
    {
        fillCards();
    }
    public void setCard()
    {

    }
    private void fillCards()
    {
        cards[0]="The Dark Portal";
        cards[1]="Warpath";
        cards[2]="Inner Rage";
        cards[3]="Wisp";
        cards[4]="Vicious Scalehide";
        cards[5]="Polymorph";
    }
    public String getHeroName()
    {
        return this.heroName;
    }
    public boolean isopen()
    {
        return true;
    }
public void useHeroPower()
{
    Game.player.setMana(2);
    heroPowerUsed++;
}
    @Override
    public String[] getSpecialCards() {
        return specialCards;
    }
private ArrayList<String> notexistinnow()
{
    ArrayList<String> ans =new ArrayList<>();
    try{
    for(int i=0;i<Game.player.getrWholeCards().size();i++)
    {
        if((Card.getCard(Game.player.getrWholeCards().get(i)).getHeroClass().equals(Game.player.getSelectedHeroName())||Card.getCard(Game.player.getrWholeCards().get(i)).getHeroClass().equals(HeroClass.NEUTRAL))&&!Game.player.getNowCards().contains(Game.player.getrWholeCards().get(i)))
        {
            ans.add(Game.player.getrWholeCards().get(i));
        }

    }
    return ans;}
    catch(Exception e)
    {
        Log.LOGGER.finest("error "+e.getStackTrace());

    }
    return ans;
}
    @Override
    public boolean setCards(String name) {
        try {
            int num = 0;
            for (int i = 0; i < 15; i++) {
                if (this.cards[i].equals(name)) {
                    num++;
                }

            }
            for (int i = 0; i < 15; i++) {
                if (this.cards[i] == null && ((num == 1) || notexistinnow().contains(name))) {
                    this.cards[i] = name;
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            Log.LOGGER.finest("error "+e.toString());

        }
return false;
    }

    @Override
    public String[] getCards() {
        return cards;
    }
public boolean removeCards(String name)
{try{
    for(int i=0;i<15;i++)
    {
        if(this.cards[i].equals(name)){
            this.cards[i]=null;return true;}
    }}
    catch(Exception e)
    {
        Log.LOGGER.finest("error "+e.getStackTrace());

    }
    return false;
}

}
