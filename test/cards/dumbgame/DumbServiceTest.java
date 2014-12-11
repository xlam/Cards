package cards.dumbgame;

import cards.common.Card;
import cards.common.Hand;
import cards.common.Rank;
import cards.common.Suit;
import java.util.ArrayList;
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
    private DumbHand hand;
    private Suit trump;

    public DumbServiceTest() {
    }

    @Before
    public void setUp() {
        service = new DumbService();
        hand = new DumbHand();
        hand.add(new Card(Suit.CLUBS,    Rank.EIGHT));
        hand.add(new Card(Suit.HEARTS,   Rank.TEN));
        hand.add(new Card(Suit.DIAMONDS, Rank.KING));
        hand.add(new Card(Suit.CLUBS,    Rank.ACE));
        hand.add(new Card(Suit.SPADES,   Rank.EIGHT));
        hand.add(new Card(Suit.SPADES,   Rank.QUEEN));
        trump = Suit.SPADES;
    }

    @After
    public void tearDown() {
        service = null;
    }

    @Test
    public void testDumbServiceCompareForBeat() {
        Card card1 = new Card(Suit.CLUBS, Rank.SIX);
        Card card2 = new Card(Suit.CLUBS, Rank.SEVEN);
        Card card3 = new Card(Suit.CLUBS, Rank.TEN);
        Card card4 = new Card(Suit.HEARTS, Rank.SIX);
        Card card5 = new Card(Suit.DIAMONDS, Rank.SEVEN);
        Card card6 = new Card(Suit.SPADES, Rank.SIX);
        Card card7 = new Card(Suit.SPADES, Rank.TEN);
        assertTrue(service.compareForBeat(card1, card2, trump) < 0);
        assertTrue(service.compareForBeat(card2, card1, trump) > 0);
        assertTrue(service.compareForBeat(card5, card1, trump) < 0);
        assertTrue(service.compareForBeat(card2, card6, trump) < 0);
        assertTrue(service.compareForBeat(card6, card3, trump) > 0);
    }

    @Test
    public void testDumbServiceGetValidCardsToMove() {
        ArrayList<Card> expected;
        ArrayList<Card> cardsInAction = new ArrayList<>();
        // PASS 1: first move, no cards in action, so all the cards in hand are match
        expected = hand.toArrayList();
        assertEquals(expected, service.getValidCardsToMove(hand, cardsInAction));
        // PASS 2: two cards are matching cards in action ranks
        cardsInAction.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        cardsInAction.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
        expected.clear();
        expected.add(hand.getCard(0));
        expected.add(hand.getCard(4));
        assertEquals(expected, service.getValidCardsToMove(hand, cardsInAction));
        // PASS 3: no cards are matching cards in action ranks
        expected.clear();
        cardsInAction.clear();
        cardsInAction.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        cardsInAction.add(new Card(Suit.DIAMONDS, Rank.JACK));
        assertEquals(expected, service.getValidCardsToMove(hand, cardsInAction));
    }

    @Test
    public void testDumbServiceGetValidCardsToBeat() {
        ArrayList<Card> expected = new ArrayList();
        Card cardToBeat;
        // PASS 1
        cardToBeat = new Card(Suit.HEARTS, Rank.NINE);
        expected.add(hand.getCard(1));
        expected.add(hand.getCard(4));
        expected.add(hand.getCard(5));
        assertEquals(expected, service.getValidCardsToBeat(cardToBeat, hand, trump));
        // PASS 2
        cardToBeat = new Card(Suit.CLUBS, Rank.SIX);
        expected.clear();
        expected.add(hand.getCard(0));
        expected.add(hand.getCard(3));
        expected.add(hand.getCard(4));
        expected.add(hand.getCard(5));
        assertEquals(expected, service.getValidCardsToBeat(cardToBeat, hand, trump));
        // PASS 3
        cardToBeat = new Card(Suit.SPADES, Rank.KING);
        expected.clear();
        assertEquals(expected, service.getValidCardsToBeat(cardToBeat, hand, trump));
    }

}
