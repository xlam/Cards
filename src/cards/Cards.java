package cards;

import cards.dumbgame.*;

public class Cards {

    public static void main(String[] args) {
        System.out.println("Cards v" + Version.getVersion());
        DumbGame game = new DumbGame();
        game.addPlayer(new AIDumbPlayer("Vasya", new SimpleStrategy()));
        game.addPlayer(new AIDumbPlayer("Petya", new AdvancedStrategy()));
        Supervisor.getInstance().setGame(game);
        game.play();
    }
}
