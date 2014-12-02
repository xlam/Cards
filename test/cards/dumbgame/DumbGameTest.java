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
    private final DumbPlayer player2 = new DumbPlayer("Player1");
    private final DumbPlayer player3 = new DumbPlayer("Player1");

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

}
