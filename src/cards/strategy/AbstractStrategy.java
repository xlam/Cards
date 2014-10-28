package cards.strategy;

import cards.Player;

/**
 *
 * @author Sergey Sokolov <xlamserg@gmail.com>
 */
abstract public class AbstractStrategy implements Strategy {
    protected Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
