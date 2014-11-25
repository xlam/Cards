package cards.strategy;

import cards.Card;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public interface Strategy {

    /**
     * Determines if hand have card suitable to move
     * @return Card if one is found, null otherwise
     */
    Card move();

    /**
     * Searches hand for higher rank card or trump card suitable to beat card
     * @param card Card to beat
     * @return Card if one is found, null otherwise
     */
    Card beat(Card card);
}
