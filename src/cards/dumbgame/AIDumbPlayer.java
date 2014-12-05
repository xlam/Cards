package cards.dumbgame;

import cards.common.*;
import java.util.List;

public class AIDumbPlayer extends DumbPlayer {

    private final Strategy strategy;
    private Supervisor supervisor;

    public AIDumbPlayer() {
        super();
        strategy = new SimpleStrategy();
        hand = new DumbHand();
    }

    public AIDumbPlayer(String name) {
        this();
        this.name = name;
    }

    public AIDumbPlayer(String name, AbstractStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
        hand = new DumbHand();
    }

    private Supervisor getSupervisor() {
        if (null == supervisor)
            supervisor = Supervisor.getInstance();
        return supervisor;
    }

    public List<Card> getCardsInAction() {
        return getSupervisor().getCardsInAction();
    }

    public Suit getTrumpSuit() {
        return getSupervisor().getTrumpSuit();
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public Card move() {
        Card card = strategy.move(hand, getCardsInAction(), getTrumpSuit());
        if (card != null) {
            hand.remove(card);
            return card;
        }
        return null;
    }

    public Card beat(Card card) {
        Card beatCard = strategy.beat(card, hand, getTrumpSuit());
        if (beatCard != null) {
            hand.remove(beatCard);
            return beatCard;
        }
        return null;
    }

}
