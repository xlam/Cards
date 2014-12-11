package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public class SimpleStrategyTest {

    private Suit trump;

    public SimpleStrategyTest() {
    }

    private SimpleStrategy strategy;

    @Before
    public void setUp() {
        // Important! It is necessary Ace to be the highest rank
        // in order to properly test DumbGame strategies!
        Rank.setAceHigh();
        strategy = new SimpleStrategy();
        trump = Suit.CLUBS;
    }

    @After
    public void tearDown() {
        strategy = null;
    }

    @Test
    public void testSimpleStrategyMoveLowestCard() {
        DumbHand hand = new DumbHand();
        List<Card> cardsInAction = new ArrayList();
        hand.add(new Card(Suit.CLUBS, Rank.ACE));
        hand.add(new Card(Suit.CLUBS, Rank.SIX));
        hand.add(new Card(Suit.DIAMONDS, Rank.SIX));
        assertEquals(hand.getCard(2), strategy.move(hand, cardsInAction, trump));
        hand.remove(2);
        assertEquals(hand.getCard(1), strategy.move(hand, cardsInAction, trump));
    }

    @Test
    public void testSimpleStrategyBeat() {
        DumbHand hand = new DumbHand();
        hand.add(new Card(Suit.CLUBS, Rank.ACE));
        hand.add(new Card(Suit.CLUBS, Rank.SIX));
        hand.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        Card card = new Card(Suit.DIAMONDS, Rank.SIX);
        assertEquals(hand.getCard(2), strategy.beat(card, hand, trump));
        card = new Card(Suit.CLUBS, Rank.SEVEN);
        assertEquals(hand.getCard(0), strategy.beat(card, hand, trump));
        hand.remove(2);
        card = new Card(Suit.DIAMONDS, Rank.EIGHT);
        assertEquals(hand.getCard(1), strategy.beat(card, hand, trump));
    }
}
