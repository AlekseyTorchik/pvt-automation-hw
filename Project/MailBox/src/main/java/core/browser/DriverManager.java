package core.browser;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    protected WebDriver driver;

    protected abstract void startServices();

    protected abstract void stopServices();

    protected abstract void createDriver();

    public WebDriver getDriver() {
        if (null == driver) {
            startServices();
            createDriver();
        }
        return driver;
    }

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }
}
