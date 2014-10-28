package cards;

import cards.strategy.MemoryStrategyTest;
import cards.strategy.SimpleStrategyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    CardsTest.class,
    PlayerTest.class,
    SimpleStrategyTest.class,
    MemoryStrategyTest.class
})
public class AllTests {

}
