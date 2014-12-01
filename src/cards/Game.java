package cards;

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
