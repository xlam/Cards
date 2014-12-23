package cards;

/**
 *
 * @author Sergey
 */
public class Version {

    private static final String VERSION_MAJOR = "1";
    private static final String VERSION_MINOR = "3";
    private static final String VERSION_BUILD = "3";
    private static final String VERSION_STAGE = "dev";

    public static final String getVersion() {
        return
            VERSION_MAJOR + "." +
            VERSION_MINOR + "." +
            VERSION_BUILD + "-" +
            VERSION_STAGE;
    }

}
