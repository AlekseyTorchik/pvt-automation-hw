package PagesMailRu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class InboxPage {

    @FindBy(xpath = "//a[@href = '/inbox/']")
    private WebElement buttonInboxInMenu;

    @FindBy(id = "PH_logoutLink")
    private WebElement logout;

    @FindBy(xpath = "(//div[@class = 'dataset__items']/a[contains(@href, '/inbox/')])")
    public List<WebElement> inboxLetterList;

    @FindBy(xpath = "(//a[contains(@class, 'letter-bottom')]//span[@class = 'mb-checkbox__wrapper'])")
    public List<WebElement> checkBoxList;

    @FindBy(xpath = "//div[contains(@class,'element_spam')]")
    private WebElement buttonSpamInTopMenu;

    @FindBy(xpath = "//a[@href = '/spam/']")
    private WebElement buttonSpamInMenu;

    @FindBy(xpath = "//span[@class = 'compose-button__wrapper']")
    private WebElement newLetterButton;

    protected WebDriver webdriver;
    protected WebDriverWait wait;
    protected Actions action;

    public InboxPage(WebDriver webdriver) {
        this.webdriver = webdriver;
        PageFactory.initElements(webdriver, this);
    }
    public void setInboxPage() {
        wait = new WebDriverWait(webdriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(buttonInboxInMenu));
        buttonInboxInMenu.click();
    }
    public void logoutMail() {
        logout.click();
    }
    public void clickSpamInTopMenu() {
        buttonSpamInTopMenu.click();
    }
    public void clickSpamInMenu() {
        buttonSpamInMenu.click();
    }
    public void clickNewLetterButton() {
        newLetterButton.click();
    }
    public void labelsOfLettersClicker(int numberOfLetters) {
        int i = 0;
        while (i < numberOfLetters) {
            wait = new WebDriverWait(webdriver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(inboxLetterList.get(i)));
            action = new Actions(webdriver);
            action.moveToElement(inboxLetterList.get(i)).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(checkBoxList.get(i)));
            action.click(checkBoxList.get(i)).build().perform();
            i++;
        }
    }
}
