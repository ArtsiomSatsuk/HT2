package constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InputParameters {

    public static String ADMIN_NAME = null;
    public static String ADMIN_PASSWORD = null;

    public static void readProperties() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("info.properties"));

        ADMIN_NAME = p.getProperty("AdminName");
        ADMIN_PASSWORD = p.getProperty("AdminPassword");
    }

    public static final String NEW_USER_NAME = "someuser";
    public static final String NEW_USER_PASSWORD = "somepassword";
    public static final String NEW_USER_FULL_NAME = "Some Full Name";
    public static final String NEW_USER_EMAIL = "some@addr.dom";

}
