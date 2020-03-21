package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewLetterWindow {
    private static final int LINK_PRESENSE_TIMEOUT = 15;

    private WebDriverWait wait;
    private WebDriver webdriver;

    @FindBy(xpath = "//input[@name = 'Subject']")
    private WebElement subjectNewLetter;

    @FindBy(xpath = "//div[@role = 'textbox']/div")
    private WebElement textMessage;

    @FindBy(xpath = "//input[contains(@class,'container')]")
    private WebElement toContact;

    @FindBy(xpath = "//button[@title = 'Expand']")
    private WebElement expand;

    @FindBy(xpath = "(//div[@class = 'compose-app__buttons']/span)[1]")
    private WebElement sendNewLetter;

    @FindBy(xpath = "//div[@class = 'layer__controls']")
    private WebElement exitButtonOfNotificationWindow;

    public NewLetterWindow(WebDriver webdriver) {
        this.webdriver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    public void setUpSubjectAndTextOfNewLetter(String subjectOfNewLetter, String textMessageToNewLetter) {
        wait = new WebDriverWait(webdriver, LINK_PRESENSE_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(subjectNewLetter));
        subjectNewLetter.clear();
        subjectNewLetter.sendKeys(subjectOfNewLetter);
        textMessage.clear();
        textMessage.sendKeys(textMessageToNewLetter);
    }

    public void setUpContact(String contactMail) {
        wait = new WebDriverWait(webdriver, LINK_PRESENSE_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(toContact));
        toContact.sendKeys(contactMail);
    }

    public void clickToExpandNewLetterWindow() {
        expand.click();
    }

    public void clickToSendButtonNewLetterWindow() {
        sendNewLetter.click();
    }

    public void closeNotificationWindow() {
        wait = new WebDriverWait(webdriver, LINK_PRESENSE_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(exitButtonOfNotificationWindow));
        exitButtonOfNotificationWindow.click();
    }
}