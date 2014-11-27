package cards;

import cards.game.*;

public class Cards {

    public static void main(String[] args) {
        DumbGame game = new DumbGame();
        Supervisor.getInstance().setGame(game);
        game.play();
    }
}
