package cards.strategy;

import cards.Card;
import cards.DumbDeck;
import cards.DumbHand;
import cards.Rank;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public class MemoryStrategy extends AbstractStrategy {

    List<Card> knownEnemyCards = new ArrayList<Card>();
    List<Card> knownOutCards = new ArrayList<Card>();
    List<Card> possibleDeckCards = new ArrayList<Card>();

    private List<Card> lastCardsInAction = new ArrayList<Card>();

    private static List<Card> defaultDeck = new ArrayList<Card>();

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
    public Card move() {
        updateKnownCards("move");
        DumbHand hand = (DumbHand) player.getHand();
        if (hand.isEmpty()) return null;
        Card moveCard = null;
        List<Card> cardsInAction = player.getCardsInAction();
        if (cardsInAction.isEmpty()) {
            moveCard = hand.getLowest(player.getTrumpSuit());
        } else {
            for (Card card : cardsInAction) {
                for (int i = 0; i < hand.size(); i++) {
                    Card c = (Card) hand.getCard(i);
                    if (c.getRank().equals(card.getRank())
                            && !(c.getSuit().equals(player.getTrumpSuit()))
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
    public Card beat(Card card) {
        updateKnownCards("beat");
        //TODO: optimize
        DumbHand hand = (DumbHand) player.getHand();
        Card beatCard = null;

        List<Card> cards = hand.getAllBySuit(card.getSuit());
        if (!cards.isEmpty()) {
            for (Card c : cards) {
                if (c.getRank().compareTo(card.getRank()) > 0) beatCard = c;
            }
        } else {
            cards = hand.getAllBySuit(player.getTrumpSuit());
            if (!cards.isEmpty()) {
                if (card.getSuit().equals(player.getTrumpSuit())) {
                    for (Card c : cards) {
                        if (c.getRank().compareTo(card.getRank()) > 0) beatCard = c;
                    }
                } else {
                    beatCard = cards.get(0);
                }
            }
        }
        lastCardsInAction.clear();
        lastCardsInAction = player.getCardsInAction();
        lastAction = "beat";
        return beatCard;
    }


    protected void updateKnownCards(String action) {
        if (action.equals("move") && lastAction.equals("move")) {
            for (Card card : lastCardsInAction) {
                knownEnemyCards.add(card);
            }
        } else if (action.equals("beat") && lastAction.equals("move")) {
            for (Card card : lastCardsInAction) {
                knownOutCards.add(card);
            }
        }
        calculatePossibleDeckCards();
    }

    protected void calculatePossibleDeckCards() {
        possibleDeckCards.clear();
        possibleDeckCards = new ArrayList<Card>(defaultDeck);
        List<Card> temp = new ArrayList<Card>();

        DumbHand hand = (DumbHand) player.getHand();
        List<Card> playerCards = hand.toList();

        for (Card c : possibleDeckCards) {
            for (Card card : knownEnemyCards) {
                if (c.isSameAs(card)) temp.add(c);
            }
            for (Card card : knownOutCards) {
                if (c.isSameAs(card)) temp.add(c);
            }
            for (Card card : playerCards) {
                if (c.isSameAs(card)) temp.add(c);
            }

        }

        for (Card card : temp) {
            possibleDeckCards.remove(card);
        }
    }
}
