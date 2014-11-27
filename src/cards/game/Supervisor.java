package cards.game;

import cards.Card;
import cards.Suit;
import java.util.List;

/**
 *
 * @author Sergey
 */
public class Supervisor {
    
    private static Supervisor instance = null;
    private DumbGame game;
    private Supervisor() {};
    
    public static Supervisor getInstance() {
        if (null == instance)
            instance = new Supervisor();
        return instance;
    }
    
    public int getPlayersCount() {
        return game.getPlayersCount();
    }
    
    public void setGame(DumbGame game) {
        this.game = game;
    }
    
    public List<Card> getCardsInAction() {
        return game.getCardsInAction();
    }
    
    public Suit getTrumpSuit() {
        return game.getTrumpSuit();
    }

}
