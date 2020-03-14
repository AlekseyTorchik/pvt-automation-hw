package mailRuLogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private static final int LINK_PRESENSE_TIMEOUT = 15;
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement submitField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement buttonSubmitOnLoginPage;

    @FindBy(xpath = "//*[@id='PH_logoutLink']")
    private WebElement logoutLink;

    public LoginPage(WebDriver webdriver) {
        this.driver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    public void setUserLoginAndPassword(String login, String password) {
        setUserIdentification(loginField, submitField, login);
        wait = new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT);
        setUserIdentification(passwordField, submitField, password);
    }

    private void setUserIdentification(WebElement element, WebElement clickElement, String identifier) {
        element.clear();
        element.sendKeys(identifier);
        clickElement.click();
    }

    public void logout() {
        logoutLink.click();
    }
}
