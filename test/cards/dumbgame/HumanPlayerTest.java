package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sergey
 */
public class HumanPlayerTest {

    private HumanDumbPlayer human;
    private Suit trump;

    @Before
    public void setUp() {
        human = new HumanDumbPlayer("Human");
        human.addCard(new Card(Suit.CLUBS,    Rank.EIGHT));
        human.addCard(new Card(Suit.HEARTS,   Rank.TEN));
        human.addCard(new Card(Suit.DIAMONDS, Rank.KING));
        human.addCard(new Card(Suit.CLUBS,    Rank.ACE));
        human.addCard(new Card(Suit.SPADES,   Rank.EIGHT));
        human.addCard(new Card(Suit.SPADES,   Rank.QUEEN));
        trump = Suit.SPADES;
    }

    @After
    public void tearDown() {
        human = null;
    }

    public HumanPlayerTest() {
    }

    @Test
    public void testHumanPlayerMove() {
        ArrayList<Card> expected;
        ArrayList<Card> cardsInAction = new ArrayList<>();
        Hand hand = human.getHand();
        // PASS 1: first move, no cards in action, so all the cards in hand are match
        expected = hand.toArrayList();
        assertEquals(expected, human.getValidCardsToMove(cardsInAction));
        // PASS 2: two cards are matching cards in action ranks
        cardsInAction.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        cardsInAction.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
        expected.clear();
        expected.add(hand.getCard(0));
        expected.add(hand.getCard(4));
        assertEquals(expected, human.getValidCardsToMove(cardsInAction));
        // PASS 3: no cards are matching cards in action ranks
        expected.clear();
        cardsInAction.clear();
        cardsInAction.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        cardsInAction.add(new Card(Suit.DIAMONDS, Rank.JACK));
        assertEquals(expected, human.getValidCardsToMove(cardsInAction));
    }

    @Test
    public void testHumanPlayerBeat() {
        ArrayList<Card> expected = new ArrayList();
        Card cardToBeat;
        Hand hand = human.getHand();
        // PASS 1
        cardToBeat = new Card(Suit.HEARTS, Rank.NINE);
        expected.add(hand.getCard(1));
        expected.add(hand.getCard(4));
        expected.add(hand.getCard(5));
        assertEquals(expected, human.getValidCardsToBeat(cardToBeat));
        // PASS 2
        cardToBeat = new Card(Suit.CLUBS, Rank.SIX);
        expected.clear();
        expected.add(hand.getCard(0));
        expected.add(hand.getCard(3));
        expected.add(hand.getCard(4));
        expected.add(hand.getCard(5));
        assertEquals(expected, human.getValidCardsToBeat(cardToBeat));
        // PASS 3
        cardToBeat = new Card(Suit.SPADES, Rank.KING);
        expected.clear();
        assertEquals(expected, human.getValidCardsToBeat(cardToBeat));
    }

//    @Test
//    public void testUserInput() {
//        ArrayList<Card> cardsInAction = new ArrayList<>();
//        HumanDumbPlayer human = new HumanDumbPlayer("Human");
//        human.addCard(new Card(Suit.SPADES,   Rank.QUEEN));
//        human.addCard(new Card(Suit.HEARTS,   Rank.TEN));
//        human.addCard(new Card(Suit.CLUBS,    Rank.ACE));
//        human.addCard(new Card(Suit.DIAMONDS, Rank.KING));
//        human.addCard(new Card(Suit.SPADES,   Rank.EIGHT));
//        human.addCard(new Card(Suit.CLUBS,    Rank.EIGHT));
//        human.setInput(new java.io.ByteArrayInputStream("10h".getBytes()));
//        Card moveCard = human.move();
//        assertTrue(moveCard.isSameAs(new Card(Suit.HEARTS, Rank.TEN)));
//        // TODO add more tests with multiple cases and trump
//    }
}
