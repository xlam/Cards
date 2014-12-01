package cards.main;

import cards.dumbgame.SimpleStrategy;
import cards.dumbgame.AdvancedStrategy;
import cards.Supervisor;
import cards.dumbgame.DumbGame;
import cards.dumbgame.Player;
import cards.Version;

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
