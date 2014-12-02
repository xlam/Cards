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
    private final Player player1 = new Player("Player1");
    private final Player player2 = new Player("Player1");
    private final Player player3 = new Player("Player1");

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
    }

}
