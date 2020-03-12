package YandexByPosterCinema;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PosterPage {

    private static final int LINK_PRESENSE_TIMEOUT = 15;

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;

    @FindBy(xpath = "(//div[contains(@class,'menu__item menu__item_type_option')])[5]")
    private WebElement todayLink;

    @FindBy(xpath = "//div[contains(@data-bem,'evening')]")
    private WebElement eveningLink;

    @FindBy(xpath = "//div[@class = 'asuggest-item-description__name']")
    private WebElement movieNameBar;

    @FindBy(xpath = "//input[@type = 'search']")
    private WebElement searchBar;

    @FindBy(xpath = "(//button[@role = 'listbox'])[1]")
    private WebElement listBoxOfDays;

    @FindBy(xpath = "(//button[@role = 'listbox'])[2]")
    private WebElement listBoxOfPeriodsOfDay;

    @FindBy(xpath = "//a[contains(@class,'afisha-common-venue-name')]")
    private WebElement venueName;

    public PosterPage(WebDriver webdriver) {
        this.driver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    public void setSearchBar(String movieName) {
        searchBar.clear();
        searchBar.sendKeys(movieName);
        searchBar.submit();
    }

    public void clickToMovieBar() {
        movieNameBar.click();
    }

    public void setDayOfMovie() {
        wait = new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(listBoxOfDays));
        action = new Actions(driver);
        action.moveToElement(listBoxOfDays).build().perform();
        action.click(listBoxOfDays).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(todayLink));
        action.moveToElement(todayLink).build().perform();
        action.click(todayLink).build().perform();
    }

    public void setPeriodOfTheDay() {
        wait.until(ExpectedConditions.elementToBeClickable(listBoxOfPeriodsOfDay));
        action.moveToElement(listBoxOfPeriodsOfDay).build().perform();
        action.click(listBoxOfPeriodsOfDay).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(eveningLink));
        action.moveToElement(eveningLink).build().perform();
        action.click(eveningLink).build().perform();
    }

    public boolean checkAvailableVenueName() {
        venueName.isDisplayed();
        return true;
    }
}
