package Model;

import Game.GameState;
import Game.TurnChanger;
import com.example.demo1.BoardController;
import com.example.demo1.Players;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Bot2 extends Player{
    public static Bot2 instance;
    public static boolean doChallenge=false;

    public Bot2(boolean isHuman, ArrayList<Card> hand, int coinNumber, int playerNumber) throws IOException, ParseException {
        super(isHuman, hand, coinNumber, playerNumber);
    }

    public static Bot2 getInstance(boolean isHuman, ArrayList<Card> hand, int coinNumber, int playerNumber) throws IOException, ParseException {
        if(instance==null){
            instance=new Bot2(isHuman, hand, coinNumber, playerNumber);
            return instance;
        }
        else {
            return instance;
        }
    }

    @Override
    public boolean newUndoneAction() throws IOException, ParseException {
        if (!(GameState.undonelastAction == Action.Income || GameState.undonelastAction==Action.ForeignAid || GameState.undonelastAction == Action.Exchange1coin || GameState.undonelastAction == Action.Coup)) {
            if (doChallenge) {
                doChallenge = !doChallenge;

                ArrayList<Card> ans=this.challenge(this);
                if (ans.size() == 2) {
                    System.out.println("b lost the challenge");
                    return true;
                }
                else {
                    System.out.println("b won the challenge");
                    return false;
                }
            }
            else {
                doChallenge = !doChallenge;
                return true;
            }
        }
        else {
            return true;
        }
    }

    public void strategy() throws IOException, ParseException, InterruptedException {
        if(this.coinNumber>=10){
            int i = ThreadLocalRandom.current().nextInt(0, Players.getInstance().getAllPlayers().size()-1);
            this.Coup(getRandomPalyer(i,Players.getInstance().getAllPlayers().indexOf(this)));
        }
        else {
            this.income();
        }
    }

}
