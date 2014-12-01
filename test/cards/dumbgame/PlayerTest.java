package cards.dumbgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergey
 */
public class PlayerTest {

    public PlayerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPlayer() {
        DumbPlayer player = new DumbPlayer("Ivanov", new SimpleStrategy());
        assertEquals(player.getName(), "Ivanov");
        assertTrue((player.getHand()) instanceof DumbHand);
    }
}
