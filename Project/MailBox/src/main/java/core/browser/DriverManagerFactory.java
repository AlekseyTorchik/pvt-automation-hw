package core.browser;

public class DriverManagerFactory {
    public static DriverManager getManager(DriverType type) {
        DriverManager driverManager;
        switch (type) {
            case CHROME:
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;
    }
}
