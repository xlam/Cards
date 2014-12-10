package cards.dumbgame;

import cards.common.*;
import java.util.HashMap;

/**
 *
 * @author Kus
 */
public class DumbService {

    private HashMap<Rank, Integer> VALUES;
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
        int d = 0;
        if (card.getSuit().equals(trump))
            d += delta;
        return VALUES.get(card.getRank()) + d;
    }

}
