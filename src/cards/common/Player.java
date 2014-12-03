package cards.common;

import java.util.List;

public class Player {

    protected String name;
    protected Hand hand;

    public Player() {
        name = super.toString();
        hand = new Hand();
    }

    public Player(String name) {
        this.name = name;
        hand = new Hand();
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Player setHand(Hand hand) {
        this.hand = hand;
        return this;
    }

    public Hand getHand() {
        return hand;
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

    @Override
    public String toString() {
        return name;
    }

}
