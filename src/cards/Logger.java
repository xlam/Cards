package cards;

/**
 *
 * @author Sergey
 */
public class Logger {

    private static boolean isEnabled = false;

    public static void enable(boolean enableFlag) {
        isEnabled = enableFlag;
    }

    public static void log(String message) {
        if (isEnabled)
            System.out.println(message);
    }
}
