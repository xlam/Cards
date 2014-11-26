/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cards.game;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Admin
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
