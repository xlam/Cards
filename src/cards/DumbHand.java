package cards;

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

    public Card getHighest(Suit trumpSuit) {
        Rank.setAceHigh();
        Card.setSortRankFirst();
        Collections.sort(hand);
        if (allSuit(trumpSuit)) return (Card) hand.get(hand.size()-1);
        Card result = null;
        Card card;
        for (Object c : hand) {
            card = (Card) c;
            if (card.getSuit().equals(trumpSuit))
                return card;
            else
                result = card;
        }
        return result;
    }
    
    public int compareTo(DumbHand hand, Suit trump) {
        Card highestCardHand1 = getHighest(trump);
        Card highestCardHand2 = hand.getHighest(trump);
        System.out.println("Highest card of hand1 " + this + " is " + highestCardHand1 + ". Trump is " + trump);
        System.out.println("Highest card of hand2 " + hand + " is " + highestCardHand2 + ". Trump is " + trump);
        Suit suit1 = highestCardHand1.getSuit();
        Suit suit2 = highestCardHand2.getSuit();
        if ((suit1.compareTo(suit2) == 0) && (suit1.compareTo(trump) == 0))
            return highestCardHand1.compareTo(highestCardHand2);
        return 0;
    }

}
