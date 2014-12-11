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

    protected List getPairs(List<Card> cards) {
        List pairs = new ArrayList();
        for (Rank rank: getRanks(cards)) {
            int count = 0;
            Card card = null;
            for (Card c: cards) {
                card = c;
                if (c.getRank().equals(rank))
                    count++;
            }
            if (count > 1)
                pairs.add(card);
        }
        return pairs;
    }

    private List<Rank> getRanks(List<Card> cards) {
        List ranks = new ArrayList();
        for (Card c: cards) {
            Rank rank = c.getRank();
            if (!(ranks.contains(rank)))
                ranks.add(rank);
        }
        return ranks;
    }
}
