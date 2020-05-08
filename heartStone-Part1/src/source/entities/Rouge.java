package source.entities;

import Game.Game;

import java.util.ArrayList;
import source.*;

public class Rouge extends Hero {
    private int HP=30;
    private int heroPowerUsed=0;
    private boolean isopen=false;
    private String[] cards=new String[15];

    private String[] specialCards=new String[2];
    private String heroName="source.Cards.Rouge";
    public boolean isopen()
    {
        return isopen;
    }
    public boolean setCards(String name)
    {
        int num=0;
        for(int i=0;i<15;i++)
        {
            if(this.cards[i].equals(name)) {
                num++;
            }

        }
        for(int i=0;i<15;i++)
        {
            if(this.cards[i]==null&&((num==1)||notexistinnow().contains(name)))
            {
                this.cards[i]=name;
                return true;
            }
        }
        return false;
    }
    private void fillCards()
    {
       cards[0]="Friendly Smith";
       cards[1]="Inner Rage";
       cards[2]="Baron Geddon";
       cards[3]="The Dark Portal";
       cards[4]="Fungal Fortunes";
       cards[5]="Serpent War";

    }
public String getHeroName()
{
    return this.heroName;
}
    @Override
    public String[] getCards() {
        return cards;
    }

    @Override
    public String[] getSpecialCards() {
        return specialCards;
    }
    public void useHeroPower()
    {
        Game.player.setMana(3);
        heroPowerUsed++;
    }
    public boolean removeCards(String name)
    {try{
        for(int i=0;i<15;i++)
        {
            if(this.cards[i].equals(name)){
                this.cards[i]=null;return true;}
        }}
        catch (Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
        return false;
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

        }}
        catch (Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
        return ans;
    }
}
