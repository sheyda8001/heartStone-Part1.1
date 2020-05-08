package source.entities;

import source.*;

public  class MinionCard extends Card {
    private int HP=0;
    private int Attack=0;
    public MinionCard (String name, String description, Rarity rarity, CardType type, HeroClass heroClass, int manaCost, int HP, int Attack, int cardCost)
    {
        super( name,description,rarity, type, heroClass,manaCost,cardCost);



    }

}
