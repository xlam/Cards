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
    private final DumbPlayer player1 = new DumbPlayer("Player1");
    private final DumbPlayer player2 = new DumbPlayer("Player2");
    private final DumbPlayer player3 = new DumbPlayer("Player3");

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
        assertEquals(player1, game.findFirstMover(Suit.CLUBS));
        player1.addCard(new Card(Suit.CLUBS, Rank.SIX));
        player2.addCard(new Card(Suit.CLUBS, Rank.SEVEN));
        player3.addCard(new Card(Suit.HEARTS, Rank.ACE));
        assertEquals(player2, game.findFirstMover(Suit.CLUBS));
    }

    @Test
    public void testFindShaker() {
        assertEquals(player2, game.findShaker(player1));
        assertEquals(player3, game.findShaker(player2));
        assertEquals(player1, game.findShaker(player3));
    }

    @Test
    public void testHandsFilled() {
        game.deck.restore();
        //System.out.println(game.deck.toString());
        // 1 pass
        game.fillPlayersHands(player1);
        assertEquals(6, player1.numberOfCards());
        assertEquals(6, player2.numberOfCards());
        assertEquals(6, player3.numberOfCards());
        // 2 pass
        player2.clearHand();
        player3.clearHand();
        game.fillPlayersHands(player3); // dealing starts from player 3
        // TODO make deck.toArray return Card[]
        Object[] deck = game.deck.toArray();
        Card cardOf3 = (Card)deck[6*3]; // pos of first card to deal to player3
        Card cardOf2 = (Card)deck[6*4]; // pos of first card to deal to player2
        assertEquals(cardOf3, player3.getHand().getCard(0));
        assertEquals(cardOf2, player2.getHand().getCard(0));
        // 3 pass
        player2.clearHand();
        player3.clearHand();
        game.fillPlayersHands(player2); // dealing starts from player 2
        cardOf2 = (Card)deck[6*5]; // pos of first card to deal to player2
        assertEquals(cardOf2, player2.getHand().getCard(0));
    }

}
