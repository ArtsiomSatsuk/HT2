package credentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InputData {

    public static String ADMIN_NAME = null;
    public static String ADMIN_PASSWORD = null;

    public static void readProperties() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("info.properties"));

        ADMIN_NAME = p.getProperty("AdminName");
        ADMIN_PASSWORD = p.getProperty("AdminPassword");
    }
}