package Model;

import Game.GameState;
import Game.TurnChanger;
import GuiController.Images;
import com.example.demo1.BoardController;
import com.example.demo1.Players;
import javafx.scene.image.ImageView;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Player extends BoardController {

    public static Suit StealBlocker;
    public static Suit suitRemoved;
    public boolean isHuman;
    public ArrayList<Card> Hand=new ArrayList<>();
    public int coinNumber;
    public int playerNumber;

    public Player(boolean isHuman, ArrayList<Card> hand, int coinNumber, int playerNumber) throws IOException, ParseException {
        this.isHuman = isHuman;
        Hand = hand;
        this.coinNumber = coinNumber;
        this.playerNumber=playerNumber;
    }

    public Player() throws IOException, ParseException {
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public ArrayList<Card> getHand() {
        return Hand;
    }

    public int getCoinNumber() {
        return coinNumber;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }

    public void setHand(ArrayList<Card> hand) {
        Hand = hand;
    }

    public void setCoinNumber(int coinNumber) throws IOException, ParseException {
        this.coinNumber = coinNumber;
    }

    public boolean income() throws IOException, ParseException {
        setUndoneLastAction(this,null,Action.Income);

        if(Deck.getInstance().getCionRemained()>0){
            this.setCoinNumber(this.getCoinNumber()+1);
            Deck.getInstance().setCionRemained(Deck.getInstance().getCionRemained()-1);

            setLastAction(this,null,Action.Income);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean foreignAid() throws IOException, ParseException, InterruptedException {
        setUndoneLastAction(this,null,Action.ForeignAid);

        if(this.AllowedAction()){
            if(this.isHuman==false && Players.getInstance().getRemovedPlayers().get(1)==0){
                static_react.setVisible(true);
                return false;
            }
            else {
                if(Deck.getInstance().getCionRemained()>1){
                    this.setCoinNumber(this.getCoinNumber()+2);
                    Deck.getInstance().setCionRemained(Deck.getInstance().getCionRemained()-2);

                    setLastAction(this,null,Action.ForeignAid);
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }

    public Suit MakeSuit(String string){
        if(string.equals("Duke")){
            return Suit.Duke;
        }
        if(string.equals("Assassin")){
            return Suit.Assassin;
        }
        if(string.equals("Captain")){
            return Suit.Captain;
        }
        if(string.equals("Ambassador")){
            return Suit.Ambassador;
        }
        if(string.equals("Contessa")){
            return Suit.Contessa;
        }
        return null;
    }

    public boolean exchange1coin(String string) throws IOException, ParseException {
        setUndoneLastAction(this,null,Action.Exchange1coin);

        if(this.getCoinNumber()<1 || Deck.getInstance().getCards().size()<1){
            return false;
        }
        else{
            this.setCoinNumber(this.getCoinNumber()-1);
            Deck.getInstance().setCionRemained(Deck.getInstance().getCionRemained()+1);

            Suit suit=MakeSuit(string);

            int i= ThreadLocalRandom.current().nextInt(0,Deck.getInstance().getCards().size());
            Card card1=Deck.getInstance().getCards().remove(i);

            Card card=new Card(suit);
            Deck.getInstance().getCards().add(card);

            for(Card temp:this.getHand()){
                if(temp.getSuit()==suit){
                    this.getHand().remove(temp);
                    break;
                }
            }
            this.getHand().add(card1);

            setLastAction(this,null,Action.Exchange1coin);
            return true;
        }
    }

    public boolean tax() throws IOException, ParseException, InterruptedException {
        setUndoneLastAction(this,null,Action.Tax);

        if(this.AllowedAction()){
            if(this.isHuman==false && Players.getInstance().getRemovedPlayers().get(1)==0){
                static_react.setVisible(true);
                return false;
            }
            else{
                if(Deck.getInstance().getCionRemained()>2){
                    this.setCoinNumber(this.getCoinNumber()+3);
                    Deck.getInstance().setCionRemained(Deck.getInstance().getCionRemained()-3);

                    setLastAction(this,null,Action.Tax);
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            return true;
        }
    }

    public Card coupSignal() throws IOException, ParseException {
        int i;
        if(this.isHuman){
            if(BoardController.PlayertoKill==10){
                static_mssg.setText("choose player to kill.");
                return null;
            }
            else{
                i=BoardController.PlayertoKill;
                BoardController.PlayertoKill=10;
            }
        }
        else{
            i= ThreadLocalRandom.current().nextInt(0,this.getHand().size());
        }

        Card ans=this.getHand().remove(i);

        if(this.getHand().size()==0){
            Players.getInstance().getAllPlayers().remove(this);
            Players.getInstance().getRemovedPlayers().set(this.playerNumber,1);
            Deck.getInstance().setCionRemained(Deck.getInstance().getCionRemained()+this.coinNumber);

            TurnChanger.setEvent(findPlayer(this.playerNumber)+" LOST THE GAME !");
            fire(this);
        }

        return ans;
    }

    public boolean Coup(Player player) throws IOException, ParseException {
        setUndoneLastAction(this,player,Action.Coup);
        if(this.getCoinNumber()<7){
            return false;
        }
        else{
            this.setCoinNumber(this.getCoinNumber()-7);
            Deck.getInstance().setCionRemained(Deck.getInstance().getCionRemained()+7);

            Card ans=player.coupSignal();
            if(ans==null){
                return false;
            }
            suitRemoved=ans.getSuit();

            getImageView(static_imNumber).setImage(new Images().getImage(suitRemoved.toString()));
            static_imNumber++;

            eventSetter(player,"KILL",ans);

            setLastAction(this,player,Action.Coup);
            return true;
        }
    }

    public boolean Kill(Player player) throws IOException, ParseException, InterruptedException {
        if(this.getCoinNumber()<3){
            static_mssg.setText(findPlayer(this.playerNumber)+" have less then 3 coins ,take another action!");
            return false;
        }

        setUndoneLastAction(this,player,Action.Assassinate);

        if(this.AllowedAction()){
            if(this.isHuman==false && Players.getInstance().getRemovedPlayers().get(1)==0){
                static_react.setVisible(true);
                return false;
            }
            else {
                if(this.getCoinNumber()<3){
                    return false;
                }
                else{
                    this.setCoinNumber(this.getCoinNumber()-3);
                    Deck.getInstance().setCionRemained(Deck.getInstance().getCionRemained()+3);

                    Card ans=player.coupSignal();
                    if(ans==null){
                        return false;
                    }
                    suitRemoved=ans.getSuit();


                    getImageView(static_imNumber).setImage(new Images().getImage(suitRemoved.toString()));
                    static_imNumber++;

                    setLastAction(this,player,Action.Assassinate);
                    return true;
                }
            }
        }
        else {
            return true;
        }
    }

    public boolean Steal(Player player) throws IOException, ParseException, InterruptedException {
        setUndoneLastAction(this,player,Action.Steal);

        if(this.AllowedAction()){
            if(this.isHuman==false && Players.getInstance().getRemovedPlayers().get(1)==0){
                static_react.setVisible(true);
                return false;
            }
            else {
                if(player.getCoinNumber()<1){
                    return false;
                }
                else{
                    if(player.getCoinNumber()==1){
                        this.setCoinNumber(this.getCoinNumber()+1);
                        player.setCoinNumber(player.getCoinNumber()-1);

                        setLastAction(this,player,Action.Steal);
                        return true;
                    }
                    else{
                        this.setCoinNumber(this.getCoinNumber()+2);
                        player.setCoinNumber(player.getCoinNumber()-2);

                        setLastAction(this,player,Action.Steal);
                        return true;
                    }
                }
            }
        }
        else {
            return true;
        }
    }

    public ArrayList<Card> challenge(Player challenger) throws IOException, ParseException {
        setLastAction(GameState.undonefirstPlayer,GameState.undonelastPlayer,GameState.undonelastAction);

        Player player1=GameState.undonefirstPlayer;
        Action action=GameState.undonelastAction;
        Suit suit=findChallengedCharacter(action);
        ArrayList<Card> ans=new ArrayList<>();

        eventSetter(this,player1,Action.Challenge);

        if(player1.challengeSignal(suit)!=null){

            player1.exchangeWithoutCoin(suit);

            Card burnt=challenger.burn1Card();
            ans.add(burnt);
            ans.add(null);

            eventSetter(challenger,"KILL",burnt);

            getImageView(static_imNumber).setImage(new Images().getImage(burnt.getSuit().toString()));
            static_imNumber++;
            return ans;
        }
        else{
            Card burnt=player1.burn1Card();
            ans.add(burnt);

            eventSetter(player1,"KILL",burnt);

            getImageView(static_imNumber).setImage(new Images().getImage(burnt.getSuit().toString()));
            static_imNumber++;
            return ans;

        }
    }

    public Card challengeSignal(Suit suit){
        for(Card item:this.getHand()){
            if(item.getSuit().equals(suit)){
                return item;
            }
        }
        return null;
    }

    public Card burn1Card() throws IOException, ParseException {
        int i;
//        if(GameState.lastAction!=Action.Assassinate){
//            System.out.println(1111);
//            if(this.isHuman){
//                System.out.println(4444);
//                if(BoardController.PlayertoKill==10){
//                    System.out.println(5555);
//                    static_mssg.setText("choose player to burn.");
//                    return null;
//                }
//                else{
//                    System.out.println(6666);
//                    i=BoardController.PlayertoKill;
//                    BoardController.PlayertoKill=10;
//                }
//            }
//            else{
//                System.out.println(3333);
//                i= ThreadLocalRandom.current().nextInt(0,this.getHand().size());
//            }
//        }
//        else {
//            System.out.println(2222);
//            i=ThreadLocalRandom.current().nextInt(0, this.getHand().size());
//        }
        i=ThreadLocalRandom.current().nextInt(0, this.getHand().size());
        Card ans=this.getHand().remove(i);

        if(this.getHand().size()==0){
            Players.getInstance().getAllPlayers().remove(this);
            Players.getInstance().getRemovedPlayers().set(this.playerNumber,1);
            Deck.getInstance().setCionRemained(Deck.getInstance().getCionRemained()+this.coinNumber);

            TurnChanger.setEvent(findPlayer(this.playerNumber)+" LOST THE GAME !");
            fire(this);
        }

        return ans;
    }

    public void exchangeWithoutCoin(Suit suit1){
        String string=this.challengeSignal(suit1).getSuit().toString();

        Suit suit=MakeSuit(string);

        int i= ThreadLocalRandom.current().nextInt(0,Deck.getInstance().getCards().size());
        Card card1=Deck.getInstance().getCards().remove(i);

        Card card=new Card(suit);
        Deck.getInstance().getCards().add(card);

        for(Card temp:this.getHand()){
            if(temp.getSuit()==suit){
                this.getHand().remove(temp);
                break;
            }
        }
        this.getHand().add(card1);

    }

    public Suit findChallengedCharacter(Action action){
        if(action==Action.Tax || action==Action.Block_ForeignAid){
            return Suit.Duke;
        }

        if(action==Action.Assassinate){
            return Suit.Assassin;
        }

        if(action==Action.Exchange || (action==Action.Block_Stealing && StealBlocker==Suit.Ambassador)){
            return Suit.Ambassador;
        }

        if(action==Action.Steal || (action==Action.Block_Stealing && StealBlocker==Suit.Captain)){
            return Suit.Captain;
        }

        if(action==Action.Block_Assassination){
            return Suit.Contessa;
        }

        return Suit.Duke;
    }

    public void setLastAction(Player player1,Player player2,Action action){
        GameState.firstPlayer=player1;
        GameState.lastPlayer=player2;
        GameState.lastAction=action;
    }

    public void setUndoneLastAction(Player player1,Player player2,Action action){
        GameState.undonefirstPlayer=player1;
        GameState.undonelastPlayer=player2;
        GameState.undonelastAction=action;

        eventSetter(player1,player2,action);
    }

    public boolean AllowedAction() throws IOException, ParseException, InterruptedException {
        boolean ans=true;

        for(Player p:this.findOpponents()){
            if(!p.newUndoneAction()){
                ans=false;
            }
        }
        return ans;
    }

    public ArrayList<Player> findOpponents() throws IOException, ParseException {
        ArrayList<Player> ans=new ArrayList<>();

        for (Player p:Players.getInstance().getAllPlayers()){
            if(p.getPlayerNumber()!=this.getPlayerNumber()){
                ans.add(p);
            }
        }

        return ans;
    }

    public boolean newUndoneAction() throws IOException, ParseException, InterruptedException {
        return true;
    }

    public boolean Block_ForeignAid() throws IOException, ParseException, InterruptedException {
        setLastAction(GameState.undonefirstPlayer,GameState.undonelastPlayer,GameState.undonelastAction);
        setUndoneLastAction(this,GameState.undonefirstPlayer,Action.Block_ForeignAid);

        if(this.AllowedAction()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean Block_Stealing1() throws IOException, ParseException, InterruptedException {
        StealBlocker=Suit.Ambassador;

        setLastAction(GameState.undonefirstPlayer,GameState.undonelastPlayer,GameState.undonelastAction);
        setUndoneLastAction(this,GameState.undonefirstPlayer,Action.Block_Stealing);

        if(this.AllowedAction()){
            return true;
        }
        else{
            return false;
        }
    }

    public void No_Reaction(){
        setLastAction(GameState.undonefirstPlayer,GameState.undonelastPlayer,GameState.undonelastAction);
    }

    public boolean Block_Assassination() throws IOException, ParseException, InterruptedException {
        setLastAction(GameState.undonefirstPlayer,GameState.undonelastPlayer,GameState.undonelastAction);
        setUndoneLastAction(this,GameState.undonefirstPlayer,Action.Block_Assassination);

        if(this.AllowedAction()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean Block_Stealing2() throws IOException, ParseException, InterruptedException {
        StealBlocker=Suit.Captain;

        setLastAction(GameState.undonefirstPlayer,GameState.undonelastPlayer,GameState.undonelastAction);
        setUndoneLastAction(this,GameState.undonefirstPlayer,Action.Block_Stealing);

        if(this.AllowedAction()){
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<Card> exchange() throws IOException, ParseException, InterruptedException {
        setUndoneLastAction(this,null,Action.Exchange);

        ArrayList<Card> ans=new ArrayList<>();
        if(this.AllowedAction()){
            if(this.isHuman==false && Players.getInstance().getRemovedPlayers().get(1)==0){
                static_react.setVisible(true);
                return null;
            }
            else {
                int i = ThreadLocalRandom.current().nextInt(0, Deck.getInstance().getCards().size());
                ans.add(Deck.getInstance().getCards().remove(i));

                int j = ThreadLocalRandom.current().nextInt(0, Deck.getInstance().getCards().size());
                ans.add(Deck.getInstance().getCards().remove(j));

                return ans;
            }
        }
        else {
            return ans;
        }
    }

    public void eventSetter(Player p1,String s,Card card){
        String s1=findPlayer(p1.playerNumber);
        String s2=s;
        String s3=card.getSuit().toString();

        TurnChanger.setEvent(s1+"->"+s2+": "+s3);
//        System.out.println(s1+"->"+s2+": "+s3);
    }

    public void eventSetter(Player p1,Player p2,Action action){
        String s1="";
        String s2="";
        String s3="";

        if(p2==null){
            if(action==Action.Exchange1coin || action==Action.Exchange){
                s2="Deck";
            }
            else {
                s2="Bank";
            }
        }
        else{
            s2=findPlayer(p2.playerNumber);
        }
        s1=findPlayer(p1.playerNumber);
        s3=action.toString();

        TurnChanger.setEvent(s1+"->"+s2+": "+s3);
//        System.out.println(s1+"->"+s2+": "+s3);
    }

    public String findPlayer(int i){
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

    public ImageView getImageView(int i) {
        if (i == 0) {
            return static_;
        }
        if (i == 1) {
            return static_1;
        }
        if (i == 2) {
            return static_2;
        }
        if (i == 3) {
            return static_3;
        }
        if (i == 4) {
            return static_4;
        }
        if (i == 5) {
            return static_5;
        }
        if (i == 6) {
            return static_6;
        }
        if (i == 7) {
            return static_7;
        }
        if (i == 8) {
            return static_8;
        }
        if (i == 9) {
            return static_9;
        }
        if (i == 10) {
            return static_10;
        }
        if (i == 11) {
            return static_11;
        }
        if (i == 12) {
            return static_12;
        }
        if (i == 13) {
            return static_13;
        }
        return static_;
    }

    public Player getRandomPalyer(int i,int banned) throws IOException, ParseException {
        if(i<banned){
            return Players.getInstance().getAllPlayers().get(i);
        }
        else {
            if(i+1==Players.getInstance().getAllPlayers().size()){
                return Players.getInstance().getAllPlayers().get(i);
            }
            return Players.getInstance().getAllPlayers().get(i+1);
        }
    }

    public static void HumanAllow(boolean b) throws IOException, ParseException {
        Action action=GameState.lastAction;
        Player P1=GameState.firstPlayer;
        Player P2=GameState.lastPlayer;

        if(b){
            if(action==Action.Steal){
                if(P2.getCoinNumber()<1){
                    TurnChanger.setEvent(P1.findPlayer(P2.playerNumber)+" have no coins!");
                    return;
                }
                if(P2.getCoinNumber()==1){
                    P1.setCoinNumber(P1.getCoinNumber()+1);
                    P2.setCoinNumber(P2.getCoinNumber()-1);
                }
                else{
                    P1.setCoinNumber(P1.getCoinNumber()+2);
                    P2.setCoinNumber(P2.getCoinNumber()-2);
                }

                TurnChanger.setEvent(P1.findPlayer(P1.playerNumber)+" took the action successfully!");
            }

            if(action==Action.Tax){
                P1.setCoinNumber(P1.getCoinNumber()+3);
                Deck.getInstance().setCionRemained(Deck.getInstance().getCionRemained()-3);

                TurnChanger.setEvent(P1.findPlayer(P1.playerNumber)+" took the action successfully!");
            }

            if(action==Action.ForeignAid){
                P1.setCoinNumber(P1.getCoinNumber()+2);
                Deck.getInstance().setCionRemained(Deck.getInstance().getCionRemained()-2);

                TurnChanger.setEvent(P1.findPlayer(P1.playerNumber)+" took the action successfully!");
            }

            if(action==Action.Assassinate){
                P1.setCoinNumber(P1.getCoinNumber()-3);
                Deck.getInstance().setCionRemained(Deck.getInstance().getCionRemained()+3);

                Card ans=P2.coupSignal();
                if(ans==null){
                    return;
                }
                suitRemoved=ans.getSuit();


                static_getImageView(static_imNumber).setImage(new Images().getImage(suitRemoved.toString()));
                static_imNumber++;

                TurnChanger.setEvent(P1.findPlayer(P2.playerNumber)+"->KILL: "+suitRemoved.toString());
            }

            if(action==Action.Exchange){
                int ambassadorIndex=0;
                for (Card card:P1.Hand){
                    if(card.getSuit()==Suit.Ambassador){
                        ambassadorIndex=P1.Hand.indexOf(card);
                    }
                }

                ArrayList<Card> ans=new ArrayList<>();
                int i = ThreadLocalRandom.current().nextInt(0, Deck.getInstance().getCards().size());
                ans.add(Deck.getInstance().getCards().remove(i));

                int j = ThreadLocalRandom.current().nextInt(0, Deck.getInstance().getCards().size());
                ans.add(Deck.getInstance().getCards().remove(j));

                Card Assassin;
                Card NotAssassin;

                if(ans.get(0).getSuit()==Suit.Assassin || ans.get(1).getSuit()==Suit.Assassin){
                    if(ans.get(0).getSuit()==Suit.Assassin){
                        Assassin=ans.get(0);
                        NotAssassin=ans.get(1);
                    }
                    else{
                        Assassin=ans.get(1);
                        NotAssassin=ans.get(0);
                    }
                    Card card1=P1.Hand.remove(ambassadorIndex);
                    P1.Hand.add(Assassin);

                    Deck.getInstance().getCards().add(NotAssassin);
                    Deck.getInstance().getCards().add(card1);
                }
                else{
                    Deck.getInstance().getCards().add(ans.get(0));
                    Deck.getInstance().getCards().add(ans.get(0));
                }
            }
        }
    }

    private static ImageView static_getImageView(int i)  {
        if (i == 0) {
            return static_;
        }
        if (i == 1) {
            return static_1;
        }
        if (i == 2) {
            return static_2;
        }
        if (i == 3) {
            return static_3;
        }
        if (i == 4) {
            return static_4;
        }
        if (i == 5) {
            return static_5;
        }
        if (i == 6) {
            return static_6;
        }
        if (i == 7) {
            return static_7;
        }
        if (i == 8) {
            return static_8;
        }
        if (i == 9) {
            return static_9;
        }
        if (i == 10) {
            return static_10;
        }
        if (i == 11) {
            return static_11;
        }
        if (i == 12) {
            return static_12;
        }
        if (i == 13) {
            return static_13;
        }
        return static_;
    }

    private void fire(Player P){
        if(P.isHuman){
            static_taction.setVisible(false);
            static_personCoins.setVisible(false);
            static_labbbb.setVisible(false);

            static_react.fire();
        }
    }

    public void finalKill(Player p1,Player p2,Action action) throws IOException, ParseException {

        Card ans=p2.coupSignal();
        suitRemoved=ans.getSuit();

        getImageView(static_imNumber).setImage(new Images().getImage(suitRemoved.toString()));
        static_imNumber++;

        eventSetter(Players.instance.getHuman(),"KILL",ans);
    }
}
