/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cards.strategy;

import cards.*;
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
public class SimpleStrategyTest_ {

    public SimpleStrategyTest_() {
    }

    private SimpleStrategy strategy;

    @Before
    public void setUp() {
        // Important! It is necessary Ace to be the highest rank
        // in order to properly test DumbGame strategies!
        Rank.setAceHigh();
        strategy = new SimpleStrategy_();
    }

    @After
    public void tearDown() {
        strategy = null;
    }

    /**
     * Test of move method, of class SimpleStrategy.
     */
    @Test
    public void testMoveLowestCard() {
        DumbHand hand = new DumbHand();
        hand.add(new Card(Suit.CLUBS, Rank.ACE));
        hand.add(new Card(Suit.CLUBS, Rank.TWO));
        hand.add(new Card(Suit.DIAMONDS, Rank.THREE));
        assertEquals(hand.getCard(2), strategy.move(hand));
        hand.remove(2);
        assertEquals(hand.getCard(1), strategy.move(hand));
    }
}