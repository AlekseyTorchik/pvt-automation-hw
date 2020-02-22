package PagesMailRu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewLetterWindow extends InboxPage {

    @FindBy(xpath = "//input[@name = 'Subject']")
    private WebElement subjectNewLetter;

    @FindBy(xpath = "//div[@role = 'textbox']/div")
    private WebElement textMessage;

    @FindBy(xpath = "//div[@data-name = 'to']//button")
    private WebElement toContacts;

    @FindBy(xpath = "//div[@class = 'b-tree__group__list']/div[@data-id='1']")
    private WebElement groupOfContactsButton;

    @FindBy(xpath = "//div[@class='explorer explorer_from-contacts']//span[@class = 'mb-checkbox__wrapper']")
    private WebElement fillUpGroupOfContacts;

    @FindBy(xpath = "//div[@class='layer__footer']/span[contains(@class,'button2_primary')]")
    private WebElement insertToLetterGroupOfContacts;

    @FindBy(xpath = "(//div[@class = 'compose-app__buttons']/span)[1]")
    private WebElement sendNewLetter;

    @FindBy(xpath = "//div[@class = 'layer__controls']")
    private WebElement exitButtonOfNotificationWindow;

    public NewLetterWindow(WebDriver webdriver) {
        super(webdriver);
    }

    public void setUpSubjectAndTextOfNewLetter(String subjectOfNewLetter, String textMessageToNewLetter) {
        wait = new WebDriverWait(webdriver, 10);
        wait.until(ExpectedConditions.visibilityOf(subjectNewLetter));
        subjectNewLetter.clear();
        subjectNewLetter.sendKeys(subjectOfNewLetter);
        textMessage.clear();
        textMessage.sendKeys(textMessageToNewLetter);
    }
    public void setUpGroupOfContacts() {
        toContacts.click();
        wait = new WebDriverWait(webdriver, 10);
        wait.until(ExpectedConditions.visibilityOf(groupOfContactsButton));
        groupOfContactsButton.click();
        fillUpGroupOfContacts.click();
        insertToLetterGroupOfContacts.click();
        sendNewLetter.click();
    }
    public void closeNotificationWindow() {
        wait = new WebDriverWait(webdriver, 10);
        wait.until(ExpectedConditions.visibilityOf(exitButtonOfNotificationWindow));
        exitButtonOfNotificationWindow.click();
    }
}

