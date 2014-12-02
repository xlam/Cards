package cards.dumbgame;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    DumbHandTest.class,
    PlayerTest.class,
    SimpleStrategyTest.class,
    MemoryStrategyTest.class,
    SupervisorTest.class
})
public class AllTests {

}
