package cards.main;

import cards.dumbgame.*;

public class CardsStrategyTester {

    public static void main(String[] args) {
        System.out.println("Cards v" + Version.getVersion());
        int vasyaWins = 0;
        int petyaWins = 0;

        DumbGame game = new DumbGame();
        game.addPlayer(new Player("Vasya", new SimpleStrategy()));
        game.addPlayer(new Player("Petya", new AdvancedStrategy()));
        Supervisor supervisor = Supervisor.getInstance();
        supervisor.setGame(game);
        for (int i = 0; i < 100; i++) {
            game.play();
            if (DumbGame.lastWinner.equals("Vasya")) {
                vasyaWins++;
            } else if (DumbGame.lastWinner.equals("Petya")) {
                petyaWins++;
            }
            game.reset();
        }

        System.out.println("Vasya wins: " + vasyaWins + " Petya wins: " + petyaWins);
    }
}
