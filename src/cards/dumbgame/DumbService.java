package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Kus
 */
public class DumbService {

    private final HashMap<Rank, Integer> VALUES;
    private final int TRUMP_VALUE_DELTA;

    public DumbService() {
        VALUES = new HashMap();
        VALUES.put(Rank.SIX,    1);
        VALUES.put(Rank.SEVEN,  2);
        VALUES.put(Rank.EIGHT,  3);
        VALUES.put(Rank.NINE,   4);
        VALUES.put(Rank.TEN,    5);
        VALUES.put(Rank.JACK,   6);
        VALUES.put(Rank.QUEEN,  7);
        VALUES.put(Rank.KING,   8);
        VALUES.put(Rank.ACE,    9);
        TRUMP_VALUE_DELTA = VALUES.size();
    }

    public int getValueOf(Card card, Suit trump) {
        if (!(VALUES.containsKey(card.getRank())))
            System.err.println("ERROR: invalid rank: " + card.getRank());
        int value = VALUES.get(card.getRank());
        if (card.getSuit().equals(trump))
            value += TRUMP_VALUE_DELTA;
        return value;
    }

    public float getValueOf(List<Card> cards, Suit trump) {
        float result = 0;
        if (cards.isEmpty())
            return result;
        for (Card c: cards)
            result += getValueOf(c, trump);
        return result / cards.size();
    }

    public float getValueOf(Hand hand, Suit trump) {
        return getValueOf(hand.toList(), trump);
    }

    public int compareForBeat(Card card1, Card card2, Suit trump) {
        Suit suit1 = card1.getSuit();
        Suit suit2 = card2.getSuit();
        if (suit1.equals(suit2))
            return VALUES.get(card1.getRank()) - VALUES.get(card2.getRank());
        if (suit1.equals(trump))
            return 1;
        if (suit2.equals(trump))
            return -1;
        return -1;
    }

   protected List getValidCardsToMove(Hand hand, List<Card> cardsInAction) {
        if (cardsInAction.isEmpty())
            return hand.toArrayList();
        List<Card> validCards = new ArrayList<>();
        for (Card c1: hand.toArrayList())
            for (Card c2: cardsInAction)
                if (c1.getRank().equals(c2.getRank()))
                    validCards.add(c1);
        return validCards;
    }

    protected List getValidCardsToBeat(Card cardToBeat, Hand hand, Suit trump) {
        List<Card> validCards = new ArrayList();
        for (Card c: hand.toArrayList())
            if (compareForBeat(c, cardToBeat, trump) > 0)
                validCards.add(c);
        return validCards;
    }

    protected int compareHands(DumbHand hand1, DumbHand hand2, Suit trumpSuit) {
        return (hand1.compareTo(hand2, trumpSuit));
    }

}
