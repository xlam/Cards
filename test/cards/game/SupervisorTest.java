package cards.game;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

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
    
    public SupervisorTest() {
    }

    @Test
    public void testSupervisorGetPlayersCount() {
        assertEquals(2, supervisor.getPlayersCount());
    }
    
    
}
