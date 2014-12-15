package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kus
 */
public class DumbService {

    private final HashMap<Rank, Integer> VALUES;
    private final int delta = 10;

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
    }

    public int getValueOf(Card card, Suit trump) {
        if (!(VALUES.containsKey(card.getRank())))
            System.err.println("ERROR: invalid rank: " + card.getRank());
        int d = 0;
        if (card.getSuit().equals(trump))
            d += delta;
        return VALUES.get(card.getRank()) + d;
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

   protected ArrayList getValidCardsToMove(Hand hand, List<Card> cardsInAction) {
        if (cardsInAction.isEmpty())
            return hand.toArrayList();
        ArrayList<Card> validCards = new ArrayList<>();
        for (Card c1: hand.toArrayList())
            for (Card c2: cardsInAction)
                if (c1.getRank().equals(c2.getRank()))
                    validCards.add(c1);
        return validCards;
    }

    protected ArrayList getValidCardsToBeat(Card cardToBeat, Hand hand, Suit trump) {
        ArrayList<Card> validCards = new ArrayList();
        for (Card c: hand.toArrayList())
            if (compareForBeat(c, cardToBeat, trump) > 0)
                validCards.add(c);
        return validCards;
    }

    protected int compareHands(DumbHand hand1, DumbHand hand2, Suit trumpSuit) {
        return (hand1.compareTo(hand2, trumpSuit));
    }

}
