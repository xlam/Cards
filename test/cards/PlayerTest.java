/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cards;

import cards.strategy.SimpleStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
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
        Player player = new Player("Ivanov", new SimpleStrategy(), null);
        assertEquals(player.getName(), "Ivanov");
    }
}
