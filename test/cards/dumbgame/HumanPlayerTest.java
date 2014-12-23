package cards.dumbgame;

import cards.common.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sergey
 */
public class HumanPlayerTest {

    private HumanDumbPlayer human;
    private Suit trump;

    @Before
    public void setUp() {
        human = new HumanDumbPlayer("Human");
        human.addCard(new Card(Suit.CLUBS,    Rank.EIGHT));
        human.addCard(new Card(Suit.HEARTS,   Rank.TEN));
        human.addCard(new Card(Suit.DIAMONDS, Rank.KING));
        human.addCard(new Card(Suit.CLUBS,    Rank.ACE));
        human.addCard(new Card(Suit.SPADES,   Rank.EIGHT));
        human.addCard(new Card(Suit.SPADES,   Rank.QUEEN));
        trump = Suit.SPADES;
    }

    @After
    public void tearDown() {
        human = null;
    }

    public HumanPlayerTest() {
    }

    @Test
    public void emptyTest() {
        
    }

//    @Test
//    public void testUserInput() {
//        ArrayList<Card> cardsInAction = new ArrayList<>();
//        HumanDumbPlayer human = new HumanDumbPlayer("Human");
//        human.addCard(new Card(Suit.SPADES,   Rank.QUEEN));
//        human.addCard(new Card(Suit.HEARTS,   Rank.TEN));
//        human.addCard(new Card(Suit.CLUBS,    Rank.ACE));
//        human.addCard(new Card(Suit.DIAMONDS, Rank.KING));
//        human.addCard(new Card(Suit.SPADES,   Rank.EIGHT));
//        human.addCard(new Card(Suit.CLUBS,    Rank.EIGHT));
//        human.setInput(new java.io.ByteArrayInputStream("10h".getBytes()));
//        Card moveCard = human.move();
//        assertTrue(moveCard.isSameAs(new Card(Suit.HEARTS, Rank.TEN)));
//        // TODO add more tests with multiple cases and trump
//    }
}
