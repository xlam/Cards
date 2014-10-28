package cards.strategy;

import cards.Card;
import cards.Player;
import cards.Rank;
import cards.Suit;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public class MemoryStrategyTest {

    @Test
    public void testPossibleDeckCards() {
        MemoryStrategy strategy = new MemoryStrategy();
        Player player = new Player("TestPlayer", strategy, null);
        player.addCard(new Card(Suit.CLUBS, Rank.NINE));
        player.addCard(new Card(Suit.CLUBS, Rank.TEN));
        strategy.setPlayer(player);
            // 1 pass
        strategy.knownEnemyCards.add(new Card(Suit.CLUBS, Rank.SIX));
        strategy.knownEnemyCards.add(new Card(Suit.CLUBS, Rank.SEVEN));
        strategy.knownOutCards.add(new Card(Suit.DIAMONDS, Rank.SIX));
        strategy.knownOutCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        strategy.calculatePossibleDeckCards();
        System.out.println(strategy.possibleDeckCards.toString());
        assertEquals(30, strategy.possibleDeckCards.size());
            // 2 pass
        strategy.knownEnemyCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
        strategy.knownOutCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
        player.addCard(new Card(Suit.CLUBS, Rank.JACK));
        strategy.calculatePossibleDeckCards();
        System.out.println(strategy.possibleDeckCards.toString());
        assertEquals(27, strategy.possibleDeckCards.size());
    }
}
