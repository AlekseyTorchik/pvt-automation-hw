package Mailru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMailPage {
    private static final int LINK_PRESENSE_TIMEOUT = 15;

    private WebDriver webdriver;
    private WebDriverWait wait;

    @FindBy(xpath = "//span[@class = 'compose-button__wrapper']")
    private WebElement newLetterButton;

    @FindBy(xpath = "//a[@href = '/inbox/']")
    private WebElement buttonInboxInMenu;

    public MainMailPage(WebDriver webdriver) {
        this.webdriver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    public void clickNewLetterButton() {
        wait = new WebDriverWait(webdriver, LINK_PRESENSE_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(newLetterButton));
        newLetterButton.click();
    }
}
