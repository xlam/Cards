package cards.dumbgame;

import cards.common.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergey
 */
public class DumbGameTest {

    private DumbGame game;
    private final AIDumbPlayer player1 = new AIDumbPlayer("Player1");
    private final AIDumbPlayer player2 = new AIDumbPlayer("Player2");
    private final AIDumbPlayer player3 = new AIDumbPlayer("Player3");

    public DumbGameTest() {
    }

    @Before
    public void setUp() {
        game = new DumbGame();
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
    }

    @After
    public void tearDown() {
        game = null;
    }

    @Test
    public void testPlayersAdded() {
        assertEquals(3, game.getPlayersCount());
    }

    @Test
    public void testPlayersWithCardsAreCounted() {
        assertEquals(0, game.countPlayersWithCards());
        player2.addCard(new Card(Suit.CLUBS, Rank.ACE));
        assertEquals(1, game.countPlayersWithCards());
        player3.addCard(new Card(Suit.CLUBS, Rank.SIX));
        assertEquals(2, game.countPlayersWithCards());
    }

    @Test
    public void testFindWhoMovesFirst() {
        // pass 1: empty hand
        assertEquals(player1, game.findFirstMover(Suit.CLUBS));
        player1.addCard(new Card(Suit.CLUBS, Rank.SIX));
        player2.addCard(new Card(Suit.CLUBS, Rank.SEVEN));
        player3.addCard(new Card(Suit.HEARTS, Rank.ACE));
        assertEquals(player2, game.findFirstMover(Suit.CLUBS));
        // pass 2: full hands from real game
        player1.getHand().clear();
        player2.getHand().clear();
        player3.getHand().clear();
        player1.addCard(new Card(Suit.SPADES,   Rank.ACE));
        player1.addCard(new Card(Suit.DIAMONDS, Rank.SIX));
        player1.addCard(new Card(Suit.HEARTS,   Rank.JACK));
        player1.addCard(new Card(Suit.SPADES,   Rank.TEN));
        player1.addCard(new Card(Suit.SPADES,   Rank.SEVEN));
        player1.addCard(new Card(Suit.SPADES,   Rank.JACK));
        player2.addCard(new Card(Suit.SPADES,   Rank.QUEEN));
        player2.addCard(new Card(Suit.HEARTS,   Rank.TEN));
        player2.addCard(new Card(Suit.CLUBS,    Rank.ACE));
        player2.addCard(new Card(Suit.DIAMONDS, Rank.KING));
        player2.addCard(new Card(Suit.SPADES,   Rank.EIGHT));
        player2.addCard(new Card(Suit.CLUBS,    Rank.EIGHT));
        player3.addCard(new Card(Suit.HEARTS,   Rank.NINE));
        player3.addCard(new Card(Suit.HEARTS,   Rank.ACE));
        player3.addCard(new Card(Suit.CLUBS,    Rank.NINE));
        player3.addCard(new Card(Suit.CLUBS,    Rank.SIX));
        player3.addCard(new Card(Suit.CLUBS,    Rank.TEN));
        player3.addCard(new Card(Suit.DIAMONDS, Rank.JACK));
        assertEquals(player1, game.findFirstMover(Suit.SPADES));
    }

    @Test
    public void testFindShaker() {
        assertEquals(player2, game.findShaker(player1));
        assertEquals(player3, game.findShaker(player2));
        assertEquals(player1, game.findShaker(player3));
    }

    @Test
    public void testNextMover() {
        game.mover = player1;
        assertEquals(player2, game.nextMover(false));
        assertEquals(player3, game.nextMover(true));
        game.mover = player2;
        assertEquals(player3, game.nextMover(false));
        assertEquals(player1, game.nextMover(true));
        game.mover = player3;
        assertEquals(player1, game.nextMover(false));
        assertEquals(player2, game.nextMover(true));
    }

    @Test
    public void testHandsFilled() {
        game.deck.restore();
        //System.out.println(game.deck.toString());
        // 1 pass
        game.dealCardsStartingFrom(player1);
        assertEquals(6, player1.numberOfCards());
        assertEquals(6, player2.numberOfCards());
        assertEquals(6, player3.numberOfCards());
        // 2 pass
        player2.clearHand();
        player3.clearHand();
        game.dealCardsStartingFrom(player3); // dealing starts from player 3
        Card[] deck = game.deck.toArray();
        Card cardOf3 = deck[6*3]; // pos of first card to deal to player3
        Card cardOf2 = deck[6*4]; // pos of first card to deal to player2
        assertEquals(cardOf3, player3.getHand().getCard(0));
        assertEquals(cardOf2, player2.getHand().getCard(0));
        // 3 pass
        player2.clearHand();
        player3.clearHand();
        game.dealCardsStartingFrom(player2); // dealing starts from player 2
        cardOf2 = deck[6*5]; // pos of first card to deal to player2
        assertEquals(cardOf2, player2.getHand().getCard(0));
    }

}
