package cards.strategy;

import cards.*;
import java.util.List;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public class AdvancedStrategy extends AbstractStrategy {

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
                    if (c.getRank().equals(card.getRank())
                        && !(c.getSuit().equals(trump))
                        && c.getRank().compareTo(Rank.KING) < 0)
                            moveCard = c;
                }
            }
        }
        return moveCard;
    }

    @Override
    public Card beat(Card card, Hand hand, Suit trump) {
        DumbHand dumbHand = (DumbHand) hand;
        Card beatCard = null;

        List<Card> cards = dumbHand.getAllBySuit(card.getSuit());
        if (!cards.isEmpty()) {
            for (Card c : cards) {
                if (c.getRank().compareTo(card.getRank()) > 0) beatCard = c;
            }
        } else {
            cards = dumbHand.getAllBySuit(trump);
            if (!cards.isEmpty()) {
                if (card.getSuit().equals(trump)) {
                    for (Card c : cards) {
                        if (c.getRank().compareTo(card.getRank()) > 0) beatCard = c;
                    }
                } else {
                    beatCard = cards.get(0);
                }
            }
        }

        return beatCard;
    }

}
