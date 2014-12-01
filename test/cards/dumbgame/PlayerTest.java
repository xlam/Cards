/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cards.dumbgame;

import cards.dumbgame.Player;
import cards.dumbgame.SimpleStrategy;
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
        Player player = new Player("Ivanov", new SimpleStrategy());
        assertEquals(player.getName(), "Ivanov");
    }
}
