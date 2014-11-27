package cards;

import java.util.Arrays;
import java.util.List;

public class Rank implements Comparable {

    private final String name;
    private final String symbol;
    private static boolean aceHigh = true;

    public static final Rank TWO    = new Rank("Two",   "2");
    public static final Rank THREE  = new Rank("Three", "3");
    public static final Rank FOUR   = new Rank("Four",  "4");
    public static final Rank FIVE   = new Rank("Five",  "5");
    public static final Rank SIX    = new Rank("Six",   "6");
    public static final Rank SEVEN  = new Rank("Seven", "7");
    public static final Rank EIGHT  = new Rank("Eight", "8");
    public static final Rank NINE   = new Rank("Nine",  "9");
    public static final Rank TEN    = new Rank("Ten",   "10");
    public static final Rank JACK   = new Rank("Jack",  "J");
    public static final Rank QUEEN  = new Rank("Queen", "Q");
    public static final Rank KING   = new Rank("King",  "K");
    public static final Rank ACE    = new Rank("Ace",   "A");

    public static final List VALUES_ACE_HIGH = Arrays.asList(
            new Rank[] {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
                JACK, QUEEN, KING, ACE
            }
    );

    public static final List VALUES_KING_HIGH = Arrays.asList(
            new Rank[] {ACE, TWO, THREE, FOUR, FIVE, SIX, EIGHT, SEVEN, NINE, TEN,
                JACK, QUEEN, KING
            }
    );

    private Rank(String name, String symbol) {
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

    public static void setAceHigh() {
        aceHigh = true;
    }

    public static void setKingHigh() {
        aceHigh = false;
    }

    @Override
    public int compareTo(Object o) {
        Rank rank = (Rank) o;
        if (aceHigh) {
            return VALUES_ACE_HIGH.indexOf(this) - VALUES_ACE_HIGH.indexOf(rank);
        } else {
            return VALUES_KING_HIGH.indexOf(this) - VALUES_KING_HIGH.indexOf(rank);
        }
    }

}
