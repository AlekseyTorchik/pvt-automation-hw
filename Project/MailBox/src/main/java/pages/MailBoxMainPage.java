package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailBoxMainPage {

    private static final int LINK_PRESENSE_TIMEOUT = 15;
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@name = 'name']")
    private WebElement newFolderName;

    @FindBy(xpath = "//div[@class = 'new-folder-btn__button-wrapper']")
    private WebElement newFolderButton;

    @FindBy(xpath = "//a[contains(@title,'newFolder')]")
    private WebElement newFolderBarInMenu;

    @FindBy(xpath = "//a[contains(@title,'Archive')]")
    private WebElement archiveBarInMenu;

    @FindBy(xpath = "//span[@class = 'filters-control__filter-text']")
    private WebElement filterButton;

    @FindBy(xpath = "(//span[@class='list-item__text'])[2]")
    private WebElement unreadButtonInFilter;

    @FindBy(xpath = "//span[@class = 'llc__subject llc__subject_unread']")
    private WebElement unreadEmail;

    @FindBy(xpath = "//span[@title = 'Select all']")
    private WebElement selectAllButton;

    @FindBy(xpath = "//span[@title = 'Move to']")
    private WebElement moveToButton;

    @FindBy(xpath = "//div[@title = 'newFolder']")
    private WebElement nameNewFolderInSpanMenu;

    @FindBy(xpath = "//a[contains(@title,'newFolder')]//span[@class = 'badge__text']")
    private WebElement folderContainsNumberMarker;

    @FindBy(xpath = "//span[@class = 'compose-button__wrapper']")
    private WebElement newLetterButton;

    @FindBy(xpath = "//a[@href = '/sent/']")
    private WebElement sentButtonInMenu;

    @FindBy(xpath = "//a[@href = '/inbox/']")
    private WebElement inboxButtonInMenu;

    @FindBy(xpath = "//a[@href = '/trash/']")
    private WebElement trashButtonInMenu;

    @FindBy(xpath = "(//span[@class = 'llc__subject']/span[@class = 'll-sj__normal'])[1]")
    private WebElement subjectOfLetterInMailBox;

    @FindBy(xpath = "(//a[contains(@href,'/inbox/1')])[1]")
    private WebElement subjectOfLetterInInbox;

    @FindBy(xpath = "(//div[@class = 'llc__content']//span[@class = 'll-sj__normal'])[1]")
    private WebElement subjectOfLetterInArchive;

    @FindBy(xpath = "(//span[@data-title-shortcut= 'F'])[1]")
    private WebElement forwardButton;

    @FindBy(xpath = "(//span[@data-title-shortcut= 'E'])[1]")
    private WebElement archiveButton;

    @FindBy(xpath = "(//span[@data-title-shortcut= 'Del'])[1]")
    private WebElement deleteButton;

    public MailBoxMainPage(WebDriver webdriver) {
        this.driver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    public void createNewFolderClick() {
        visibilityWaiter(newFolderButton);
        newFolderButton.click();
    }

    public String findSubjectOfLetter() {
        visibilityWaiter(subjectOfLetterInMailBox);
        return subjectOfLetterInMailBox.getText();
    }

    public String findSubjectOfLetterInArchive() {
        visibilityWaiter(subjectOfLetterInArchive);
        return subjectOfLetterInArchive.getText();
    }

    public void submitNameNewFolder(String nameOfNewFolder) {
        visibilityWaiter(newFolderName);
        newFolderName.sendKeys(nameOfNewFolder);
        newFolderName.submit();
    }

    public boolean checkNewFolderInMenu() {
        visibilityWaiter(newFolderBarInMenu);
        return newFolderBarInMenu.isDisplayed();
    }

    public void clickNewFolderInMenu() {
        newFolderBarInMenu.click();
    }

    public void selectAllButtonOnTopMenu() {
        selectAllButton.click();
    }

    public void clickSentButtonInLeftMenu() {
        sentButtonInMenu.click();
    }

    public void clickTrashButtonInLeftMenu() {
        trashButtonInMenu.click();
    }

    public void clickSubjectOfLetter() {
        visibilityWaiter(subjectOfLetterInInbox);
        subjectOfLetterInInbox.click();
    }

    public void clickInboxButtonInLeftMenu() {
        toBeClickableWaiter(inboxButtonInMenu);
        inboxButtonInMenu.click();
    }

    public void clickForwardButtonInTopMenu() {
        toBeClickableWaiter(forwardButton);
        forwardButton.click();
    }

    public void moveLettersInNewFolderFromInbox() {
        moveToButton.click();
        toBeClickableWaiter(nameNewFolderInSpanMenu);
        nameNewFolderInSpanMenu.click();
    }

    public void moveLettersFromNewFolderToArchive() {
        archiveButton.click();
        toBeClickableWaiter(archiveBarInMenu);
        archiveBarInMenu.click();
    }

    public void moveLettersFromArchiveToTrash() {
        toBeClickableWaiter(deleteButton);
        deleteButton.click();
    }

    public void selectUnreadIncomingEmail() {
        filterButton.click();
        toBeClickableWaiter(unreadButtonInFilter);
        unreadButtonInFilter.click();
    }

    public boolean unreadEmailIsDisplayed() {
        visibilityWaiter(unreadEmail);
        return unreadEmail.isDisplayed();
    }

    public void clickNewLetterButton() {
        toBeClickableWaiter(newLetterButton);
        newLetterButton.click();
    }

    public boolean checkContentInNewFolder() {
        return folderContainsNumberMarker.getText().matches("\\d+");
    }

    public void visibilityWaiter(WebElement elementVisibility) {
        wait = new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(elementVisibility));
    }
    public void toBeClickableWaiter(WebElement elementClickable) {
        wait = new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(elementClickable));
    }
}