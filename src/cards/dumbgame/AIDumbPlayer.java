package cards.dumbgame;

import cards.common.*;
import java.util.List;

public class AIDumbPlayer extends DumbPlayer {

    private final Strategy strategy;

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

    @Override
    public Strategy getStrategy() {
        return strategy;
    }

    @Override
    public Card move() {
        Card card = strategy.move(hand, getCardsInAction(), getTrumpSuit());
        if (card != null) {
            hand.remove(card);
            return card;
        }
        return null;
    }

    @Override
    public Card beat(Card card) {
        Card beatCard = strategy.beat(card, hand, getTrumpSuit());
        if (beatCard != null) {
            hand.remove(beatCard);
            return beatCard;
        }
        return null;
    }

}
