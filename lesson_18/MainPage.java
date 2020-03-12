package YandexByPosterCinema;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private static final int LINK_PRESENSE_TIMEOUT = 15;
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[@href = 'https://afisha.yandex.by/']")
    private WebElement posterPage;

    public MainPage(WebDriver webdriver) {
        this.driver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    public void clickPosterPageLink() {
        wait = new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(posterPage));
        posterPage.click();
    }
}
