/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cards.dumbgame;

import cards.common.*;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sergey
 */
public class HumanPlayerTest {

    public HumanPlayerTest() {
    }

    @Test
    public void testHumanPlayer() {
        Suit trump = Suit.SPADES;
        ArrayList<Card> expected;
        ArrayList<Card> result;
        ArrayList<Card> cardsInAction = new ArrayList<>();
        HumanDumbPlayer human = new HumanDumbPlayer("Human");
        human.addCard(new Card(Suit.SPADES,   Rank.QUEEN));
        human.addCard(new Card(Suit.HEARTS,   Rank.TEN));
        human.addCard(new Card(Suit.CLUBS,    Rank.ACE));
        human.addCard(new Card(Suit.DIAMONDS, Rank.KING));
        human.addCard(new Card(Suit.SPADES,   Rank.EIGHT));
        human.addCard(new Card(Suit.CLUBS,    Rank.EIGHT));
        Hand hand = human.getHand();
        // PASS 1: first move, no cards in action
        expected = hand.toArrayList();
        result = human.getValidCardsToMove(cardsInAction, trump);
        assertEquals(expected, result);
        // PASS 2: two cards are matching cards in action ranks
        cardsInAction.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        cardsInAction.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
        expected.clear();
        expected.add(hand.getCard(4));
        expected.add(hand.getCard(5));
        result = human.getValidCardsToMove(cardsInAction, trump);
        assertEquals(expected, result);
        // PASS 3: no cards are matching cards in action ranks
        expected.clear();
        cardsInAction.clear();
        cardsInAction.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        cardsInAction.add(new Card(Suit.DIAMONDS, Rank.JACK));
        result = human.getValidCardsToMove(cardsInAction, trump);
        assertEquals(expected, result);

//        human.setInput(new java.io.ByteArrayInputStream("10h".getBytes()));
//        Card beatCard = human.beat(new Card(Suit.HEARTS, Rank.NINE));
//        assertTrue(beatCard.isSameAs(new Card(Suit.HEARTS, Rank.TEN)));
        // TODO add more tests with multiple cases and trump
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
