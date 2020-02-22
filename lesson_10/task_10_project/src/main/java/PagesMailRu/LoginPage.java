package PagesMailRu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement buttonSubmitOnLoginPage;

    private WebDriver webdriver;

    public LoginPage(WebDriver webdriver) {
        this.webdriver = webdriver;
        PageFactory.initElements(webdriver, this);
    }
    public void setLoginAndPassword(String login, String password) {
        loginField.clear();
        loginField.sendKeys(login);
        buttonSubmitOnLoginPage.click();
        passwordField.sendKeys(password);
        buttonSubmitOnLoginPage.click();
    }

}
