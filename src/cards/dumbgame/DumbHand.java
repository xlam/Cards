package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DumbHand extends Hand {


    /**
     * Check if card is not contained within hand and add it
     * @param card Card to add
     * @return DumbHand
     */
    @Override
    public DumbHand add(Card card) {
        if (!hand.contains(card)) super.add(card);
        return this;
    }

    @Override
    public DumbHand addAll(List cards) {
        for (Object c : cards) {
            Card card = (Card) c;
            add(card);
        }
        return this;
    }

    /**
     * Searches hand for lowest rank card suitable for the move, taking
     * trump suit into account
     * @param trumpSuit Suit of trump
     * @return Card if one is found, null otherwise
     */
    public Card getLowest(Suit trumpSuit) {
        Rank.setAceHigh();
        Card.setSortRankFirst();
        Collections.sort(hand);
        if (allSuit(trumpSuit)) return (Card) hand.get(0);
        for (Object c : hand) {
            Card card = (Card) c;
            if (!(card.getSuit().equals(trumpSuit))) return card;
        }
        return null;
    }

    // TODO method seems ugly, try to refactor
    public Card getHighest(Suit trumpSuit) {
        Rank.setAceHigh();
        Card result = (Card) hand.get(0);
        ArrayList<Card> trumps = getTrumps(trumpSuit);
        if (trumps.isEmpty()) {
            for (Object o: hand) {
                Card c = (Card) o;
                if (c.getRank().compareTo(result.getRank()) > 0)
                    result = c;
            }
        }
        else {
            result = trumps.get(0);
            for (Card c: trumps) {
                if (c.getRank().compareTo(result.getRank()) > 0)
                    result = c;
            }
        }
        return result;
    }

    protected ArrayList<Card> getTrumps(Suit trump) {
        ArrayList<Card> trumps = new ArrayList<>();
        for (Object o: hand) {
            Card c = (Card) o;
            if (c.getSuit().compareTo(trump) == 0)
                trumps.add(c);
        }
        return trumps;
    }

    // TODO needs refactoring
    public int compareTo(DumbHand hand, Suit trump) {
        Rank.setAceHigh();
        if (this.hand.isEmpty() && hand.isEmpty())
            return 0;
        if (this.hand.isEmpty())
            return -1;
        if (hand.isEmpty())
            return 1;
        Card highestCardHand1 = getHighest(trump);
        Card highestCardHand2 = hand.getHighest(trump);
        Suit suit1 = highestCardHand1.getSuit();
        Suit suit2 = highestCardHand2.getSuit();
        if (suit1.equals(trump) && !(suit2.equals(trump)) ||
            suit2.equals(trump) && !(suit1.equals(trump))) {
                return suit1.equals(trump) ? 1 : -1;
        }
        return highestCardHand1.getRank().compareTo(highestCardHand2.getRank());
    }

}