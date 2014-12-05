/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cards.dumbgame;

import cards.common.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergey
 */
public class HumanPlayerTest {

    public HumanPlayerTest() {
    }

    @Test
    public void testHumanPlayer() {
        HumanDumbPlayer human = new HumanDumbPlayer("Human");
        human.addCard(new Card(Suit.SPADES,   Rank.QUEEN));
        human.addCard(new Card(Suit.HEARTS,   Rank.TEN));
        human.addCard(new Card(Suit.CLUBS,    Rank.ACE));
        human.addCard(new Card(Suit.DIAMONDS, Rank.KING));
        human.addCard(new Card(Suit.SPADES,   Rank.EIGHT));
        human.addCard(new Card(Suit.CLUBS,    Rank.EIGHT));
        Card beatCard = human.beat(new Card(Suit.HEARTS, Rank.NINE));
        assertTrue(beatCard.isSameAs(new Card(Suit.HEARTS, Rank.TEN)));
        // TODO add more tests with multiple cases and trump
    }
}
