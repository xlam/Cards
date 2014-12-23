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
        // huge perfomance impact here
        //updateKnownCards("move", hand);
        DumbHand dumbHand = (DumbHand) hand;
        if (hand.isEmpty())
            return null;
        Card moveCard = null;
        if (cardsInAction.isEmpty()) {
            moveCard = dumbHand.getLowestCard(trump);
        } else {
            for (Card card : cardsInAction) {
                for (Card c: dumbHand.toList()) {
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
        // huge perfomance impact here
        //updateKnownCards("beat", hand);
        Card beatCard = findBeatCardOfSameSuit(card, hand);
        if (beatCard == null && card.suitIsNot(trump))
            beatCard = ((DumbHand)hand).getLowestTrump(trump);
        lastCardsInAction.clear();
        lastCardsInAction = getSupervisor().getCardsInAction();
        lastAction = "beat";
        return beatCard;
    }

    private Card findBeatCardOfSameSuit(Card card, Hand hand) {
        List<Card> cards = hand.getAllBySuitSorted(card.getSuit());
        for (Card c : cards)
            if (c.getRank().compareTo(card.getRank()) > 0)
                return c;
        return null;
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
