package cards.common;

import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author User
 */
public class CardsTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    @Test
    public void testCardsSuit() {
        Suit s = Suit.SPADES;
        Suit c = Suit.DIAMONDS;
        assertEquals("Spades", s.getName());
        assertEquals("Spades", s.toString());
        assertEquals("s", s.getSymbol());
        assertTrue(s.compareTo(c) > 0);
    }

    @Test
    public void testCardsRank() {
        Rank a = Rank.ACE;
        Rank k = Rank.KING;
        assertEquals("Ace", a.getName());
        assertEquals("Ace", a.toString());
        assertEquals("A", a.getSymbol());
        assertTrue(a.compareTo(k) > 0);
        Rank.setKingHigh();
        assertTrue(a.compareTo(k) < 0);
    }


    @Test
    public void testCardsCard() {
        Card card = new Card(Suit.CLUBS, Rank.FIVE);
        assertEquals("Five of Clubs", card.toString());
        assertEquals("5c", card.getSymbol());
    }

    @Test
    public void testCardsCompareCards() {
        Card card_5d = new Card(Suit.DIAMONDS, Rank.FIVE);
        Card card_5c = new Card(Suit.CLUBS, Rank.FIVE);
        Card card_6c = new Card(Suit.CLUBS, Rank.SIX);
        assertTrue(card_5d.compareTo(card_5c) > 0 &&
                   card_5d.compareTo(card_6c) < 0);
        Card.setSortSuitFirst();
        assertTrue(card_6c.compareTo(card_5c) > 0 &&
                   card_6c.compareTo(card_5d) < 0);
    }


    @Test
    public void testCardsDeck() {
        Deck deck = Deck.defaultDeck();
        assertEquals(52, deck.size());
        assertEquals(52, deck.getCardsRemaining());
        deck.deal();
        deck.deal();
        assertEquals(50, deck.getCardsRemaining());
        deck.restore();
        assertEquals(52, deck.getCardsRemaining());
    }

    //@Test
    public void testCardsDeckSortAndShuffle() {
        Deck deck = Deck.defaultDeck();
        System.out.println(deck);
        System.out.println(deck.shuffle());
        System.out.println(deck.sort());
    }

    @Test
    public void testCardsHandAddAndRemove() {
        Hand hand = new HandMock();
        Card card = new Card(Suit.CLUBS, Rank.TWO);
        assertEquals(0, hand.size());
        hand.add(card);
        assertEquals(1, hand.size());
        Card card2 = hand.remove(0);
        assertEquals(0, hand.size());
        assertTrue(card2.equals(card));
        hand.add(card);
        assertTrue(hand.remove(card));
        assertEquals(0, hand.size());
    }

    @Test
    public void testCardsHandGetAndContainsCard() {
        Hand hand = new HandMock();
        hand.add(new Card(Suit.CLUBS, Rank.TWO));
        assertTrue(hand.containsCard(hand.getCard(0)));
    }

    @Test
    public void testCardsAllSuit() {
        Hand hand = new Hand();
        assertFalse(hand.allSuit(Suit.CLUBS));
        hand.add(new Card(Suit.CLUBS, Rank.ACE));
        assertTrue(hand.allSuit(Suit.CLUBS));
    }

    @Test
    public void testCardsDumbHandGetAllBySuit() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.CLUBS, Rank.ACE));
        hand.add(new Card(Suit.CLUBS, Rank.TWO));
        hand.add(new Card(Suit.DIAMONDS, Rank.THREE));
        List cards = hand.getAllBySuit(Suit.CLUBS);
        assertEquals(2, cards.size());
        assertTrue(((Card) cards.get(0)).getSuit().equals(Suit.CLUBS));
        assertTrue(((Card) cards.get(1)).getSuit().equals(Suit.CLUBS));
        cards = hand.getAllBySuit(Suit.DIAMONDS);
        assertEquals(1, cards.size());
        assertTrue(((Card) cards.get(0)).getSuit().equals(Suit.DIAMONDS));
        cards = hand.getAllBySuit(Suit.HEARTS);
        assertTrue(cards.isEmpty());
    }

    @Test
    public void testCardsSuitIsAndSuitIsNot() {
        Card card = new Card(Suit.CLUBS, Rank.SIX);
        assertTrue(card.suitIs(Suit.CLUBS));
        assertTrue(card.suitIsNot(Suit.DIAMONDS));
    }

    @Test
    public void testCardsRankIsAndSuitIsNot() {
        Card card = new Card(Suit.CLUBS, Rank.SIX);
        assertTrue(card.rankIs(Rank.SIX));
        assertTrue(card.rankIsNot(Rank.SEVEN));
    }

}
