package cards;

public class CardsTestStrategy {

    public static void main(String[] args) {
        int vasyaWins = 0;
        int petyaWins = 0;
        
        DumbGame game = new DumbGame();
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
