package source;

import source.entities.Card;
import Game.Game;
import source.entities.Hero;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CLI {


    private static boolean isEnteredCard=false;
    private static boolean isIsEnteredStore=false;
    private static boolean isSelect=false;
    public static void cli(String command)throws IOException
    {

        if(command.indexOf("sell ")==0)
        {
            if(isIsEnteredStore)
            {try{
if(Game.player.getSt().getCards().contains(command.substring(5))&&Game.player.getSt().sellCard(command.substring(5)))
{
    System.out.println("you selled the card");
    Log.LOGGER.finest("sell card:"+command.substring(5));

}
else{
    System.out.println("you can't sell the card");
}}
catch(Exception e)
{
    Log.LOGGER.finest("error  "+e.getStackTrace() );

}
            }
                else{
                    System.out.println("you didn't enter to the store ");
                }

        }
        if(command.indexOf("buy ")==0)
        {
            if(isIsEnteredStore)
            {try{
                if(Game.player.getSt().getCards().contains(command.substring(4))&&Game.player.getSt().buyCard(command.substring(4)))
                {
                    System.out.println("you buyed the card");
                    Log.LOGGER.finest("buy card:"+command.substring(4));

                }
                else{
                    System.out.println("you can't buy this card");
                }}
                catch(Exception e)
                {
                    Log.LOGGER.finest("error  "+e.getStackTrace() );

                }
            }
            else{
                System.out.println("you didn't enter to the store ");
            }
        }
        if(command.indexOf("remove ")==0)
        {
            if(isSelect)
            {try{
                if(Game.player.getSt().getCards().contains(command.substring(7))&&Game.player.getSelectedHero().removeCards(command.substring(7))){
                    System.out.println("the card is removed");
                    Log.LOGGER.finest("remove card:frome deck"+command.substring(7));

                }
                else{
                    System.out.println("there is a problem");
                }}
                catch(Exception e)
                {
                    Log.LOGGER.finest("error  "+e.getStackTrace() );

                }
            }
            else{
                System.out.println("the card isn't added");
            }
        }
        if(command.indexOf("add ")==0)
        {
            if(isSelect)
            {try{
            if(Game.player.getSt().getCards().contains(command.substring(4))&&Game.player.getSelectedHero().setCards(command.substring(4)))

            {

                System.out.println("the card is added");
                Log.LOGGER.finest("add card:to deck : "+command.substring(7));

            }
            else{
                System.out.println("the card isn't added");
            }}
            catch(Exception e)
            {
                Log.LOGGER.finest("error  "+e.getStackTrace() );
            }
            }

            else{
                System.out.println("tou didn't select a hero");
            }
        }
        if(command.indexOf("select ")==0&&isEnteredCard)
        {try{
            if(Game.player.getOpenedHeros().contains(command.substring(7)))
            {
               if( Hero.getAllHero().contains(command.substring(7))&&Game.player.setHero(command.substring(7))){
                System.out.println("the hero is selected"); isSelect=true;
                   Log.LOGGER.finest("select hero:"+command.substring(7));

               }
               else{
                   System.out.println("the hero isn't selected plz enter again");
               }
            }
            else{
                System.out.println("selected hero hasn't been opened plz select another hero");
            }}
            catch(Exception e)
            {
                Log.LOGGER.finest("error  "+e.getStackTrace() );

            }

        }
        switch(command)
        {
            case "exit-a":
                Log.LOGGER.finest("exit all");

                System.exit(0);
            break;
            case"exit":
                Log.LOGGER.finest("exit user");

                EntrancePage.enterPage();
                break;
            case"delete-player":

DeleteAccount del=new DeleteAccount(Game.player);
String usern=Game.player.getUser();
long id=Game.player.getid();
                if(del.delete()) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println("account is deleted");
                    Log.LOGGER.finest("delete user:"+usern);
                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter("logs\\"+usern+"-"+(id+1)+".log", true));
//                    writer.newLine();   //Add new line
                    writer.write("DELETED_AT:"+dtf.format(now));
                    writer.close();
                    EntrancePage.enterPage();
                }
            break;
            case"collections":System.out.println(Game.player.wholeCardsToString());
            isEnteredCard=true;
                Log.LOGGER.finest("navigate collections");

                break;
            case "store":
Store store=new Store(Game.player);

                System.out.println(store.toStringNotExist());
                isIsEnteredStore=true;
                Log.LOGGER.finest("navigate store");

                break;
            case "ls-a-heros":
                if(isEnteredCard){
                    System.out.print("all heros are :  ");
                    Hero.showHeros();
                    System.out.println();
                    Log.LOGGER.finest("list hero:all");

                }
                else{
                    System.out.println("you haven't been entered to cards manager section yet");
                }
                break;
            case "ls-m-heros":
                if(isEnteredCard)
                {
if(Game.player.getSelectedHeroName()!=null)
{
    System.out.println("selected hero is : "+Game.player.getSelectedHeroName());
    Log.LOGGER.finest("list nowhero:"+Game.player.getSelectedHeroName());

}
else{
    System.out.println("the hero isn't selected ");
}
                }
                else{
                    System.out.println("you haven't been entered to cards manager section yet");
                }
                break;
            case"ls-a-cards":
                if(isSelect)
                {
                    System.out.println(Game.player.wholeCardsToString());
                    Log.LOGGER.finest("list cards :all");

                }
                else{
                    System.out.println("you didn't select hero");
                }
                break;
            case"ls-m-cards":
                if(isSelect)
                {System.out.println("you have "+Game.player.getNowCardsSize()+"cards");
                    System.out.println(Game.player.getNowCardsToString());
                    Log.LOGGER.finest("list cards:in deck ");

                }
                else{
                    System.out.println("you didn't select hero");

                }
                break;
            case"ls-n-cards":
                if(isSelect){
                for(int i=0;i<Game.player.getrWholeCards().size();i++)
                {
                    if((Card.getCard(Game.player.getrWholeCards().get(i)).getHeroClass().equals(Game.player.getSelectedHeroName())||Card.getCard(Game.player.getrWholeCards().get(i)).getHeroClass().equals(HeroClass.NEUTRAL))&&!Game.player.getNowCards().contains(Game.player.getrWholeCards().get(i)))
                    {
                        System.out.print(Game.player.getrWholeCards().get(i)+"    ");
                        Log.LOGGER.finest("list cards:not in deck ");

                    }
                }}
                else{
                    System.out.println("you didn't select hero");
                    Log.LOGGER.finest("list cards not select hero");

                }
                break;
            case"wallet":
                if(isIsEnteredStore)
                {
                    System.out.println("your number of coins is "+Game.player.getCoins());
                    Log.LOGGER.finest("show  player coins ");

                }
                else{
                        System.out.println("you didn't enter to the store ");

                }
                break;
            case"ls-s":
                if(isIsEnteredStore)
                {
                    System.out.println("you can sell these cards"+Game.player.getSt().sellableCards().toString());
                    Log.LOGGER.finest("list  canSellCards ");

                }
                else{
                    System.out.println("you didn't enter to the store ");

                }
                break;
            case"ls-b":
                if(isIsEnteredStore)
                {
System.out.println("you can buy"+Game.player.getSt().canBuy().toString());
                    Log.LOGGER.finest("list  canBuyCards");

                }
                else{
                    System.out.println("you didn't enter to the store ");
                }
                break;
            case"help":
                if(isIsEnteredStore)
                {
                    System.out.println("is-b   "+"is-s   "+"wallet   "+"sell [name]  "+"buy [name]  ");
                }
                if(isEnteredCard)
                {
                    System.out.println("ls-a-heros   "+"ls-m-heros   ");
                }
                if(isSelect)
                {
                    System.out.println("remove [name]   "+"add  [name]   "+"select  [name]   "+"ls-a-cards   "+"ls-m-cards   "+"ls-n-cards   ");
                }
                System.out.println("exit-a   "+"exit   "+"delete-player   "+"collections   "+"store   ");
                Log.LOGGER.finest("help");
                break;

        }

    }

}

