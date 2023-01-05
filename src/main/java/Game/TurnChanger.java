package Game;

import GuiController.Images;
import Model.*;
import com.example.demo1.BoardController;
import com.example.demo1.Players;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TurnChanger extends BoardController{
    public static int turn=4;
    public static String event="";

    public TurnChanger() throws IOException, ParseException {
    }

    public static void setEvent(String event) {
        TurnChanger.event += String.format("%s%n",event);
    }

    public static void setTurn() throws IOException, ParseException, InterruptedException {
        System.out.println("turn: "+turn);
//        System.out.println(Players.getInstance().getRemovedPlayers()+" :Remained players");

        if(Players.getInstance().getAllPlayers().size()==1){

            TurnChanger.setEvent(findPlayer(turn)+" WON THE GAME !");

            update();
            static_nxt.setVisible(false);
            return;
        }
        if(turn==1){
            turn=2;
            if(Players.getInstance().getRemovedPlayers().get(turn)==1){
                setTurn();
            }
            else{
                findInstance(Players.getInstance().getA());
                update();
                return;
            }
        }
        if(turn==2){
            turn=3;
            if(Players.getInstance().getRemovedPlayers().get(turn)==1){
                setTurn();
            }
            else{
                findInstance(Players.getInstance().getB());
                update();
                return;
            }
        }
        if(turn==3){
            turn=4;
            if(Players.getInstance().getRemovedPlayers().get(turn)==1){
                setTurn();
            }
            else{
                findInstance(Players.getInstance().getC());
                update();
                return;
            }
        }
        if(turn==4){
            if(Players.getInstance().getRemovedPlayers().get(1)==1){
                turn=1;
                update();
                setTurn();
            }
            else {
                static_mssg.setText("its humans turn.");
                return;
            }
        }
    }

    public static void findInstance(Player player) throws IOException, ParseException, InterruptedException {
        if(player instanceof Bot1){
            ((Bot1)(player)).strategy();
        }
        if(player instanceof Bot2){
            ((Bot2)(player)).strategy();
        }
        if(player instanceof Bot3){
            ((Bot3)(player)).strategy();
        }
        if(player instanceof Bot4){
            ((Bot4)(player)).strategy();
        }
    }

    public static String  findPlayer(int i){
        if(i==1){
            return "You";
        }
        if(i==2){
            return "A";
        }
        if(i==3){
            return "B";
        }
        if(i==4){
            return "C";
        }

        return "";
    }

    private static void update(){
        static_txtArea.setText(String.format("%s%s",static_txtArea.getText(),TurnChanger.event));
        TurnChanger.event="";
        try {
            if(Players.getInstance().getHuman().getHand().size()>=1){
                static_Label1.setVisible(true);
                static_Card1.setImage(new Images().getImage(Players.getInstance().getHuman().getHand().get(0).getSuit().toString()));
                static_Label1.setText(Players.getInstance().getHuman().getHand().get(0).getSuit().toString());
            }
            else{
                static_Card1.setImage(null);
                static_Label1.setText("");
                static_Label1.setVisible(false);
            }

            if(Players.getInstance().getHuman().getHand().size()==2){
                static_Label2.setVisible(true);
                static_Card2.setImage(new Images().getImage(Players.getInstance().getHuman().getHand().get(1).getSuit().toString()));
                static_Label2.setText(Players.getInstance().getHuman().getHand().get(1).getSuit().toString());
            }
            else{
                static_Card2.setImage(null);
                static_Label2.setText("");
                static_Label2.setVisible(false);
            }


            static_bCoin.setText(String.valueOf(Players.getInstance().getA().getCoinNumber()));
            static_cCoin.setText(String.valueOf(Players.getInstance().getB().getCoinNumber()));
            static_dCoin.setText(String.valueOf(Players.getInstance().getC().getCoinNumber()));
            static_deckCoin.setText(String.valueOf(Deck.getInstance().getCionRemained()));

            static_bCard.setText(String.valueOf(Players.getInstance().getA().getHand().size()));
            static_cCard.setText(String.valueOf(Players.getInstance().getB().getHand().size()));
            static_dCard.setText(String.valueOf(Players.getInstance().getC().getHand().size()));
            static_deckCard.setText(String.valueOf(Deck.getInstance().getCards().size()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
