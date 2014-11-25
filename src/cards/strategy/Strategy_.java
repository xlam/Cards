package cards.strategy;

import cards.Card;
import cards.Hand;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public interface Strategy_ {

    /**
     * Determines if hand have card suitable to move
     * @param hand Hand object to find card to move
     * @return Card if one is found, null otherwise
     */
    Card move(Hand hand);

    /**
     * Searches hand for higher rank card or trump card suitable to beat card
     * @param card Card to beat
     * @return Card if one is found, null otherwise
     */
    Card beat(Card card);
}