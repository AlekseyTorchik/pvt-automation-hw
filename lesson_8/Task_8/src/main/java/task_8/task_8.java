package task_8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class task_8 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Programs\\Setups\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.booking.com/");
        System.out.println(driver.getTitle());
        WebElement searchInput = driver.findElement(By.xpath("//input[@id = 'ss']"));
        searchInput.sendKeys("Москва");
        searchInput.submit();
        Thread.sleep(3000);
        WebElement termOfLiving = driver.findElement(By.xpath("(//span[contains(text(),'18')])[1]"));
        termOfLiving.click();
        WebElement numberOfAdults = driver.findElement(By.xpath("(//select/option[@value = '2'])[1]"));
        numberOfAdults.click();
        WebElement hotelRoom = driver.findElement(By.xpath("(//select/option[@value = '1'])[3]"));
        hotelRoom.click();
        WebElement submitButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
        submitButton.click();
        WebElement hotelList = driver.findElement(By.xpath("//div[@data-block-id='hotel_list']"));
        boolean isDisplayedHotelList = hotelList.isDisplayed();
        System.out.println(isDisplayedHotelList);
        Thread.sleep(2000);
        driver.close();
        driver.quit();
    }
}
