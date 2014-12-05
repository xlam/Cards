package cards.dumbgame;

import cards.common.*;

/**
 *
 * @author Sergey
 */
public class HumanPlayer extends Player implements DumbPlayer {

    public HumanPlayer(String name) {
        this.name = name;
        this.hand = new DumbHand();
    }

    @Override
    public Card move() {
        return null;
    }

    @Override
    public Card beat(Card card) {
        return new Card(Suit.HEARTS, Rank.TEN);
    }
}
