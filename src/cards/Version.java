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
        String version =
            VERSION_MAJOR + "." +
            VERSION_MINOR + "." +
            VERSION_BUILD;
        if (!VERSION_STAGE.isEmpty())
            version += "-" + VERSION_STAGE;
        return version;
    }

}
