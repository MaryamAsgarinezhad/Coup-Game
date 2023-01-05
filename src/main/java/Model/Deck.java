package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Deck {

    private static Deck instance=new Deck(new ArrayList<>(Arrays.asList(
            new Card(Suit.Assassin),new Card(Suit.Assassin),new Card(Suit.Assassin),
            new Card(Suit.Contessa),new Card(Suit.Contessa),new Card(Suit.Contessa),
            new Card(Suit.Captain),new Card(Suit.Captain),new Card(Suit.Captain),
            new Card(Suit.Duke),new Card(Suit.Duke),new Card(Suit.Duke),
            new Card(Suit.Ambassador),new Card(Suit.Ambassador),new Card(Suit.Ambassador))),
            42);

    private ArrayList<Card> cards;
    private int CionRemained;

    public static Deck getInstance()
    {
        return instance;
    }

    private Deck(ArrayList<Card> cards, int cionRemained) {
        this.cards = cards;
        CionRemained = cionRemained;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getCionRemained() {
        return CionRemained;
    }

    public void setCards(ArrayList<Card> deck) {
        this.cards = deck;
    }

    public void setCionRemained(int cionRemained) {
        CionRemained = cionRemained;
    }
}
