package cards.dumbgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergey
 */
public class DumbPlayerTest {

    public DumbPlayerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDumbPlayer() {
        AIDumbPlayer player = new AIDumbPlayer("Ivanov", new SimpleStrategy());
        assertEquals(player.getName(), "Ivanov");
        assertTrue((player.getHand()) instanceof DumbHand);
    }

    @Test
    public void testDumbPlayerToString() {
        AIDumbPlayer player1 = new AIDumbPlayer("Player1", new SimpleStrategy());
        AIDumbPlayer player2 = new AIDumbPlayer("Player2", new SimpleStrategy());
        assertEquals("Player1", player1.toString());
        assertEquals("Player2", player2.toString());
//        System.out.println("Player1 name is " + player1);
//        System.out.println("Player2 name is " + player2);
    }

}
