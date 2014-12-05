package cards.dumbgame;

import cards.common.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

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
    public Card move() {
        System.out.println("Your hand is" + hand);
        System.out.print("Input card to move: ");
        String input = readInput();
        Card c = getCardMatchedInput(input);
        hand.remove(c);
        return c;
    }

    @Override
    public Card beat(Card card) {
        System.out.println("Your hand is" + hand);
        System.out.print("Input card to beat " + card.getSymbol() + ": ");
        String input = readInput();
        Card c = getCardMatchedInput(input); // TODO do card verification
        hand.remove(c);
        return c;
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
