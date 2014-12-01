package cards.dumbgame;

/**
 *
 * @author Sergey
 */
public abstract class AbstractStrategy implements Strategy {

    protected Supervisor supervisor;

    protected Supervisor getSupervisor() {
        if (null == supervisor)
            supervisor = Supervisor.getInstance();
        return supervisor;
    }
}
