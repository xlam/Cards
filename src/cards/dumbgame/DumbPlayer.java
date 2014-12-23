package cards.dumbgame;

import cards.common.Card;
import cards.common.Player;
import cards.common.Suit;
import java.util.List;

/**
 *
 * @author Sergey
 */
public abstract class DumbPlayer extends Player {
    public abstract Card move(List cardsInAction, Suit trump);
    public abstract Card beat(Card card, Suit trump);
    public abstract Strategy getStrategy();

    protected DumbService service;

    protected DumbService getService() {
        if (null == service)
            service = new DumbService();
        return service;
    }

    @Override
    public DumbHand getHand() {
        return (DumbHand)hand;
    }
}
