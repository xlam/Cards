package cards;

import cards.dumbgame.*;

public class CardsWithHumanPlayer {

    public static void main(String[] args) {
        System.out.println("Cards v" + Version.getVersion());
        DumbGame game = new DumbGame();
        game.addPlayer(new AIDumbPlayer("Vasya", new SimpleStrategy()));
        game.addPlayer(new HumanDumbPlayer("Petya"));
        Supervisor.getInstance().setGame(game);
        game.play();
    }
}
