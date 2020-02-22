import PagesMailRu.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestTask10 {
    private LoginPage loginPage;
    private InboxPage inboxPage;
    private SpamPage spamPage;
    private InboxPageFlag inboxPageFlag;
    private NewLetterWindow newLetterWindow;
    private WebDriver webdriver;

    private String nameURL = "http://mail.ru";
    private String nameLogin = "mymailboxgo";
    private String namePassword = "asd97654321";
    private String nameSubjectNewLetter = "test";
    private String textMessageToNewLetter = "test";

    private int numberOfInboxLettersCheckedToSpam = 1;
    private int numberOfSpamLettersCheckedUnSpam = 1;
    private int numberOfInboxLettersCheckedFlag = 3;

    @Test
    public void testTask10() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\Programs\\Setups\\chromedriver.exe");
        webdriver = new ChromeDriver();
        webdriver.get(nameURL);
        webdriver.manage().window().maximize();
        loginPage = new LoginPage(webdriver);
        loginPage.setLoginAndPassword(nameLogin, namePassword);
        inboxPage = new InboxPage(webdriver);
        inboxPage.setInboxPage();
        inboxPage.labelsOfLettersClicker(numberOfInboxLettersCheckedToSpam);
        inboxPage.clickSpamInTopMenu();
        inboxPage.clickSpamInMenu();
        spamPage = new SpamPage(webdriver);
        spamPage.labelsOfLettersClicker(numberOfSpamLettersCheckedUnSpam);
        spamPage.clickButtonUnSpamTopMenu();
        inboxPage.setInboxPage();
        inboxPageFlag = new InboxPageFlag(webdriver);
        inboxPageFlag.labelsOfLettersClicker(numberOfInboxLettersCheckedFlag);
        inboxPageFlag.labelsOfLettersClicker(numberOfInboxLettersCheckedFlag);
        inboxPage.clickNewLetterButton();
        newLetterWindow = new NewLetterWindow(webdriver);
        newLetterWindow.setUpSubjectAndTextOfNewLetter(nameSubjectNewLetter,textMessageToNewLetter);
        newLetterWindow.setUpGroupOfContacts();
        newLetterWindow.closeNotificationWindow();
        inboxPage.logoutMail();
        webdriver.quit();
    }

}