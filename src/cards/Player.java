package cards;

import cards.game.Supervisor;
import cards.strategy.*;
import java.util.List;

public class Player {
    
    // as we set strategy at initialisation it may be final
    private final Strategy strategy;
    private String name;
    private DumbHand hand;
    private Supervisor supervisor;

    public Player() {
        name = super.toString();
        strategy = new SimpleStrategy();
        hand = new DumbHand();
    }

    public Player(String name, AbstractStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
        hand = new DumbHand();
    }

    private Supervisor getSupervisor() {
        if (null == supervisor)
            supervisor = Supervisor.getInstance();
        return supervisor;
    }
    
    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Player setHand(Hand hand) {
        this.hand = (DumbHand)hand;
        return this;
    }

    public DumbHand getHand() {
        return hand;
    }

    public List<Card> getCardsInAction() {
        return getSupervisor().getCardsInAction();
    }

    public Suit getTrumpSuit() {
        return getSupervisor().getTrumpSuit();
    }

    public boolean isEmpty() {
        return hand.isEmpty();
    }

    public Player addCard(Card card) {
        hand.add(card);
        return this;
    }

    public Player addCard(List<Card> cards) {
        hand.addAll(cards);
        return this;
    }

    public int numberOfCards() {
        return hand.size();
    }

    public Player clearHand() {
        hand.clear();
        return this;
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

    @Override
    public String toString() {
        return name;
    }
}
