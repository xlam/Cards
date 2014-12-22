package cards;

import cards.dumbgame.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StrategyTesterDistribution {

    public static void main(String[] args) {
        System.out.println("Cards v" + Version.getVersion());

        HashMap<DumbPlayer, Integer> players = new HashMap<>();

        players.put(new AIDumbPlayer("Vasya", new SimpleStrategy()), 0);
        players.put(new AIDumbPlayer("Jenya", new SimpleStrategy()), 0);
        players.put(new AIDumbPlayer("Petya", new AdvancedStrategy()), 0);
        players.put(new AIDumbPlayer("Gosha", new MemoryStrategy()), 0);

        Set<Map.Entry<DumbPlayer, Integer>> playersSet = players.entrySet();

        int iterations = 100;
        int playersCount = players.size();

        int dist[][] = new int[iterations][playersCount];

        for (int j = 0; j < iterations; j++) {
            System.out.println("Playing iteration " + j + "...");
            DumbGame game = new DumbGame();
            Supervisor supervisor = Supervisor.getInstance();
            supervisor.setGame(game);

            for (Map.Entry<DumbPlayer, Integer> entry: playersSet)
                players.put(entry.getKey(), 0);

            for (Map.Entry<DumbPlayer, Integer> entry: playersSet)
                game.addPlayer(entry.getKey());

            for (int i = 0; i < 100; i++) {
                game.play();
                DumbPlayer looser = game.getLastLooser();
                if (looser != null)
                    players.put(looser, players.get(looser) + 1);
                game.reset();

            }

            int k = 0;
            for (Map.Entry<DumbPlayer, Integer> entry: playersSet) {
                dist[j][k++] = entry.getValue();
                System.out.println(entry);
            }

        }

        for (int j = 0; j < iterations; j++) {
            System.out.print(j);
            for (int i = 0; i < playersCount; i++)
                System.out.print(";" + dist[j][i]);
            System.out.println("");
        }
    }
}
