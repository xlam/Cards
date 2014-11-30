package cards.game;

import cards.Player;

/**
 *
 * @author Sergey
 */
public interface Game {
    public void addPlayer(Player player);
    public void play();
    public void reset();
}
