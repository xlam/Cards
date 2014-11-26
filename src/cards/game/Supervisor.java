/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cards.game;

/**
 *
 * @author Admin
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
    
    
    
}
