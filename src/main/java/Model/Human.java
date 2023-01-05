package Model;

import Game.GameState;
import com.example.demo1.BoardController;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class Human extends Player{
    public static Human instance;

    public Human(boolean isHuman, ArrayList<Card> hand, int coinNumber, int playerNumber) throws IOException, ParseException {
        super(isHuman, hand, coinNumber, playerNumber);
    }

    public static Human getInstance(boolean isHuman, ArrayList<Card> hand, int coinNumber, int playerNumber) throws IOException, ParseException {
        if(instance==null){
            instance=new Human(isHuman, hand, coinNumber, playerNumber);
            return instance;
        }
        else {
            return instance;
        }
    }

}
