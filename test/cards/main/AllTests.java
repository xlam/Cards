package cards.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    cards.AllTests.class,
    cards.dumbgame.AllTests.class,
})
public class AllTests {

}
