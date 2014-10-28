package cards.strategy;

import cards.Card;
import cards.DumbHand;
import java.util.List;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public class SimpleStrategy extends AbstractStrategy {

    @Override
    public Card move() {
        DumbHand hand = (DumbHand) player.getHand();
        if (hand.isEmpty()) return null;
        Card moveCard = null;
        List<Card> cardsInAction = player.getCardsInAction();
        if (cardsInAction.isEmpty()) {
            moveCard = hand.getLowest(player.getTrumpSuit());
        } else {
            for (Card card : cardsInAction) {
                for (int i = 0; i < hand.size(); i++) {
                    Card c = (Card) hand.getCard(i);
                    if (c.getRank().equals(card.getRank())
                            && !(c.getSuit().equals(player.getTrumpSuit()))) moveCard = c;
                }
            }
        }
        return moveCard;
    }

    @Override
    public Card beat(Card card) {

        //TODO: optimize
        DumbHand hand = (DumbHand) player.getHand();
        Card beatCard = null;

        List<Card> cards = hand.getAllBySuit(card.getSuit());
        if (!cards.isEmpty()) {
            for (Card c : cards) {
                if (c.getRank().compareTo(card.getRank()) > 0) beatCard = c;
            }
        } else {
            cards = hand.getAllBySuit(player.getTrumpSuit());
            if (!cards.isEmpty()) {
                if (card.getSuit().equals(player.getTrumpSuit())) {
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
