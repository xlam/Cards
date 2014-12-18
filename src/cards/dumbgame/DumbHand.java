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
    public Card getLowestCard(Suit trumpSuit) {
        if (hand.isEmpty())
            return null;
        Card card = (Card) hand.get(0);
        DumbService s = new DumbService();
        for (Object o : hand) {
            Card c = (Card) o;
            if (s.getValueOf(c, trumpSuit) < s.getValueOf(card, trumpSuit))
                card = c;
        }
        return card;
    }

    public Card getHighestCard(Suit trumpSuit) {
        if (hand.isEmpty())
            return null;
        Card card = (Card) hand.get(0);
        DumbService s = new DumbService();
        for (Object o : hand) {
            Card c = (Card) o;
            if (s.getValueOf(c, trumpSuit) > s.getValueOf(card, trumpSuit))
                card = c;
        }
        return card;
    }

    public Card getLowestTrump(Suit trump) {
        List<Card> cards = getAllBySuit(trump);
        if (cards.isEmpty())
            return null;
        Rank.setAceHigh();
        Collections.sort(cards);
        return cards.get(0);
    }

    public int compareTo(DumbHand hand, Suit trump) {
        if (this.hand.isEmpty() && hand.isEmpty())
            return 0;
        if (this.hand.isEmpty())
            return -1;
        if (hand.isEmpty())
            return 1;
        Card highestCardHand1 = getHighestCard(trump);
        Card highestCardHand2 = hand.getHighestCard(trump);
        Suit suit1 = highestCardHand1.getSuit();
        Suit suit2 = highestCardHand2.getSuit();
        if (suit1.equals(trump) && !(suit2.equals(trump)) ||
            suit2.equals(trump) && !(suit1.equals(trump))) {
                return suit1.equals(trump) ? 1 : -1;
        }
        Rank.setAceHigh();
        return highestCardHand1.getRank().compareTo(highestCardHand2.getRank());
    }

}
