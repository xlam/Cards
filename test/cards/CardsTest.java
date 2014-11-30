package cards;

import java.util.ArrayList;
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
    public void testCardsDumbHandGetAllBySuit() {
        DumbHand hand = new DumbHand();
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
    }
    
}
