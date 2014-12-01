package cards.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author User
 */
public class Hand {

    protected List hand;

    public Hand() {
        hand = new ArrayList();
    }

    public Hand add(Card card) {
        hand.add(card);
        return this;
    }

    public boolean remove(Card card) {
        return hand.remove(card);
    }

    public Card remove(int index) {
        return (Card) hand.remove(index);
    }

    public Card getCard(int i) {
        return (Card) hand.get(i);
    }

    public Hand clear() {
        hand.clear();
        return this;
    }

    public boolean isEmpty() {
        return hand.isEmpty();
    }

    public int size() {
        return hand.size();
    }

    public boolean containsCard(Card card) {
        return hand.contains(card);
    }

    /**
     * Checks if all cards in hand is of one suit
     * @param suit Suit to verify cards
     * @return True if suit of all cards is equal to parameter suit
     */
    public boolean allSuit(Suit suit) {
        for (Object c : hand) {
            if (!((Card) c).getSuit().equals(suit)) return false;
        }
        return true;
    }

    /**
     * Returns sorted list of cards which suit is same as parameter.
     * If hand have no cards of that suit, empty list returned.
     * @param suit Suit to compare to
     * @return Sorted list of cards of Suit presented in hand
     */
    public List getAllBySuit(Suit suit) {
        List cards = new ArrayList();
        for (Object c : hand) {
            Card card = (Card) c;
            if (card.getSuit().equals(suit)) cards.add(card);
        }
        Collections.sort(cards);
        return cards;
    }

    /**
     * Adds all cards containing in cards parameter to the hand.
     * Doesn't check cards for doubles.
     * @param cards List of cards
     * @return Hand
     */
    public Hand addAll(List cards) {
        hand.addAll(cards);
        return this;
    }


    @Override
    public String toString() {
        String out = "";
        for (Object c : hand) {
            Card card = (Card) c;
            out += " " + card.getSymbol();
        }
        return out;
    }

    public List<Card> toList() {
        return new ArrayList<>(hand);
    }
}
