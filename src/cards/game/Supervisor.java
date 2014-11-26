package cards.game;

/**
 *
 * @author Sergey
 */
public class Supervisor {
    
    public static Supervisor instance = null;
    private DumbGame game;
    private Supervisor() {};
    
    public static Supervisor getInstance() {
        if (null == instance)
            instance = new Supervisor();
        return instance;
    }
    
    public int getPlayersCount() {
        return game.getPlayersCount();
    }
    
    public void setGame(DumbGame game) {
        this.game = game;
    }
    
}
