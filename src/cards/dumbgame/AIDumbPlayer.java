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
    public Card move(List cardsInAction, Suit trump) {
        Card card = strategy.move(hand, cardsInAction, trump);
        if (card != null)
            hand.remove(card);
        return card;
    }

    @Override
    public Card beat(Card card, Suit trump) {
        Card beatCard = strategy.beat(card, hand, trump);
        if (beatCard != null)
            hand.remove(beatCard);
        return beatCard;
    }

}
