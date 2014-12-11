package cards.dumbgame;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    DumbHandTest.class,
    DumbPlayerTest.class,
    DumbGameTest.class,
    SimpleStrategyTest.class,
    MemoryStrategyTest.class,
    SupervisorTest.class,
    HumanPlayerTest.class,
    DumbServiceTest.class
})
public class AllTests {

}
