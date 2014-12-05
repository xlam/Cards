package cards.dumbgame;

import cards.common.*;

/**
 *
 * @author Sergey
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        this.name = name;
        this.hand = new DumbHand();
    }

    public Card beat(Card card) {
        return new Card(Suit.HEARTS, Rank.TEN);
    }
}
