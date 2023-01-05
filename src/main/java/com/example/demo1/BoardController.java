package com.example.demo1;

import Game.GameState;
import Game.TurnChanger;
import GuiController.Images;
import Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
    Player player;
    int imNumber = 0;
    static boolean wait = true;

    public static boolean updete1=true;

    @FXML
    MenuButton taction;
    @FXML
    Label labbbb;
    @FXML
    MenuButton react;
    @FXML
    Label personCoins;
    @FXML
    ImageView Card1;
    @FXML
    ImageView Card2;
    @FXML
    Label Label1;
    @FXML
    Label Label2;
    @FXML
    Label mssg;
    @FXML
    VBox vbox;
    @FXML
    ListView listView;
    @FXML
    Button btn;
    @FXML
    ImageView im;
    @FXML
    ImageView im1;
    @FXML
    ImageView im2;
    @FXML
    ImageView im3;
    @FXML
    ImageView im4;
    @FXML
    ImageView im5;
    @FXML
    ImageView im6;
    @FXML
    ImageView im7;
    @FXML
    ImageView im8;
    @FXML
    ImageView im9;
    @FXML
    ImageView im10;
    @FXML
    ImageView im11;
    @FXML
    ImageView im12;
    @FXML
    ImageView im13;
    @FXML
    Label bCoin;
    @FXML
    Label bCard;
    @FXML
    Label cCoin;
    @FXML
    Label cCard;
    @FXML
    Label dCoin;
    @FXML
    Label dCard;
    @FXML
    Label deckCoin;
    @FXML
    Label deckCard;
    @FXML
    public
    TextArea eventRecorder;
    @FXML
    static
    Label r;

    @FXML
    Button nxt;


    public static TextArea static_txtArea;
    public static ImageView static_;
    public static ImageView static_1;
    public static ImageView static_2;
    public static ImageView static_3;
    public static ImageView static_4;
    public static ImageView static_5;
    public static ImageView static_6;
    public static ImageView static_7;
    public static ImageView static_8;
    public static ImageView static_9;
    public static ImageView static_10;
    public static ImageView static_11;
    public static ImageView static_12;
    public static ImageView static_13;
    public static int static_imNumber=0;
    public static Label static_bCoin;
    public static Label static_cCoin;
    public static Label static_dCoin;
    public static Label static_deckCoin;
    public static Label static_bCard;
    public static Label static_cCard;
    public static Label static_dCard;
    public static Label static_deckCard;
    public static MenuButton static_react;

    public static ImageView static_Card1;
    public static ImageView static_Card2;
    public static Label static_Label1;
    public static Label static_Label2;
    public static Label static_mssg;

    public static MenuButton static_taction;
    public static Label static_labbbb;
    public static Label static_personCoins;
    public static Button static_nxt;
    public static int PlayertoKill=10;

    public BoardController() throws IOException, ParseException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setStatic();
        vbox.setVisible(false);
        react.setVisible(false);
        try {
            updateComponents();
            updete1=false;
            player = Players.getInstance().getHuman();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setStatic(){
        static_taction=taction;
        static_labbbb=labbbb;
        static_personCoins=personCoins;

        static_txtArea=eventRecorder;
        static_=im;
        static_1=im1;
        static_2=im2;
        static_3=im3;
        static_4=im4;
        static_5=im5;
        static_6=im6;
        static_7=im7;
        static_8=im8;
        static_9=im9;
        static_10=im10;
        static_11=im11;
        static_12=im12;
        static_13=im13;
        static_imNumber=imNumber;

        static_bCard=bCard;
        static_cCard=cCard;
        static_dCard=dCard;
        static_deckCard=deckCard;

        static_bCoin=bCoin;
        static_cCoin=cCoin;
        static_dCoin=dCoin;
        static_deckCoin=deckCoin;
        static_react=react;

        static_Card1=Card1;
        static_Card2=Card2;
        static_Label1=Label1;
        static_Label2=Label2;

        static_mssg=mssg;
        static_nxt=nxt;
    }


    public void income(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(TurnChanger.turn!=4){
            mssg.setText("its not your turn.");
            return;
        }
        if(player.coinNumber>=10){
            mssg.setText("you have to coup!");
            return;
        }

        if (!player.income()) {
            mssg.setText("there is less than 1 coin in the bank, take another action!");
            return;
        } else {
            mssg.setText("");
            updateComponents();
        }
    }

    public void exchangeLeft(MouseEvent mouseEvent) throws IOException, ParseException, InterruptedException {
        if(TurnChanger.turn!=4){
            mssg.setText("its not your turn.");
            return;
        }
        if(player.coinNumber>=10){
            mssg.setText("you have to coup!");
            return;
        }

        if (!player.exchange1coin(Label1.getText())) {
            mssg.setText("there is less than 1 card in the deck or you have less than 1 coin, take anoter action!");
            return;
        } else {
            mssg.setText("");
            updateComponents();
        }
    }

    public void exchangeRight(MouseEvent mouseEvent) throws IOException, ParseException, InterruptedException {
        if(TurnChanger.turn!=4){
            mssg.setText("its not your turn.");
            return;
        }
        if(player.coinNumber>=10){
            mssg.setText("you have to coup!");
            return;
        }

        if (!player.exchange1coin(Label2.getText())) {
            mssg.setText("there is less than 1 card in the deck or you have less than 1 coin, take anoter action!");
            return;
        } else {
            mssg.setText("");
            updateComponents();
        }
    }

    public void foreignAid(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(TurnChanger.turn!=4){
            mssg.setText("its not your turn.");
            return;
        }
        if(player.coinNumber>=10){
            mssg.setText("you have to coup!");
            return;
        }

        if (!player.foreignAid()) {
            mssg.setText("there is less than 2 coins in the deck, take anoter action!");
            return;
        } else {
            mssg.setText("");
            updateComponents();
        }
    }

    public void tax(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(TurnChanger.turn!=4){
            mssg.setText("its not your turn.");
            return;
        }
        if(player.coinNumber>=10){
            mssg.setText("you have to coup!");
            return;
        }

        if (!player.tax()) {
            mssg.setText("there is less than 3 coins in the deck, take anoter action!");
            return;
        } else {
            mssg.setText("");
            updateComponents();
        }
    }

    public void coupA(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if (!player.Coup(Players.getInstance().getA())) {
            mssg.setText("you have less than 7 zero, take anoter action");
            return;
        } else {
            mssg.setText("");
            updateComponents();
        }
    }

    public void coupB(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if (!player.Coup(Players.getInstance().getB())) {
            mssg.setText("you have less than 7 zero, take anoter action");
            return;
        } else {
            mssg.setText("");
            updateComponents();
        }
    }

    public void coupC(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if (!player.Coup(Players.getInstance().getC())) {
            mssg.setText("you have less than 7 zero, take anoter action");
            return;
        } else {
            mssg.setText("");
            updateComponents();
        }
    }

    public void killA(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(TurnChanger.turn!=4){
            mssg.setText("its not your turn.");
            return;
        }
        if(player.coinNumber>=10){
            mssg.setText("you have to coup!");
            return;
        }

        if (!player.Kill(Players.getInstance().getA())) {
            mssg.setText("you have less than 3 zero, take anoter action");
            return;
        } else {
            mssg.setText("");
            updateComponents();
        }
    }

    public void killB(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(TurnChanger.turn!=4){
            mssg.setText("its not your turn.");
            return;
        }
        if(player.coinNumber>=10){
            mssg.setText("you have to coup!");
            return;
        }

        if (!player.Kill(Players.getInstance().getB())) {
            mssg.setText("you have less than 3 zero, take anoter action");
            return;
        } else {
            mssg.setText("");
            updateComponents();
        }
    }

    public void killC(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(TurnChanger.turn!=4){
            mssg.setText("its not your turn.");
            return;
        }
        if(player.coinNumber>=10){
            mssg.setText("you have to coup!");
            return;
        }

        if (!player.Kill(Players.getInstance().getC())) {
            mssg.setText("you have less than 3 zero, take anoter action");
            return;
        } else {
            mssg.setText("");
            updateComponents();
        }
    }

    public void exchange(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(TurnChanger.turn!=4){
            mssg.setText("its not your turn.");
            return;
        }
        if(player.coinNumber>=10){
            mssg.setText("you have to coup!");
            return;
        }

        ArrayList<Card> ans=player.exchange();
        Card card1 = ans.get(0);
        Card card2 = ans.get(1);

        listView.getItems().clear();
        listView.getItems().addAll(card1.suit.toString(), card2.suit.toString(), Label1.getText(), Label2.getText());
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        vbox.setVisible(true);
    }

    public void choose(ActionEvent event) throws IOException, ParseException, InterruptedException {
        ObservableList listOfItems = listView.getSelectionModel().getSelectedItems();
        if (listOfItems.size() != 2) {
            mssg.setText("select 2 items only!");
            return;
        }

        ObservableList<Integer> listOfNums = listView.getSelectionModel().getSelectedIndices();
        int a = listOfNums.get(0);
        int b = listOfNums.get(1);

        player.getHand().clear();
        for (Object item : listOfItems) {
            player.getHand().add(new Card(MakeSuit((String) item)));
        }

        for (int i = 0; i < 4; i++) {
            if (i != a && i != b) {
                Deck.getInstance().getCards().add(new Card(MakeSuit(listView.getItems().get(i).toString())));
            }
        }

        vbox.setVisible(false);
        updateComponents();
    }

    public Suit MakeSuit(String string) {
        if (string.equals("Duke")) {
            return Suit.Duke;
        }
        if (string.equals("Assassin")) {
            return Suit.Assassin;
        }
        if (string.equals("Captain")) {
            return Suit.Captain;
        }
        if (string.equals("Ambassador")) {
            return Suit.Ambassador;
        }
        if (string.equals("Contessa")) {
            return Suit.Contessa;
        }
        return null;
    }

    public ImageView getImageView(int i) {
        if (i == 0) {
            return im;
        }
        if (i == 1) {
            return im1;
        }
        if (i == 2) {
            return im2;
        }
        if (i == 3) {
            return im3;
        }
        if (i == 4) {
            return im4;
        }
        if (i == 5) {
            return im5;
        }
        if (i == 6) {
            return im6;
        }
        if (i == 7) {
            return im7;
        }
        if (i == 8) {
            return im8;
        }
        if (i == 9) {
            return im9;
        }
        if (i == 10) {
            return im10;
        }
        if (i == 11) {
            return im11;
        }
        if (i == 12) {
            return im12;
        }
        if (i == 13) {
            return im13;
        }

        return im;
    }

    public void Steal_A(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(TurnChanger.turn!=4){
            mssg.setText("its not your turn.");
            return;
        }
        if(player.coinNumber>=10){
            mssg.setText("you have to coup!");
            return;
        }

        if (!player.Steal(Players.getInstance().getA())) {
            mssg.setText(" A has no coins, choose another player!");
            return;
        } else {
            mssg.setText("");
            updateComponents();
        }
    }

    public void Steal_B(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(TurnChanger.turn!=4){
            mssg.setText("its not your turn.");
            return;
        }
        if(player.coinNumber>=10){
            mssg.setText("you have to coup!");
            return;
        }

        if (!player.Steal(Players.getInstance().getB())) {
            mssg.setText(" B has no coins, choose another player!");
            return;
        } else {
            mssg.setText("Stealed successfully!");
            updateComponents();
        }
    }

    public void Steal_C(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(TurnChanger.turn!=4){
            mssg.setText("its not your turn.");
            return;
        }
        if(player.coinNumber>=10){
            mssg.setText("you have to coup!");
            return;
        }

        if (!player.Steal(Players.getInstance().getC())) {
            mssg.setText(" C has no coins, choose another player!");
            return;
        } else {
            mssg.setText("Stealed successfully!");
            updateComponents();
        }
    }

    public void updateComponents() throws IOException, ParseException, InterruptedException {
        try {
            if(Players.getInstance().getHuman().getHand().size()>=1){
                Label1.setVisible(true);
                Card1.setImage(new Images().getImage(Players.getInstance().getHuman().getHand().get(0).getSuit().toString()));
                Label1.setText(Players.getInstance().getHuman().getHand().get(0).getSuit().toString());

            }
            else{
                Card1.setImage(null);
                Label1.setText("");
                Label1.setVisible(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            if(Players.getInstance().getHuman().getHand().size()==2){
                Label2.setVisible(true);
                Card2.setImage(new Images().getImage(Players.getInstance().getHuman().getHand().get(1).getSuit().toString()));
                Label2.setText(Players.getInstance().getHuman().getHand().get(1).getSuit().toString());
            }
            else{
                Card2.setImage(null);
                Label2.setText("");
                Label2.setVisible(false);
            }
            personCoins.setText(String.valueOf(Players.getInstance().getHuman().getCoinNumber()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            bCoin.setText(String.valueOf(Players.getInstance().getA().getCoinNumber()));
            cCoin.setText(String.valueOf(Players.getInstance().getB().getCoinNumber()));
            dCoin.setText(String.valueOf(Players.getInstance().getC().getCoinNumber()));
            deckCoin.setText(String.valueOf(Deck.getInstance().getCionRemained()));

            bCard.setText(String.valueOf(Players.getInstance().getA().getHand().size()));
            cCard.setText(String.valueOf(Players.getInstance().getB().getHand().size()));
            dCard.setText(String.valueOf(Players.getInstance().getC().getHand().size()));
            deckCard.setText(String.valueOf(Deck.getInstance().getCards().size()));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(!updete1){
            if(eventRecorder.getText()==""){
                eventRecorder.setText(String.format("%s",TurnChanger.event));
            }
            else{
                eventRecorder.setText(String.format("%s%s",eventRecorder.getText(),TurnChanger.event));
            }
            TurnChanger.event="";
            TurnChanger.turn=1;
        }
    }

    public boolean challenge(ActionEvent event) throws IOException, ParseException {
        if(!(GameState.undonelastAction==Action.Income || GameState.undonelastAction==Action.Exchange1coin || GameState.undonelastAction==Action.Coup || GameState.undonelastAction==Action.ForeignAid)){
            ArrayList<Card> ans = player.challenge(player);

            if (ans.size() == 2) {
                mssg.setText("challenge false!");

                eventRecorder.setText(String.format("%s%s",eventRecorder.getText(),TurnChanger.event));
                TurnChanger.event="";
                Player.HumanAllow(true);
                return false;
            } else {
                mssg.setText("challenge true!");

                eventRecorder.setText(String.format("%s%s",eventRecorder.getText(),TurnChanger.event));
                TurnChanger.event="";
                Player.HumanAllow(false);
                return true;
            }
        }
        else{
            mssg.setText("you cant challenge this action");
            return false;
        }
    }

    public void Block_ForeignAid(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(GameState.undonelastAction==Action.ForeignAid ){
            if (!player.Block_ForeignAid()) {
                mssg.setText("you bluffed!");
                Player.HumanAllow(true);
                wait = false;
            }
            else {
                mssg.setText("foreign aid blocked!");
                Player.HumanAllow(false);
                wait = false;
            }
        }
        else{
            mssg.setText("nobody foreign aided so you cant block foreign aid!");
        }
    }

    public void Block_Stealing1(ActionEvent event) throws IOException, ParseException, InterruptedException {
//        new Player().BoardSetEvent(Players.getInstance().getHuman(),GameState.undonefirstPlayer,Action.Block_Stealing);

        if(GameState.undonelastAction==Action.Steal && GameState.undonelastPlayer.getPlayerNumber()==player.getPlayerNumber()){
            if (!player.Block_Stealing1()) {
                mssg.setText("you bluffed!");
                Player.HumanAllow(true);
                wait = false;
            }
            else {
                mssg.setText("Stealing blocked!");
                Player.HumanAllow(false);
                wait = false;
            }
        }
        else{
            mssg.setText("nobody stealed you so you cant block Stealing!");
        }
    }

    public void Block_Assassination(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(GameState.undonelastAction==Action.Assassinate && GameState.undonelastPlayer.getPlayerNumber()==player.getPlayerNumber()){
            if (!player.Block_Assassination()) {
                mssg.setText("you bluffed!");
                Player.HumanAllow(true);
            }
            else {
                mssg.setText("Assassination blocked!");
                Player.HumanAllow(false);
            }
        }
        else{
            mssg.setText("nobody Assassinated you so you cant block Assassination!");
        }
    }

    public void Block_Stealing2(ActionEvent event) throws IOException, ParseException, InterruptedException {
        if(GameState.undonelastAction==Action.Steal && GameState.undonelastPlayer.getPlayerNumber()==player.getPlayerNumber()){
            if (!player.Block_Stealing2()) {
                mssg.setText("you bluffed!");
                Player.HumanAllow(true);
                wait = false;
            } else {
                mssg.setText("Stealing blocked!");
                Player.HumanAllow(false);
                wait = false;
            }
        }
        else{
            mssg.setText("nobody stealed you so you cant block Stealing!");
        }
    }

    public void No_Reaction(ActionEvent event) throws IOException, ParseException {
        player.No_Reaction();
        Player.HumanAllow(true);
    }

    public void next(ActionEvent event) throws IOException, ParseException, InterruptedException {
        react.setVisible(false);
        TurnChanger.setTurn();
    }

    public void Kill1(ActionEvent event) throws IOException, ParseException, InterruptedException {
        killPlayer(0);
    }

    public void Kill2(ActionEvent event) throws IOException, ParseException, InterruptedException {
        killPlayer(1);
    }

    public void killPlayer(int i) throws IOException, ParseException, InterruptedException {
        BoardController.PlayertoKill = i;
        mssg.setText("");

        Action action=GameState.lastAction;
        Player player1=findPlayerr(GameState.firstPlayer.playerNumber);
        Player player2=findPlayerr(GameState.lastPlayer.playerNumber);
        new Player().finalKill(player1,player2,action);

    }

    public Player findPlayerr(int i){
        if(i==1){
            return Players.instance.getHuman();
        }
        if(i==2){
            return Players.instance.getA();
        }
        if(i==3){
            return Players.instance.getB();
        }
        if(i==4){
            return Players.instance.getC();
        }

        return null;
    }

}