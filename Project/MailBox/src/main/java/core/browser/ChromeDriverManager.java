package core.browser;

import core.configuration.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService chromeDriverService;

    @Override
    public void startServices() {
        if (null == chromeDriverService) {
            try {
                WebDriverManager.chromedriver().setup();
                chromeDriverService = new ChromeDriverService.Builder().usingAnyFreePort().build();
                chromeDriverService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        //options.set(Configuration.getFullscreen());
        driver = new ChromeDriver(options);
    }

    @Override
    public void stopServices() {
        if (null != chromeDriverService && chromeDriverService.isRunning())
            chromeDriverService.stop();
    }
}
