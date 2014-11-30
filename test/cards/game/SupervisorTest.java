package cards.game;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sergey
 */
public class SupervisorTest {
    
    private static Supervisor supervisor = null;
    
    @BeforeClass
    public static void beforeClass() {
        supervisor = Supervisor.getInstance();
    }
    
    @Before
    public void setUp() {
        DumbGame game = new DumbGame();
        supervisor.setGame(game);
    }
    
    public SupervisorTest() {
    }

    @Test
    public void testSupervisorGetPlayersCount() {
        assertEquals(2, supervisor.getPlayersCount());
    }
    
    
}
