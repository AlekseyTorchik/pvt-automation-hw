package PagesMailRu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InboxPageFlag extends InboxPage {

    @FindBy(xpath = "(//div[@class = 'dataset__items']/a[contains(@href, '/inbox/')])")
    public List<WebElement> letterList;

    @FindBy(xpath = "(//div[@class='llc__item llc__item_flag']/button)")
    public List<WebElement> flagList;

    public InboxPageFlag(WebDriver webdriver) {
        super(webdriver);
    }

    public void labelsOfLettersClicker(int numberOfLetters) {
        int i = 0;
        while (i < numberOfLetters) {
            wait = new WebDriverWait(webdriver, 15);
            wait.until(ExpectedConditions.elementToBeClickable(letterList.get(i)));
            action = new Actions(webdriver);
            action.moveToElement(letterList.get(i)).build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(flagList.get(i)));
            action.click(flagList.get(i)).build().perform();
            i++;
        }
    }
}
