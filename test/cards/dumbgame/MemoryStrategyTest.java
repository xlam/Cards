package cards.dumbgame;

import cards.common.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
public class MemoryStrategyTest {

    @Test
    public void testMemoryStrategyPossibleDeckCards() {
        MemoryStrategy strategy = new MemoryStrategy();
        DumbHand hand = new DumbHand();
        hand.add(new Card(Suit.CLUBS, Rank.NINE));
        hand.add(new Card(Suit.CLUBS, Rank.TEN));
            // 1 pass
        strategy.knownEnemyCards.add(new Card(Suit.CLUBS, Rank.SIX));
        strategy.knownEnemyCards.add(new Card(Suit.CLUBS, Rank.SEVEN));
        strategy.knownOutCards.add(new Card(Suit.DIAMONDS, Rank.SIX));
        strategy.knownOutCards.add(new Card(Suit.DIAMONDS, Rank.SEVEN));
        strategy.calculatePossibleDeckCards(hand);
        //System.out.println(strategy.possibleDeckCards.toString());
        assertEquals(30, strategy.possibleDeckCards.size());
            // 2 pass
        strategy.knownEnemyCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
        strategy.knownOutCards.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
        hand.add(new Card(Suit.CLUBS, Rank.JACK));
        strategy.calculatePossibleDeckCards(hand);
        //System.out.println(strategy.possibleDeckCards.toString());
        assertEquals(27, strategy.possibleDeckCards.size());
    }
}
