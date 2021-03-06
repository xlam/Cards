package cards.dumbgame;

import cards.common.Card;
import cards.common.Rank;
import cards.common.Suit;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sergey
 */
public class AdvancedStrategyTest {

    private Suit trump;
    private AdvancedStrategy strategy;

    public AdvancedStrategyTest() {
    }

    @Before
    public void setUp() {
        strategy = new AdvancedStrategy();
        trump = Suit.SPADES;
    }

    @After
    public void tearDown() {
        strategy = null;
        trump = null;
    }

    @Test
    public void testGetPairs() {
        List<Card> cards = new ArrayList();
        Card card1 = new Card(Suit.CLUBS,     Rank.SIX);
        Card card2 = new Card(Suit.CLUBS,     Rank.SEVEN);
        cards.add(card1);
        cards.add(new Card(Suit.DIAMONDS,  Rank.SIX));
        cards.add(card2);
        cards.add(new Card(Suit.CLUBS,     Rank.EIGHT));
        cards.add(new Card(Suit.SPADES,    Rank.SIX));
        cards.add(new Card(Suit.SPADES,    Rank.SEVEN));
        List pairs = strategy.getPairs(cards);
        System.out.println(pairs);
        assertEquals(2, pairs.size());
        assertTrue(pairs.contains(card1));
        assertTrue(pairs.contains(card2));
    }

}
