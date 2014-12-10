package cards.dumbgame;

import cards.common.Card;
import cards.common.Rank;
import cards.common.Suit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kus
 */
public class DumbServiceTest {

    private DumbService service;
    private Suit trump;

    public DumbServiceTest() {
    }

    @Before
    public void setUp() {
        service = new DumbService();
        trump = Suit.SPADES;
    }

    @After
    public void tearDown() {
        service = null;
    }

    @Test
    public void testAssertCardsValues() {
        Card card1 = new Card(Suit.CLUBS, Rank.SIX);
        Card card2 = new Card(Suit.CLUBS, Rank.SEVEN);
        Card card3 = new Card(Suit.CLUBS, Rank.TEN);
        Card card4 = new Card(Suit.HEARTS, Rank.SIX);
        Card card5 = new Card(Suit.DIAMONDS, Rank.SEVEN);
        Card card6 = new Card(Suit.SPADES, Rank.SIX);
        Card card7 = new Card(Suit.SPADES, Rank.TEN);
        assertTrue(service.getValueOf(card1, trump) < service.getValueOf(card2, trump));
        assertTrue(service.getValueOf(card3, trump) > service.getValueOf(card1, trump));
        assertTrue(service.getValueOf(card1, trump) == service.getValueOf(card4, trump));
        assertTrue(service.getValueOf(card2, trump) == service.getValueOf(card5, trump));
        assertTrue(service.getValueOf(card2, trump) < service.getValueOf(card6, trump));
        assertTrue(service.getValueOf(card6, trump) < service.getValueOf(card7, trump));
    }

}
