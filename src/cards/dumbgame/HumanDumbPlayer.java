package cards.dumbgame;

import cards.Logger;
import cards.common.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        // TODO: needs refactoring?
        DumbService s = getService();
        List validCards = s.getValidCardsToMove(hand, cardsInAction);
        if (validCards.isEmpty())
            return null;
        Card c;
        do {
            Logger.log("Your hand is" + hand);
            Logger.log("Cards in action: " + cardsInAction);
            System.out.print("Input card to move: ");
            c = getInputCardMatched(validCards);
            Logger.log("Card selected to move: " + c);
        }
        while (cardsInAction.isEmpty() && c == null);
        if (c != null)
            hand.remove(c);
        return c;
    }

    @Override
    public Card beat(Card card, Suit trump) {
        Logger.log("Your hand is" + hand);
        System.out.print("Input card to beat " + card.getSymbol() + ": ");
        String input = readInput();
        Card c = getCardMatchedInput(input); // TODO do card verification
        hand.remove(c);
        return c;
    }

    private Card getInputCardMatched(List<Card> validCards) {
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
