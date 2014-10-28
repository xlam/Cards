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
public class SimpleStrategyTest {

    public SimpleStrategyTest() {
    }

    private SimpleStrategy strategy;

    @Before
    public void setUp() {
        strategy = new SimpleStrategy();
        strategy.setPlayer(new PlayerMock());
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
        strategy.getPlayer().setHand(hand);
        assertEquals(hand.getCard(2), strategy.move());
        hand.remove(2);
        assertEquals(hand.getCard(1), strategy.move());
    }

    @Test
    public void testBeat() {
        DumbHand hand = new DumbHand();
        hand.add(new Card(Suit.CLUBS, Rank.ACE));
        hand.add(new Card(Suit.CLUBS, Rank.TWO));
        hand.add(new Card(Suit.DIAMONDS, Rank.THREE));
        strategy.getPlayer().setHand(hand);
        Card card = new Card(Suit.DIAMONDS, Rank.TWO);
        assertEquals(hand.getCard(2), strategy.beat(card));
        card = new Card(Suit.CLUBS, Rank.THREE);
        assertEquals(hand.getCard(0), strategy.beat(card));
        hand.remove(2);
        card = new Card(Suit.DIAMONDS, Rank.TWO);
        assertEquals(hand.getCard(1), strategy.beat(card));
    }
}

class PlayerMock extends Player {

    public PlayerMock() {
        super("Vasya", new SimpleStrategy(), null);
    }
    
    @Override
    public List<Card> getCardsInAction() {
        return new ArrayList<Card>();
    }

    @Override
    public Suit getTrumpSuit() {
        return Suit.CLUBS;
    }
}