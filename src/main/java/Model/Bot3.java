package Model;

import Game.TurnChanger;
import com.example.demo1.BoardController;
import com.example.demo1.Players;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Bot3 extends Player{
    public static Bot3 instance;

    public Bot3(boolean isHuman, ArrayList<Card> hand, int coinNumber, int playerNumber) throws IOException, ParseException {
        super(isHuman, hand, coinNumber, playerNumber);
    }

    public static Bot3 getInstance(boolean isHuman, ArrayList<Card> hand, int coinNumber, int playerNumber) throws IOException, ParseException {
        if(instance==null){
            instance=new Bot3(isHuman, hand, coinNumber, playerNumber);
            return instance;
        }
        else {
            return instance;
        }
    }

    public void strategy() throws IOException, ParseException, InterruptedException {
        if(this.coinNumber>=10){
            int i = ThreadLocalRandom.current().nextInt(0, Players.getInstance().getAllPlayers().size()-1);
            this.Coup(getRandomPalyer(i,Players.getInstance().getAllPlayers().indexOf(this)));
        }
        else{
            boolean b=false;
            boolean ambassador=false;
            int ambassadorIndex=0;

            for (Card card:this.Hand){
                if(card.getSuit()==Suit.Assassin){
                    b=true;
                }
                if(card.getSuit()==Suit.Ambassador){
                    ambassador=true;
                    ambassadorIndex=this.Hand.indexOf(card);
                }
            }

            if(b && this.coinNumber>=3){
                int i = ThreadLocalRandom.current().nextInt(0, Players.getInstance().getAllPlayers().size());
                this.Kill(getRandomPalyer(i,Players.getInstance().getAllPlayers().indexOf(this)));
//                this.Kill(Players.instance.getHuman());

            }
            else{
                if(ambassador){
                    ArrayList<Card> ans=this.exchange();
                    Card Assassin;
                    Card NotAssassin;

                    if (ans == null) {
                        return;
                    }
                    if(ans.get(0).getSuit()==Suit.Assassin || ans.get(1).getSuit()==Suit.Assassin){
                        if(ans.get(0).getSuit()==Suit.Assassin){
                            Assassin=ans.get(0);
                            NotAssassin=ans.get(1);
                        }
                        else{
                            Assassin=ans.get(1);
                            NotAssassin=ans.get(0);
                        }
                        Card card1=this.Hand.remove(ambassadorIndex);
                        this.Hand.add(Assassin);

                        Deck.getInstance().getCards().add(NotAssassin);
                        Deck.getInstance().getCards().add(card1);
                    }
                    else{
                        Deck.getInstance().getCards().add(ans.get(0));
                        Deck.getInstance().getCards().add(ans.get(0));
                    }
                }
                else{
                    if(this.coinNumber==0){
                        this.foreignAid();
                    }
                    else {
                        this.exchange1coin(this.Hand.get(0).getSuit().toString());
                    }
                }
            }
        }
    }
}
