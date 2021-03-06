package cards.dumbgame;

import cards.common.*;
import java.util.List;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public interface Strategy {

    /**
     * Determines if hand have card suitable to move
     * @param hand Hand object to find card to move
     * @param cardsInAction Card whose are lies on the table
     * @param trump Trump suit of the current game
     * @return Card if one is found, null otherwise
     */
    Card move(Hand hand, List<Card> cardsInAction, Suit trump);

    /**
     * Searches hand for higher rank card or trump card suitable to beat card
     * @param card Card to beat
     * @param hand Hand in which to search card to beat
     * @param trump Trump suit
     * @return Card if one is found, null otherwise
     */
    Card beat(Card card, Hand hand, Suit trump);
}
