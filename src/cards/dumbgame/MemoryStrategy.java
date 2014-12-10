package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public class MemoryStrategy extends AbstractStrategy {

    List<Card> knownEnemyCards = new ArrayList();
    List<Card> knownOutCards = new ArrayList();
    List<Card> possibleDeckCards = new ArrayList();

    private static final List<Card> defaultDeck = new ArrayList();
    private List<Card> lastCardsInAction = new ArrayList();
    private static String lastAction = "";


    public MemoryStrategy() {
        DumbDeck fullDeck = new DumbDeck();
        int i = fullDeck.getCardsRemaining();
        defaultDeck.clear();
        for (int j = 0; j < i; j++) {
            defaultDeck.add(fullDeck.deal());
        }
    }

    @Override
    public Card move(Hand hand, List<Card> cardsInAction, Suit trump) {
        // TODO: looks like needs refactoring...
        updateKnownCards("move", hand);
        DumbHand dumbHand = (DumbHand) hand;
        if (dumbHand.isEmpty()) return null;
        Card moveCard = null;
        if (cardsInAction.isEmpty()) {
            moveCard = dumbHand.getLowest(trump);
        } else {
            for (Card card : cardsInAction) {
                for (int i = 0; i < dumbHand.size(); i++) {
                    Card c = (Card) dumbHand.getCard(i);
                    if (c.getRank().equals(card.getRank())
                            && !(c.getSuit().equals(trump))
                            && c.getRank().compareTo(Rank.JACK) < 0) moveCard = c;
                }
            }
        }
        lastCardsInAction.clear();
        lastCardsInAction = cardsInAction;
        lastAction = "move";
        return moveCard;
    }

    @Override
    public Card beat(Card card, Hand hand, Suit trump) {
        // TODO: looks like needs refactoring...
        updateKnownCards("beat", hand);
        DumbHand dumbHand = (DumbHand) hand;
        Card beatCard = null;

        List<Card> cards = dumbHand.getAllBySuit(card.getSuit());
        if (!cards.isEmpty()) {
            for (Card c : cards) {
                if (c.getRank().compareTo(card.getRank()) > 0) beatCard = c;
            }
        } else {
            cards = dumbHand.getAllBySuit(trump);
            if (!cards.isEmpty()) {
                if (card.getSuit().equals(trump)) {
                    for (Card c : cards) {
                        if (c.getRank().compareTo(card.getRank()) > 0) beatCard = c;
                    }
                } else {
                    beatCard = cards.get(0);
                }
            }
        }
        lastCardsInAction.clear();
        lastCardsInAction = getSupervisor().getCardsInAction();
        lastAction = "beat";
        return beatCard;
    }

    protected void updateKnownCards(String action, Hand hand) {
        if (action.equals("move") && lastAction.equals("move")) {
            for (Card card : lastCardsInAction) {
                knownEnemyCards.add(card);
            }
        } else if (action.equals("beat") && lastAction.equals("move")) {
            for (Card card : lastCardsInAction) {
                knownOutCards.add(card);
            }
        }
        calculatePossibleDeckCards(hand);
    }

    protected void calculatePossibleDeckCards(Hand hand) {
        possibleDeckCards.clear();
        possibleDeckCards.addAll(defaultDeck);
        List<Card> playerCards = hand.toList();
        List<Card> temp = new ArrayList<>();
        for (Card c : possibleDeckCards) {
            for (Card card : knownEnemyCards)
                if (c.isSameAs(card)) temp.add(c);
            for (Card card : knownOutCards)
                if (c.isSameAs(card)) temp.add(c);
            for (Card card : playerCards)
                if (c.isSameAs(card)) temp.add(c);
        }
        possibleDeckCards.removeAll(temp);
    }
}
