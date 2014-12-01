package cards.common;

// TODO get rid of dumbgame!
import cards.dumbgame.Player;

/**
 *
 * @author Sergey
 */
public interface Game {
    public void addPlayer(Player player);
    public void play();
    public void reset();
}
