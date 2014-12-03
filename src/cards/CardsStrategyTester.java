package cards;

import cards.dumbgame.*;

public class CardsStrategyTester {

    public static void main(String[] args) {
        System.out.println("Cards v" + Version.getVersion());
        int vasyaLooses = 0;
        int petyaLooses = 0;

        DumbGame game = new DumbGame();
        game.addPlayer(new DumbPlayer("Vasya", new SimpleStrategy()));
        game.addPlayer(new DumbPlayer("Petya", new AdvancedStrategy()));
        Supervisor supervisor = Supervisor.getInstance();
        supervisor.setGame(game);
        for (int i = 0; i < 100; i++) {
            game.play();
            if (DumbGame.lastLooser.equals("Vasya")) {
                vasyaLooses++;
            } else if (DumbGame.lastLooser.equals("Petya")) {
                petyaLooses++;
            }
            game.reset();
        }

        System.out.println("Vasya looses: " + vasyaLooses + " Petya looses: " + petyaLooses);
    }
}
