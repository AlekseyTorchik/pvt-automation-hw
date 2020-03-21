package base;

import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import core.browser.DriverManager;
import core.browser.DriverManagerFactory;
import core.configuration.Configuration;
import core.parser.AdditionalDataHandler;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.AdditionalData;
import model.DataFromSQL;
import model.JavaDBConnect;
import org.apache.log4j.Logger;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;
import pages.LoginPage;
import pages.MailBoxMainPage;
import pages.NewLetterWindow;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MailRuStepsTest {
    public static final String ADDITIONALDATA_XML = "AdditionalData.xml";
    private WebDriver driver;
    private static Logger logger = Logger.getLogger(MailRuStepsTest.class);
    private DriverManager driverManager;
    private LoginPage loginPage;
    private MailBoxMainPage mailBoxMainPage;
    private NewLetterWindow newLetterWindow;
    private TestResultsSummary allTestResults;
    private String login;
    private String password;
    private String contactMail;
    private String subjectOfLetter;
    private String messageInLetter;
    private String apiKey;
    private String nameOfNewFolder;
    private String subject;

    @Before
    public void beforeTest() throws SQLException, IOException, SAXException, ParserConfigurationException {
        driverManager = DriverManagerFactory.getManager(Configuration.getDriverType());
        driver = driverManager.getDriver();
        driver.manage().window().maximize();
        List<DataFromSQL> mailDataBase = JavaDBConnect.getDBMailData();
        login = mailDataBase.get(0).getLogin();
        password = mailDataBase.get(0).getPassword();
        mailBoxMainPage = new MailBoxMainPage(driver);
        loginPage = new LoginPage(driver);
        newLetterWindow = new NewLetterWindow(driver);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        AdditionalDataHandler additionalDataHandler = new AdditionalDataHandler();
        saxParser.parse(new File(ADDITIONALDATA_XML), additionalDataHandler);
        List<AdditionalData> fields = additionalDataHandler.getFields();
        contactMail = fields.get(0).getContactMail();
        subjectOfLetter = fields.get(0).getSubjectOfLetter();
        messageInLetter =fields.get(0).getMessageInLetter();
        apiKey = fields.get(0).getApiKey();
        nameOfNewFolder = fields.get(0).getNameOfNewFolder();
        logger.info("Start before tests");
    }

    @Given("^I am on a main application page$")
    public void launchTestAutomationChromeMainPage() {
        driver.get(Configuration.getMainPageUrl());
        logger.info("Open browser");
    }

    @When("^I login as a correct user$")
    public void loginAsCorrectUser() {
        loginPage.setUserLoginAndPassword(login, password);
    }

    @And("^I am on inbox page$")
    public void screenshotInboxPage() {
        allTestResults = snapshot("Mailbox window", "Inbox page step");
    }

    @Then("^is the inbox page$")
    public void isTheInboxPageTest() {
        Assert.assertTrue(allTestResults.getAllResults()[0].getTestResults().isPassed());
        logger.info("Compare screenshots");
    }

    @And("^I filter only unread email$")
    public void filterUnreadEmailAtInboxPage() {
        mailBoxMainPage.selectUnreadIncomingEmail();
    }

    @Then("^unread email are shown$")
    public void unreadEmailAreShownTest() {
        Assert.assertTrue(mailBoxMainPage.unreadEmailIsDisplayed());
        logger.info("Unread email are shown");
    }

    @And("^I send new email to other user$")
    public void sendNewEmailToOtherUser() {
        mailBoxMainPage.clickNewLetterButton();
        newLetterWindow.setUpContact(contactMail);
        newLetterWindow.setUpSubjectAndTextOfNewLetter(subjectOfLetter, messageInLetter);
        newLetterWindow.clickToSendButtonNewLetterWindow();
    }

    @Then("^email has been sent$")
    public void emailHasBeenSentTest() {
        newLetterWindow.closeNotificationWindow();
        mailBoxMainPage.clickSentButtonInLeftMenu();
        Assert.assertEquals(subjectOfLetter, mailBoxMainPage.findSubjectOfLetter());
        logger.info("New email has been sent");
    }

    @And("^I forward incoming email to other user$")
    public void forwardIncomingLetterToOtherUser() {
        mailBoxMainPage.clickInboxButtonInLeftMenu();
        mailBoxMainPage.clickSubjectOfLetter();
        mailBoxMainPage.clickForwardButtonInTopMenu();
        newLetterWindow.setUpContact(contactMail);
        newLetterWindow.clickToSendButtonNewLetterWindow();
    }

    @Then("^email has been forward$")
    public void emailHasBeenForwardTest() {
        newLetterWindow.closeNotificationWindow();
        mailBoxMainPage.clickSentButtonInLeftMenu();
        Assert.assertTrue(mailBoxMainPage.findSubjectOfLetter().contains("Fwd: "));
        logger.info("Email has been forward");
    }

    @And("^I create new folder in mailbox$")
    public void moveIncomingLettersToArchive() {
        mailBoxMainPage.createNewFolderClick();
        mailBoxMainPage.submitNameNewFolder(nameOfNewFolder);
    }

    @Then("^new folder is create in menu$")
    public void checkNewFolderInMenuTest() {
        Assert.assertTrue(mailBoxMainPage.checkNewFolderInMenu());
        logger.info("New folder is create");
    }

    @And("^I move email from inbox to new folder$")
    public void moveIncomingLettersToNewFolder() {
        mailBoxMainPage.clickInboxButtonInLeftMenu();
        mailBoxMainPage.selectAllButtonOnTopMenu();
        mailBoxMainPage.moveLettersInNewFolderFromInbox();
    }

    @Then("^email has been moved to new folder$")
    public void emailHasBeenMovedToNewFolderTest() {
        Assert.assertTrue(mailBoxMainPage.checkContentInNewFolder());
        logger.info("Email has been moved to new folder");
    }

    @And("^I move emails from new folder to archive$")
    public void moveLettersFromNewFolderToArchive() {
        mailBoxMainPage.clickNewFolderInMenu();
        subject = mailBoxMainPage.findSubjectOfLetterInArchive();
        mailBoxMainPage.selectAllButtonOnTopMenu();
        mailBoxMainPage.moveLettersFromNewFolderToArchive();
    }

    @Then("^emails has been moved to archive$")
    public void emailHasBeenMovedToArchiveTest() {
        Assert.assertEquals(subject, mailBoxMainPage.findSubjectOfLetterInArchive());
        logger.info("Email has been moved to archive");
    }

    @And("^I move message from archive to trash$")
    public void moveLettersFromArchiveToTrash() {
        subject = mailBoxMainPage.findSubjectOfLetterInArchive();
        mailBoxMainPage.selectAllButtonOnTopMenu();
        mailBoxMainPage.moveLettersFromArchiveToTrash();
        mailBoxMainPage.clickTrashButtonInLeftMenu();
    }

    @Then("^emails has been moved to trash$")
    public void emailHasBeenMovedToTrashTest() {
        Assert.assertEquals(subject, mailBoxMainPage.findSubjectOfLetterInArchive());
        logger.info("Email has been moved to trash");
    }

    @And("^I sing out$")
    public void singOut() {
        loginPage.logout();
        allTestResults = snapshot("Main page window", "logout page step");
    }

    @Then("^I am on a main page$")
    public void isMainPageTest() {
        Assert.assertTrue(allTestResults.getAllResults()[0].getTestResults().isPassed());
        logger.info("I am on a main page");
    }

    @After
    public void afterTest() {
        driverManager.quitDriver();
    }

    public TestResultsSummary snapshot(String windowName, String stepName) {
        EyesRunner runner = new ClassicRunner();
        Eyes eyes = new Eyes(runner);
        eyes.setApiKey(apiKey);
        eyes.open(driver, "Mailbox", stepName);
        eyes.checkWindow(windowName);
        eyes.closeAsync();
        eyes.abortIfNotClosed();
        TestResultsSummary allTestResults = runner.getAllTestResults();
        return allTestResults;
    }

}
