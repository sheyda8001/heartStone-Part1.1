package source.entities;

import Game.Game;

import java.util.ArrayList;import source.*;


public class Warlock extends Hero {
    private int HP=30;
    private boolean isopen=false;
    private int heroPowerUsed=0;
    private String[] cards=new String[15];
    private String[] specialCards=new String[2];
    private String heroName="source.Cards.Warlock";
    public Warlock()
    {
        fillCards();
    }
    public void setCard()
    {

    }
    public String getHeroName()
    {
        return this.heroName;
    }
    public void fillCards()
    {
        cards[0]="Witchwood Imp";
        cards[1]="Fungal Fortunes";
        cards[2]="Silence";
        cards[3]="Inner Rage";
        cards[4]="Warpath";
        cards[5]="Baron Geddon";
    }
public boolean isopen(){return isopen;}
    @Override
    public String[] getCards() {
        return cards;
    }

    @Override
    public String[] getSpecialCards() {
        return specialCards;
    }

    @Override
    public boolean setCards(String name) {
        try{
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
        }}
        catch (Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace());

        }
        return false;
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
            Log.LOGGER.finest("error  "+e.getStackTrace() );
        }
        return false;
    }
    private ArrayList<String> notexistinnow()
    {
        ArrayList<String> ans =new ArrayList<>();
        try{
        for(int i = 0; i< Game.player.getrWholeCards().size(); i++)
        {
            if((Card.getCard(Game.player.getrWholeCards().get(i)).getHeroClass().equals(Game.player.getSelectedHeroName())||Card.getCard(Game.player.getrWholeCards().get(i)).getHeroClass().equals(HeroClass.NEUTRAL))&&!Game.player.getNowCards().contains(Game.player.getrWholeCards().get(i)))
            {
                ans.add(Game.player.getrWholeCards().get(i));
            }

        }}
        catch(Exception e)
        {
            Log.LOGGER.finest("error  "+e.getStackTrace() );

        }
        return ans;
    }
}
