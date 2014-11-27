package cards;

import cards.strategy.*;
import cards.game.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    CardsTest.class,
    PlayerTest.class,
    SimpleStrategyTest.class,
    MemoryStrategyTest.class,
    SupervisorTest.class
})
public class AllTests {

}
