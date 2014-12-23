package cards.dumbgame;

import cards.common.*;
import java.util.List;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public class SimpleStrategy extends AbstractStrategy {

    @Override
    public Card move(Hand hand, List<Card> cardsInAction, Suit trump) {
        // TODO: smells like needs refactoring...
        DumbHand dumbHand = (DumbHand) hand;
        if (dumbHand.isEmpty()) return null;
        Card moveCard = null;
        if (cardsInAction.isEmpty()) {
            moveCard = dumbHand.getLowestCard(trump);
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
    public Card beat(Card card, Hand hand, Suit trump) {
        // TODO: smells like needs refactoring...
        Card beatCard = null;
        List<Card> cards = hand.getAllBySuitSorted(card.getSuit());
        if (!cards.isEmpty()) {
            for (Card c : cards) {
                if (c.getRank().compareTo(card.getRank()) > 0) beatCard = c;
            }
        } else {
            cards = hand.getAllBySuitSorted(trump);
            if (!cards.isEmpty()) {
                if (card.getSuit().equals(trump)) {
                    for (Card c : cards) {
                        if (c.getRank().compareTo(card.getRank()) > 0) beatCard = c;
                    }
                } else {
                    beatCard = cards.get(0);
                }            }
        }
        return beatCard;
    }

}
