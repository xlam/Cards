package cards;

import cards.dumbgame.DumbGame;
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
        checkGameNotNull();
        return game.getCardsInAction();
    }
    
    public Suit getTrumpSuit() {
        checkGameNotNull();
        return game.getTrumpSuit();
    }

    private void checkGameNotNull() {
        try {
            if (null == game)
                throw new Exception("Game object is not set in Supervisor");
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
    
}
