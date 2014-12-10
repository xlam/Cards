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

    protected Supervisor supervisor;

    protected Supervisor getSupervisor() {
        if (null == supervisor)
            supervisor = Supervisor.getInstance();
        return supervisor;
    }

    protected List<Card> getCardsInAction() {
        return getSupervisor().getCardsInAction();
    }

    protected Suit getTrumpSuit() {
        return getSupervisor().getTrumpSuit();
    }

    @Override
    public DumbHand getHand() {
        return (DumbHand)hand;
    }
}
