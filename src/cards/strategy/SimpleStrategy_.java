package cards.strategy;

import cards.Card;
import cards.DumbHand;
import cards.Hand;
import cards.Suit;
import java.util.List;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public class SimpleStrategy_ extends AbstractStrategy_ {

    @Override
    public Card move(Hand hand, List<Card> cardsInAction, Suit trump) {
        DumbHand dumbHand = (DumbHand) hand;
        if (dumbHand.isEmpty()) return null;
        Card moveCard = null;
        if (cardsInAction.isEmpty()) {
            moveCard = dumbHand.getLowest(trump);
        } else {
            for (Card card : cardsInAction) {
                for (int i = 0; i < dumbHand.size(); i++) {
                    Card c = (Card) dumbHand.getCard(i);
                    if (c.getRank().equals(card.getRank()) &&
                        !(c.getSuit().equals(trump)))
                            moveCard = c;
                }
            }
        }
        return moveCard;
    }

    @Override
    public Card beat(Card card) {
        // TODO to be implemented
        return null;
    }

}
