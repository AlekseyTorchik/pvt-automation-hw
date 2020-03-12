package Mailru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

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

    public void setLoginAndPassword(String login, String password) {
        loginField.clear();
        loginField.sendKeys(login);
        buttonSubmitOnLoginPage.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        buttonSubmitOnLoginPage.click();
    }

    public void logout() {
        logoutLink.click();
    }
}
