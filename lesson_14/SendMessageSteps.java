package MailruVisualUItest;

import Mailru.LoginPage;
import Mailru.MainMailPage;
import Mailru.NewLetterWindow;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SendMessageSteps {
    private String apiKey = "fLBY101brwB0bgW9shn4ZvlTFJp107r109GkHWAuZicTSaCiM110";
    private String mailUrl = "http://mail.ru";
    private String mailName = "testautomation9999@mail.ru";
    private String mailPassword = "asd968574321";
    private String contactMail = "alexeytor4ik@gmail.com";
    private String subjectOfLetter = "TEST";
    private String messageInLetter = "test-test-test";

    private LoginPage loginPage;
    private MainMailPage mainMailPage;
    private NewLetterWindow newLetterWindow;
    private WebDriver driver;
    private TestResultsSummary allTestResults;

    @Before
    public void enterSite() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\Programs\\Setups\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("^I am on a main application page$")
    public void getMailPage() {
        driver.get(mailUrl);
        loginPage = new LoginPage(driver);

    }

    @When("^I take screenshot login page$")
    public void screenshotMailPage() {
        allTestResults = snapshot("Mail page window", "Main page step");
    }

    @And("^I login as a correct user$")
    public void loginAsCorrectUser() {
        loginPage.setLoginAndPassword(mailName, mailPassword);
    }

    @And("^I take screenshot inbox window$")
    public void screenshotInboxPage() {
        allTestResults = snapshot("Inbox page window", "Inbox page step");
    }

    @And("^I create new letter$")
    public void createNewLetter() {
        mainMailPage = new MainMailPage(driver);
        mainMailPage.clickNewLetterButton();
        newLetterWindow = new NewLetterWindow(driver);
        newLetterWindow.setUpContact(contactMail);
        newLetterWindow.setUpSubjectAndTextOfNewLetter(subjectOfLetter, messageInLetter);
    }

    @And("^I take screenshot new letter window$")
    public void screenshotNewLetterWindow() {
        newLetterWindow.clickToExpandNewLetterWindow();
        allTestResults = snapshot("New letter window", "New letter window step");
    }

    @And("^I send letter$")
    public void sendLetter() {
        newLetterWindow.clickToSendButtonNewLetterWindow();
    }

    @And("^I take screenshot send notification window")
    public void screenshotSendNotificationWindow() {
        allTestResults = snapshot("Notification window", "Notification window step");

    }
    @Then("^I compare screenshots$")
    public void screenshotsCompassion() {
        Assert.assertTrue(allTestResults.getAllResults()[0].getTestResults().isPassed());
    }

    @After
    public void exitSite() {
        newLetterWindow.closeNotificationWindow();
        loginPage.logout();
        driver.close();
        driver.quit();
    }

    public TestResultsSummary snapshot(String windowName, String stepName) {
        EyesRunner runner = new ClassicRunner();
        Eyes eyes = new Eyes(runner);
        eyes.setApiKey(apiKey);
        eyes.open(driver, "SendMessage", stepName);
        eyes.checkWindow(windowName);
        eyes.closeAsync();
        eyes.abortIfNotClosed();
        TestResultsSummary allTestResults = runner.getAllTestResults();
        return allTestResults;
    }
}
