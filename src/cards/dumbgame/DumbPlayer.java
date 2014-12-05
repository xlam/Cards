package cards.dumbgame;

import cards.common.Card;

/**
 *
 * @author Sergey
 */
public interface DumbPlayer {
    public Card move();
    public Card beat(Card card);
}
