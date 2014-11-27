package cards;

import java.util.Arrays;
import java.util.List;

public class Suit implements Comparable {

    private final String name;
    private final String symbol;

    public static final Suit CLUBS = new Suit("Clubs", "c");        // "♣"
    public static final Suit DIAMONDS = new Suit("Diamonds", "d");  // "♦"
    public static final Suit HEARTS = new Suit("Hearts", "h");      // "♥"
    public static final Suit SPADES = new Suit("Spades", "s");      // "♠"

    // alphabetical order
    public static final List VALUES = Arrays.asList(
            new Suit[] {CLUBS, DIAMONDS, HEARTS, SPADES}
    );

    private Suit(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        Suit suit = (Suit) o;
        return VALUES.indexOf(this) - VALUES.indexOf(suit);
    }


}
