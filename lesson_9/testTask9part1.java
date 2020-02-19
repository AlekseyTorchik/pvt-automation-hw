import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

public class testTask9part1 {

    @Test
    public void testBookingPart1() throws InterruptedException {
        int termOfLiving = 7;

        System.setProperty("webdriver.chrome.driver", "D:\\Programs\\Setups\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.booking.com/");
        System.out.println(driver.getTitle());

        WebElement searchInput = driver.findElement(By.xpath("//input[@id = 'ss']"));
        searchInput.sendKeys("Париж");
        Calendar calendar = new GregorianCalendar();
        setDiffBtwTodayCheckInDayAndTermOfLiving(driver, calendar, "yyyy-MM-dd", 3, 7);

        WebElement numberOfAdultsKidsRooms = driver.findElement(By.xpath("//div[@data-visible='accommodation,flights']"));
        numberOfAdultsKidsRooms.click();
        setTheNumberOfAdultsOrRooms(driver, "adults", 2);
        searchInput.submit();
        sleep(5000);

        WebElement sortByLowPrice = driver.findElement(By.xpath("//li[contains(@class,'sort_price')]"));
        sortByLowPrice.click();
        WebElement enableHotelList = driver.findElement(By.xpath("(//div[contains(@class,'bui-price-display__value')])"));
        assertTrue(enableHotelList.isDisplayed());

        WebElement priceLimit = driver.findElement(By.xpath("//a[contains(@data-id,'pri-4')]//span[contains(@class,'filter_label')]"));
        String currentPriceLimit = priceLimit.getText().substring(priceLimit.getText().indexOf('-')).replaceAll("\\D", "");
        int priceHighLimit = Integer.parseInt(currentPriceLimit);
        priceLimit.click();

        WebElement maxHotelScore = driver.findElement(By.xpath("//a[@data-id = 'review_score-90']"));
        maxHotelScore.click();
        sleep(6000);

        WebElement priceOfHotelWithMaxScore = driver.findElement(By.xpath("(//div[contains(@class,'bui-price-display__value')])[1]"));
        String currentPrice = priceOfHotelWithMaxScore.getText().replaceAll("\\D", "");
        int priceForHotel = Integer.parseInt(currentPrice);
        assertTrue(priceHighLimit >= priceForHotel / termOfLiving);
        driver.quit();
    }

    public void setTheNumberOfAdultsOrRooms(WebDriver driver, String param, int requireNumberOfParam) {
        int line = 0;
        switch (param) {
            case "adults":
                line = 1;
                break;
            case "rooms":
                line = 3;
                break;
            default:
                throw new IllegalStateException("ERROR! Please insert adults, kids or rooms " + param);
        }
        WebElement numberOfParam = driver.findElement(By.xpath("(//span[@class= 'bui-stepper__display'])[" + line + "]"));
        WebElement increaseNumberOfParam = driver.findElement(By.xpath("(//span[text() = '+'])[" + line + "]"));
        WebElement reduceNumberOfParam = driver.findElement(By.xpath("(//span[text() = '−'])[" + line + "]"));
        int number = Integer.parseInt(numberOfParam.getText());
        int a = Math.abs(requireNumberOfParam - number);
        if (number <= requireNumberOfParam) {
            for (int i = 0; i < a; i++) {
                increaseNumberOfParam.click();
            }
        } else for (int i = 0; i < a; i++) {
            reduceNumberOfParam.click();
        }
    }

    public void setDiffBtwTodayCheckInDayAndTermOfLiving(WebDriver driver, Calendar calendar,
                                                         String formatOfDate, int diffTodayCheckInDay,
                                                         int termOfLiving) {
        WebElement calendarBar = driver.findElement(By.xpath("//div[@class = 'xp__dates-inner']"));
        calendarBar.click();
        calendar.add(Calendar.DAY_OF_YEAR, +diffTodayCheckInDay);
        Date checkInDate = calendar.getTime();
        SimpleDateFormat formatForCheckInDate = new SimpleDateFormat(formatOfDate);
        String currentCheckInDate = formatForCheckInDate.format(checkInDate);
        WebElement checkInDateWeb = driver.findElement(By.xpath("//td[contains(@data-date,'" + currentCheckInDate + "')]"));
        calendar.add(Calendar.DAY_OF_YEAR, +termOfLiving);
        Date checkOutDate = calendar.getTime();
        SimpleDateFormat formatForCheckOutDate = new SimpleDateFormat(formatOfDate);
        String currentCheckOutDate = formatForCheckOutDate.format(checkOutDate);
        WebElement checkOutDateWeb = driver.findElement(By.xpath("//td[contains(@data-date,'" + currentCheckOutDate + "')]"));
        checkInDateWeb.click();
        checkOutDateWeb.click();
    }
}