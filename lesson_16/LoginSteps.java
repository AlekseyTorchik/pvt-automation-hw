package UItestByApplitools;

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
import mailRuLogin.LoginPage;
import model.Jdbc;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLException;

public class LoginSteps {

    private LoginPage loginPage;
    private WebDriver driver;
    private TestResultsSummary allTestResults;
    private String url;
    private String login;
    private String password;
    private String apiKey;

    @Before
    public void setWebDriver() throws SQLException {
        System.setProperty("webdriver.chrome.driver",
                "D:\\Programs\\Setups\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String dataForTests = Jdbc.getDBMailData().toString();
        url = dataForTests.substring(dataForTests.indexOf('1'), dataForTests.indexOf('2')).substring(1);
        login = dataForTests.substring(dataForTests.indexOf('2'), dataForTests.indexOf('^')).substring(1);
        password = dataForTests.substring(dataForTests.indexOf('^'), dataForTests.indexOf('*')).substring(1);
        apiKey = dataForTests.substring(dataForTests.indexOf('*'), dataForTests.indexOf('-')).substring(1);
    }

    @Given("^I am on a main application page$")
    public void getMailPage() {
        driver.get(url);
        loginPage = new LoginPage(driver);
    }

    @When("^I take screenshot login page$")
    public void screenshotMailPage() {
        allTestResults = snapshot("Mail page window", "Main page step");
    }

    @And("^I login as a correct user$")
    public void loginAsCorrectUser() {
        loginPage.setUserLoginAndPassword(login, password);
    }

    @And("^I take screenshot mail window$")
    public void screenshotInboxPage() {
        allTestResults = snapshot("Mail page window", "Mail page step");
    }

    @Then("^I compare screenshots$")
    public void screenshotsCompassion() {
        Assert.assertTrue(allTestResults.getAllResults()[0].getTestResults().isPassed());
    }

    @After
    public void exitWebDriver() {
        loginPage.logout();
        driver.close();
        driver.quit();
    }

    public TestResultsSummary snapshot(String windowName, String stepName) {
        EyesRunner runner = new ClassicRunner();
        Eyes eyes = new Eyes(runner);
        eyes.setApiKey(apiKey);
        eyes.open(driver, "Login", stepName);
        eyes.checkWindow(windowName);
        eyes.closeAsync();
        eyes.abortIfNotClosed();
        TestResultsSummary allTestResults = runner.getAllTestResults();
        return allTestResults;
    }
}
