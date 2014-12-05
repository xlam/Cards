package cards;

import cards.dumbgame.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StrategyTesterManyPlayers {

    public static void main(String[] args) {
        System.out.println("Cards v" + Version.getVersion());

        HashMap<AIDumbPlayer, Integer> players = new HashMap<>();

        players.put(new AIDumbPlayer("Vasya", new SimpleStrategy()), 0);
        players.put(new AIDumbPlayer("Jenya", new SimpleStrategy()), 0);
        players.put(new AIDumbPlayer("Petya", new AdvancedStrategy()), 0);
        players.put(new AIDumbPlayer("Gosha", new MemoryStrategy()), 0);

        DumbGame game = new DumbGame();
        Supervisor supervisor = Supervisor.getInstance();
        supervisor.setGame(game);

        Set<Map.Entry<AIDumbPlayer, Integer>> playersSet = players.entrySet();
        for (Map.Entry<AIDumbPlayer, Integer> entry: playersSet)
            game.addPlayer(entry.getKey());

        for (int i = 0; i < 100; i++) {
            game.play();
            AIDumbPlayer looser = game.getLastLooser();
            if (looser != null)
                players.put(looser, players.get(looser) + 1);
            game.reset();
        }

        for (Map.Entry<AIDumbPlayer, Integer> entry: playersSet) {
            System.out.print(entry.getKey() + " looses: " + entry.getValue());
            System.out.println(" (" + entry.getKey().getStrategy() + ")");
        }
    }
}
