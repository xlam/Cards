package cards.common;

public class Card implements Comparable<Card> {

    private final Suit suit;
    private final Rank rank;
    private static boolean sortRankFirst = true;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public String getSymbol() {
        return rank.getSymbol() + suit.getSymbol();
    }

    public static void setSortRankFirst() {
        sortRankFirst = true;
    }

    public static void setSortSuitFirst() {
        sortRankFirst = false;
    }

    @Override
    public int compareTo(Card card) {
        int rankDiff = rank.compareTo(card.getRank());
        int suitDiff = suit.compareTo(card.getSuit());

        if (sortRankFirst) {
            if (rankDiff != 0) {
                return rankDiff;
            } else {
                return suitDiff;
            }
        } else {
            if (suitDiff != 0) {
                return suitDiff;
            } else {
                return rankDiff;
            }
        }
    }

    @Override
    public String toString() {
        return rank.getName() + " of " + suit.getName();
    }

    public String toStringUTF() {
        return "test_" + '\u2660';
    }

    public boolean isSameAs(Card card) {
        return suit.equals(card.getSuit()) && rank.equals(card.getRank());
    }

    public boolean suitIs(Suit suit) {
        return this.suit.equals(suit);
    }

    public boolean suitIsNot(Suit suit) {
        return !(this.suit.equals(suit));
    }

    public boolean rankIs(Rank rank) {
        return this.rank.equals(rank);
    }

    public boolean rankIsNot(Rank rank) {
        return !(this.rank.equals(rank));
    }

}
