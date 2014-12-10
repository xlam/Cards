package cards.dumbgame;

import cards.common.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Sergey
 */
public class HumanDumbPlayer extends DumbPlayer {

    private BufferedReader inputStream;

    public HumanDumbPlayer(String name) {
        this.name = name;
        this.hand = new DumbHand();
        inputStream = new BufferedReader(new InputStreamReader(System.in));
    }

    public void setInput(InputStream input) {
        this.inputStream = new BufferedReader(new InputStreamReader(input));
    }

    @Override
    public Card move(List cardsInAction, Suit trump) {
        // TODO: terrible testing support due to need of DumbGame and Supervisor
        //       objects to present
        ArrayList validCards = getValidCardsToMove(cardsInAction);
        if (validCards.isEmpty())
            return null;
        Card c;
        do {
            System.out.println("Your hand is" + hand);
            System.out.println("Cards in action: " + cardsInAction);
            System.out.print("Input card to move: ");
            c = getInputCardMatched(validCards);
            System.out.println("Card selected to move: " + c);
        }
        while (cardsInAction.isEmpty() && c == null);
        if (c != null)
            hand.remove(c);
        return c;
    }

    @Override
    public Card beat(Card card, Suit trump) {
        System.out.println("Your hand is" + hand);
        System.out.print("Input card to beat " + card.getSymbol() + ": ");
        String input = readInput();
        Card c = getCardMatchedInput(input); // TODO do card verification
        hand.remove(c);
        return c;
    }

    protected ArrayList getValidCardsToMove(List<Card> cardsInAction) {
        if (cardsInAction.isEmpty())
            return hand.toArrayList();
        ArrayList<Card> validCards = new ArrayList<>();
        for (Card c1: hand.toArrayList())
            for (Card c2: cardsInAction)
                if (c1.getRank().equals(c2.getRank()))
                    validCards.add(c1);
        return validCards;
    }

    protected ArrayList getValidCardsToBeat(Card cardToBeat) {
        ArrayList<Card> validCards = new ArrayList();
        //Suit trump = getTrumpSuit();
        Suit trump = Suit.SPADES;
        for (Card c: hand.toArrayList()) {
            if (c.getSuit().equals(cardToBeat.getSuit()))
                if (c.getRank().compareTo(cardToBeat.getRank()) > 0)
                    validCards.add(c);
            if (!(cardToBeat.getSuit().equals(trump)) && c.getSuit().equals(trump))
                validCards.add(c);
        }
        return validCards;
    }

    private Card getInputCardMatched(ArrayList<Card> validCards) {
        String input = readInput();
        Card card = null;
        for (Card c: validCards)
            if (input.equals(c.getSymbol()))
                card = c;   // TODO: does it need break?
        return card;
    }

    private Card getCardMatchedInput(String input) {
        HashMap<String, Card> cards = hand.toHashMap();
        return cards.get(input);
    }

    private String readInput() {
        try {
            return inputStream.readLine();
        }
        catch (IOException e) {
        }
        return null;
    }

    @Override
    public Strategy getStrategy() {
        return null;
    }
}