package cards.dumbgame;

import cards.common.Card;
import cards.common.Player;

/**
 *
 * @author Sergey
 */
public abstract class DumbPlayer extends Player {
    public abstract Card move();
    public abstract Card beat(Card card);

    @Override
    public DumbHand getHand() {
        return (DumbHand)hand;
    }
}
