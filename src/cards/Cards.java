package cards;

import cards.game.*;
import cards.strategy.*;

public class Cards {

    public static void main(String[] args) {
        System.out.println("Cards v" + Version.getVersion());
        DumbGame game = new DumbGame();
        game.addPlayer(new Player("Vasya", new SimpleStrategy()));
        game.addPlayer(new Player("Petya", new AdvancedStrategy()));
        Supervisor.getInstance().setGame(game);
        game.play();
    }
}
