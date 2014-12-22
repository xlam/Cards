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

        String[] names = new String[4];
        names[0] = "Vasya";
        names[0] = "Jenya";
        names[0] = "Petya";
        names[0] = "Gosha";

        Set<Map.Entry<DumbPlayer, Integer>> playersSet = players.entrySet();

        int dist[][] = new int[100][4];

        for (int j = 0; j < 100; j++) {
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

            for (Map.Entry<DumbPlayer, Integer> entry: playersSet) {
                int k = 0;
                dist[j][k] = entry.getValue();
                System.out.print(entry.getKey() + " looses: " + entry.getValue());
                System.out.println(" (" + entry.getKey().getStrategy() + ")");
            }

        }
        System.out.println(dist);
    }
}
