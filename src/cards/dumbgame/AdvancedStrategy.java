package cards.dumbgame;

import cards.Logger;
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
        List<Card> validCards = s.getValidCardsToMove(hand, cardsInAction);
        float value = s.getValueOf(hand, trump);
        List handCards = hand.toList();
        Card nextRandomCard = s.getRandomCard();
        handCards.add(nextRandomCard);
        Logger.log("AS: next card (random): " + nextRandomCard);
        Logger.log("AS: hand [" + hand + "] value: " + value);
        String values = "AS: new hand values: [";
        for (Card c: validCards) {
            handCards.remove(c);
            float newValue = s.getValueOf(handCards, trump);
            values += c.getSymbol() + "/" + newValue + " ";
            if (newValue > value) {
                if (newValue - value > 0.35) {
                    moveCard = c;
                    value = newValue;
                }
            }
            handCards.add(c);
        }
        Logger.log(values + "]");
        if (moveCard == null && cardsInAction.isEmpty())
            moveCard = ((DumbHand)hand).getLowestCard(trump);
        return moveCard;
    }

    @Override
    public Card beat(Card card, Hand hand, Suit trump) {
        Card beatCard = null;
        DumbService s = new DumbService();
        List<Card> validCards = s.getValidCardsToBeat(card, hand, trump);
        if (!(validCards.isEmpty()))
            beatCard = validCards.get(0);
        return beatCard;
    }

    protected List getPairs(List<Card> cards) {
        List<Card> pairs = new ArrayList();
        for (Rank rank: getRanks(cards)) {
            int count = 0;
            Card card = null;
            for (Card c: cards) {
                if (c.getRank().equals(rank)) {
                    if (++count == 1)
                        card = c;
                }
            }
            if (count > 1)
                pairs.add(card);
        }
        return pairs;
    }

    private List<Rank> getRanks(List<Card> cards) {
        List<Rank> ranks = new ArrayList();
        for (Card c: cards) {
            Rank rank = c.getRank();
            if (!(ranks.contains(rank)))
                ranks.add(rank);
        }
        return ranks;
    }
}
