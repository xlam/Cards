package cards.game;

/**
 *
 * @author Sergey
 */
public class Supervisor {
    
    public static Supervisor instance = null;
    
    private Supervisor() {};
    
    public static Supervisor getInstance() {
        if (null == instance)
            instance = new Supervisor();
        return instance;
    }
    
    public int getPlayersCount() {
        return 2;
    }
    
    public void setGame(DumbGame game) {
        
    }
    
}
