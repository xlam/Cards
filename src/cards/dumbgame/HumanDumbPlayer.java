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
        // TODO refactor duplication with beat()
        HashMap<String, Card> cards = getCardsHashMap();
        System.out.println("Your hand is" + hand);
        System.out.print("Input card to move: ");
        String input = "";
        try {
            input = inputStream.readLine();
        }
        catch (IOException e) {
        }
        Card c = cards.get(input);
        hand.remove(c);
        return c;
    }

    @Override
    public Card beat(Card card) {
        HashMap<String, Card> cards = getCardsHashMap();
        System.out.println("Your hand is" + hand);
        System.out.print("Input card to beat " + card.getSymbol() + ": ");
        String input = "";
        try {
            input = inputStream.readLine();
        }
        catch (IOException e) {
        }
        // TODO do card verification
        Card beatCard = cards.get(input);
        hand.remove(beatCard);
        return beatCard;
    }

    // TODO move this to Hand
    private HashMap<String, Card> getCardsHashMap() {
        HashMap<String, Card> cards = new HashMap<>();
        for (Card c: hand.toList())
            cards.put(c.getSymbol(), c);
        return cards;
    }
}
