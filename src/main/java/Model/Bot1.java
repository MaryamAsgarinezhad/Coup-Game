package Model;

import Game.TurnChanger;
import com.example.demo1.Players;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Bot1 extends Player{
    public static Bot1 instance;

    public Bot1(boolean isHuman, ArrayList<Card> hand, int coinNumber, int playerNumber) throws IOException, ParseException {
        super(isHuman, hand, coinNumber, playerNumber);
    }

    public static Bot1 getInstance(boolean isHuman, ArrayList<Card> hand, int coinNumber, int playerNumber) throws IOException, ParseException {
        if(instance==null){
            instance=new Bot1(isHuman, hand, coinNumber, playerNumber);
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
            if(this.coinNumber<7){
                this.tax();
//                this.Kill(Players.getInstance().getHuman());
            }
            else{
                int i = ThreadLocalRandom.current().nextInt(0, Players.getInstance().getAllPlayers().size()-1);
                System.out.println(getRandomPalyer(i,Players.getInstance().getAllPlayers().indexOf(this)).playerNumber);
                this.Coup(getRandomPalyer(i,Players.getInstance().getAllPlayers().indexOf(this)));
            }
        }
    }
}
