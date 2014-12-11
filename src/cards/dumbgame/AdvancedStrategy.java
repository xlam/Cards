package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public class AdvancedStrategy extends AbstractStrategy {

    @Override
    public Card move(Hand hand, List<Card> cardsInAction, Suit trump) {
        Card moveCard = null;
        DumbService s = new DumbService();
        ArrayList<Card> validCards = s.getValidCardsToMove(hand, cardsInAction);
        if (!(validCards.isEmpty()))
            moveCard = validCards.get(0);
        return moveCard;
    }

    @Override
    public Card beat(Card card, Hand hand, Suit trump) {
        Card beatCard = null;
        DumbService s = new DumbService();
        ArrayList<Card> validCards = s.getValidCardsToBeat(card, hand, trump);
        if (!(validCards.isEmpty()))
            beatCard = validCards.get(0);
        return beatCard;
    }

}
