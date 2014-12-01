package cards.dumbgame;

import cards.*;

public class DumbDeck extends Deck {

    public DumbDeck() {
        Rank.setAceHigh();
        for (Object s : Suit.VALUES) {
            for (Object r : Rank.VALUES_ACE_HIGH) {
                Rank rank = (Rank) r;
                if (rank.compareTo(Rank.FIVE) > 0) {
                    deck.add(new Card((Suit) s, rank));
                }
            }
        }
    }
}
