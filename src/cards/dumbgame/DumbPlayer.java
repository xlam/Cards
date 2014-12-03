package cards.dumbgame;

import cards.common.*;
import java.util.List;

public class DumbPlayer extends Player {

    private final Strategy strategy;
    private Supervisor supervisor;

    public DumbPlayer() {
        super();
        strategy = new SimpleStrategy();
        hand = new DumbHand();
    }

    public DumbPlayer(String name) {
        this();
        this.name = name;
    }

    public DumbPlayer(String name, AbstractStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
        hand = new DumbHand();
    }

    private Supervisor getSupervisor() {
        if (null == supervisor)
            supervisor = Supervisor.getInstance();
        return supervisor;
    }

    @Override
    public DumbHand getHand() {
        return (DumbHand)hand;
    }

    public List<Card> getCardsInAction() {
        return getSupervisor().getCardsInAction();
    }

    public Suit getTrumpSuit() {
        return getSupervisor().getTrumpSuit();
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
