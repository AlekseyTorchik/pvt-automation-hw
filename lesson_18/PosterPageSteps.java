import YandexByPosterCinema.MainPage;
import YandexByPosterCinema.PosterPage;
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

import model.SQLMovieName;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static model.SQLMovieName.getDBMovies;

public class PosterPageSteps {
    private String apiKey = "fLBY101brwB0bgW9shn4ZvlTFJp107r109GkHWAuZicTSaCiM110";
    private String mailUrl = "https://yandex.by/";
    private String movieName;
    private String appName = "YandexByPosterCinema";
    private String windowName = "Poster Cinema";
    private String stepName = "Search Result";

    private MainPage mainPage;
    private PosterPage posterPage;
    private WebDriver driver;
    private TestResultsSummary allTestResults;

    @Before
    public void setWebDriver() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\Programs\\Setups\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("^I am on a main page$")
    public void getMainPage() {
        driver.get(mailUrl);
        mainPage = new MainPage(driver);
    }

    @When("^I go to poster page$")
    public void getPosterPage() {
        mainPage.clickPosterPageLink();
        Set<String> windowTabs = driver.getWindowHandles();
        for (String window : windowTabs) {
            driver.switchTo().window(window);
        }
    }

    @And("^I search movie for tonight$")
    public void searchMovieForTonight() throws ClassNotFoundException {
        posterPage = new PosterPage(driver);
        movieName = SQLMovieName.getDBMovies().toString().replaceAll("^\\[|\\]$", "");
        posterPage.setSearchBar(movieName);
        posterPage.clickToMovieBar();
        posterPage.setDayOfMovie();
        posterPage.setPeriodOfTheDay();
    }

     @And ("^I take screenshot of result$")
    public void snapShotOfResult() {
        allTestResults = snapshot();
    }

    @Then ("^I check available cinema for tonight$")
    public void  checkAvailableCinema() {
        Assert.assertTrue("ERROR! No available cinema",posterPage.checkAvailableVenueName());
    }

    @After
    public void WebDriver() {
        driver.close();
        driver.quit();
    }

    public TestResultsSummary snapshot() {
        EyesRunner runner = new ClassicRunner();
        Eyes eyes = new Eyes(runner);
        eyes.setApiKey(apiKey);
        eyes.open(driver, appName, stepName);
        eyes.checkWindow(windowName);
        eyes.closeAsync();
        eyes.abortIfNotClosed();
        TestResultsSummary allTestResults = runner.getAllTestResults();
        return allTestResults;
    }
}
