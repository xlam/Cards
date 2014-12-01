package cards.common;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    protected ArrayList deck;
    protected int index;

    public Deck() {
        deck = new ArrayList();
        index = 0;
    }

    /*
     * Returns default 52 cards deck (all those in Rank and Suit classes)
     */
    public static Deck defaultDeck() {
        Deck deck = new Deck();
        for (Object s : Suit.VALUES) {
            for (Object r : Rank.VALUES_ACE_HIGH) {
                deck.add(new Card((Suit) s, (Rank) r));
            }
        }
        return deck;
    }

    public Deck add(Card card) {
        deck.add(card);
        return this;
    }

    public int size() {
        return deck.size();
    }

    public int getCardsRemaining() {
        return deck.size() - index;
    }

    public Card deal() {
        if (index < deck.size()) {
            return (Card) deck.get(index++);
        }
        return null;
    }

    public Deck sort() {
        Collections.sort(deck);
        return this;
    }
    
    public Deck shuffle() {
        Collections.shuffle(deck);
        return this;
    }

    public Deck restore() {
        index = 0;
        return this;
    }

    @Override
    public String toString() {
        String result = "";
        for (Object o : deck) {
            result += " " + ((Card) o).getSymbol();
        }
        return result;
    }

    public Object[] toArray() {
        return deck.toArray();
    }
}
