package cards;

import cards.dumbgame.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StrategyTesterManyPlayers {

    public static void main(String[] args) {
        System.out.println("Cards v" + Version.getVersion());

        HashMap<DumbPlayer, Integer> players = new HashMap<>();

        players.put(new DumbPlayer("Vasya", new SimpleStrategy()), 0);
        players.put(new DumbPlayer("Jenya", new SimpleStrategy()), 0);
        players.put(new DumbPlayer("Petya", new AdvancedStrategy()), 0);

        DumbGame game = new DumbGame();
        Supervisor supervisor = Supervisor.getInstance();
        supervisor.setGame(game);

        Set<Map.Entry<DumbPlayer, Integer>> playersSet = players.entrySet();
        for (Map.Entry<DumbPlayer, Integer> entry: playersSet)
            game.addPlayer(entry.getKey());

        for (int i = 0; i < 100; i++) {
            game.play();
            if (game.lastLooser != null)
                players.put(game.lastLooser, players.get(game.lastLooser) + 1);
            game.reset();
        }

        for (Map.Entry<DumbPlayer, Integer> entry: playersSet) {
            System.out.println(entry.getKey() + " looses: " + entry.getValue());
        }
    }
}
