import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

public class testTask9part2and3 {

    @Test
    public void testBookingPart2and3() throws InterruptedException {
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
        setTheNumberOfAdultsOrRooms(driver, "adults", 4);
        setTheNumberOfAdultsOrRooms(driver, "rooms", 2);
        searchInput.submit();
        sleep(5000);

        WebElement highPrice = driver.findElement(By.xpath("//a[contains(@data-id,'pri-5')]//span[contains(@class,'filter_label')]"));
        String currentHighPrice = highPrice.getText().replaceAll("\\D", "");
        int priceHigh = Integer.parseInt(currentHighPrice);
        highPrice.click();
        sleep(3000);
        driver.findElement(By.xpath("//li[contains(@class,'sort_price')]")).click();
        sleep(3000);
        WebElement priceOfHotelWithMaxScore = driver.findElement(By.xpath("(//div[contains(@class,'bui-price-display__value')])[1]"));
        String currentPrice = priceOfHotelWithMaxScore.getText().replaceAll("\\D", "");
        int priceForHotel = Integer.parseInt(currentPrice);
        assertTrue(priceHigh <= priceForHotel / termOfLiving);
        sleep(2000);
        driver.findElement(By.xpath("(//div[@class = 'sr-cta-button-row'])[1]")).click();
        sleep(5000);
        Set<String> windowTabs = driver.getWindowHandles();
        for (String window : windowTabs) {
            driver.switchTo().window(window);
        }
        sleep(2000);
        WebElement selectRoom = driver.findElement(By.xpath("(//select[@class = 'hprt-nos-select'])[1]"));
        Select selectHotelRoom = new Select(selectRoom);
        selectHotelRoom.selectByValue("1");
        sleep(4000);
        driver.findElement(By.xpath("(//div[@class = 'hprt-reservation-cta'])[1]")).click();
        sleep(2000);
        driver.findElement(By.id("lastname")).sendKeys("Puertos");
        driver.findElement(By.id("email")).sendKeys("alexandro.puertos@mail.ru");
        driver.findElement(By.id("email_confirm")).sendKeys("alexandro.puertos@mail.ru");
        driver.findElement(By.xpath("//button[contains(@class,'submit_holder_button')]")).click();
        driver.findElement(By.id("phone")).sendKeys("447783598");
        WebElement selectCreditCard = driver.findElement(By.id("cc_type"));
        Select selectCC = new Select(selectCreditCard);
        selectCC.selectByVisibleText("Visa");
        sleep(1000);
        WebElement creditNumber = driver.findElement(By.id("cc_number"));
        creditNumber.sendKeys("4242424242424242");
        sleep(1000);
        WebElement termsCreditCard = driver.findElement(By.id("cc_month"));
        Select selectTermsCreditCard = new Select(termsCreditCard);
        selectTermsCreditCard.selectByValue("10");
        sleep(1000);
        driver.findElement(By.xpath("(//span[@class = 'bp_submit_button__copy'])[2]")).click();
        sleep(2000);
        WebElement alertMessageCreditCard = driver.findElement(By.xpath("(//p[@id='bp_form_cc_number_msg'])[1]"));
        System.out.println(alertMessageCreditCard.getText());
        sleep(2000);
        driver.close();
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