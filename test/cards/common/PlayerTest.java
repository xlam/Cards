package cards.common;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergey
 */
public class PlayerTest {

    public PlayerTest() {
    }

    @Test
    public void testPlayerToString() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        assertEquals("Player1", player1.toString());
        assertEquals("Player2", player2.toString());
        //System.out.println("Player1 name is " + player1);
        //System.out.println("Player2 name is " + player2);
    }

}
