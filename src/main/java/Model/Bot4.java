package Model;

import Game.GameState;
import Game.TurnChanger;
import com.example.demo1.BoardController;
import com.example.demo1.Players;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Bot4 extends Player{
    public static Bot4 instance;
    public boolean aBoolean=true;

    public Bot4(boolean isHuman, ArrayList<Card> hand, int coinNumber, int playerNumber) throws IOException, ParseException {
        super(isHuman, hand, coinNumber, playerNumber);
    }

    public static Bot4 getInstance(boolean isHuman, ArrayList<Card> hand, int coinNumber, int playerNumber) throws IOException, ParseException {
        if(instance==null){
            instance=new Bot4(isHuman, hand, coinNumber, playerNumber);
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
        else {
            if (aBoolean) {
                aBoolean= !aBoolean;
                this.income();
            }
            else{
                aBoolean = !aBoolean;
                this.exchange1coin(this.Hand.get(0).getSuit().toString());
            }
        }
    }
}
