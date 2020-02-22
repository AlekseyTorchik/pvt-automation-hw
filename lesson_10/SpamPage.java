package PagesMailRu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpamPage extends InboxPage {

    @FindBy(xpath = "(//div[@class = 'dataset__items']/a[contains(@href, '/spam/')])[1]")
    private WebElement inboxLetter;

    @FindBy(xpath = "(//a[contains(@class, 'letter-bottom')]//span[@class = 'mb-checkbox__wrapper'])[1]")
    private WebElement checkBox;

    @FindBy(xpath = "//div[contains(@class,'element_unspam')]")
    private WebElement buttonUnSpamTopMenu;

    public SpamPage(WebDriver webdriver) {
        super(webdriver);
    }

    public void clickButtonUnSpamTopMenu() {
        buttonUnSpamTopMenu.click();
    }

    public void labelsOfLettersClicker(int numberOfLetters) {
        int i = 0;
        while (i < numberOfLetters) {
            wait = new WebDriverWait(webdriver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(inboxLetter));
            action = new Actions(webdriver);
            action.moveToElement(inboxLetter).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(checkBox));
            action.click(checkBox).build().perform();
            i++;
        }
    }
}
