package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergey
 */
public class DumbHandTest {

    public DumbHandTest() {
    }

    @Test
    public void testCardsDumbHandAddAll() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.CLUBS, Rank.ACE));
        cards.add(new Card(Suit.CLUBS, Rank.TWO));
        cards.add(new Card(Suit.DIAMONDS, Rank.THREE));
        DumbHand hand = new DumbHand();
        hand.addAll(cards);
        assertEquals(3, hand.size());
    }

    @Test
    public void testCardsDumbHandCompare() {
        Suit trump = Suit.HEARTS;
        DumbHand hand1 = new DumbHand();
        // pass 1
        hand1.add(new Card(Suit.HEARTS, Rank.SIX));
        hand1.add(new Card(Suit.CLUBS, Rank.ACE));
        hand1.add(new Card(Suit.DIAMONDS, Rank.JACK));
        DumbHand hand2 = new DumbHand();
        hand2.add(new Card(Suit.HEARTS, Rank.ACE));
        hand2.add(new Card(Suit.CLUBS, Rank.KING));
        hand2.add(new Card(Suit.DIAMONDS, Rank.TEN));
        assertTrue(hand1.compareTo(hand2, trump) < 0);
        assertTrue(hand2.compareTo(hand1, trump) > 0);
        // pass 2
        hand1.clear();
        hand1.add(new Card(Suit.SPADES, Rank.SIX));
        hand2.clear();
        hand2.add(new Card(Suit.CLUBS, Rank.SIX));
        assertEquals(0, hand1.compareTo(hand2, trump));
        assertEquals(0, hand2.compareTo(hand1, trump));
        // pass 3
        hand1.clear();
        hand1.add(new Card(Suit.SPADES, Rank.SEVEN));
        hand2.clear();
        hand2.add(new Card(Suit.CLUBS, Rank.SIX));
        assertTrue(hand1.compareTo(hand2, trump) > 0);
        assertTrue(hand2.compareTo(hand1, trump) < 0);
        // pass 4
        hand1.clear();
        hand1.add(new Card(Suit.HEARTS, Rank.SIX));
        hand2.clear();
        hand2.add(new Card(Suit.CLUBS, Rank.SIX));
        assertTrue(hand1.compareTo(hand2, trump) > 0);
        assertTrue(hand2.compareTo(hand1, trump) < 0);
        // pass 5
        hand1.clear();
        hand2.clear();
        hand1.add(new Card(Suit.HEARTS, Rank.SIX));
        hand1.add(new Card(Suit.HEARTS, Rank.EIGHT));
        hand2.add(new Card(Suit.HEARTS, Rank.NINE));
        hand2.add(new Card(Suit.HEARTS, Rank.TEN));
        assertTrue(hand1.compareTo(hand2, trump) < 0);
        assertTrue(hand2.compareTo(hand1, trump) > 0);
    }

    @Test
    public void testGetHighest() {
        Card result, expected;
        Suit trump = Suit.SPADES;
        DumbHand hand = new DumbHand();
        hand.add(new Card(Suit.SPADES,   Rank.ACE));
        hand.add(new Card(Suit.DIAMONDS, Rank.SIX));
        hand.add(new Card(Suit.HEARTS,   Rank.JACK));
        hand.add(new Card(Suit.SPADES,   Rank.TEN));
        hand.add(new Card(Suit.SPADES,   Rank.SEVEN));
        hand.add(new Card(Suit.SPADES,   Rank.JACK));
        expected = new Card(Suit.SPADES, Rank.ACE);
        result = hand.getHighest(trump);
        System.out.println("Pass1 hand:" + hand);
        System.out.println("Expected: " + expected + " result: " + result);
        assertEquals(0, result.compareTo(expected));
        hand.clear();
        hand.add(new Card(Suit.SPADES,   Rank.QUEEN));
        hand.add(new Card(Suit.HEARTS,   Rank.TEN));
        hand.add(new Card(Suit.CLUBS,    Rank.ACE));
        hand.add(new Card(Suit.DIAMONDS, Rank.KING));
        hand.add(new Card(Suit.SPADES,   Rank.EIGHT));
        hand.add(new Card(Suit.CLUBS,    Rank.EIGHT));
        expected = new Card(Suit.SPADES, Rank.QUEEN);
        result = hand.getHighest(trump);
        System.out.println("Pass2 hand:" + hand);
        System.out.println("Expected: " + expected + " result: " + result);
        assertEquals(0, result.compareTo(expected));
        hand.clear();
        hand.add(new Card(Suit.HEARTS,   Rank.NINE));
        hand.add(new Card(Suit.HEARTS,   Rank.ACE));
        hand.add(new Card(Suit.CLUBS,    Rank.NINE));
        hand.add(new Card(Suit.CLUBS,    Rank.SIX));
        hand.add(new Card(Suit.CLUBS,    Rank.TEN));
        hand.add(new Card(Suit.DIAMONDS, Rank.JACK));
        expected = new Card(Suit.HEARTS, Rank.ACE);
        result = hand.getHighest(trump);
        System.out.println("Pass1 hand:" + hand);
        System.out.println("Expected: " + expected + " result: " + result);
        assertEquals(0, result.compareTo(expected));
    }

}
