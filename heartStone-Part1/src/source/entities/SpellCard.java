package source.entities;

import source.CardType;
import source.HeroClass;
import source.Rarity;

public  class SpellCard extends Card {

    public SpellCard(String name, String description, Rarity rarity, CardType type, HeroClass heroClass, int manaCost, int cardCost)
    {
        super( name,description,rarity, type, heroClass,manaCost,cardCost);


    }



}
