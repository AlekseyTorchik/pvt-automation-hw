package task_8_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class task_8_2 {
    public static void main(String[] args) throws InterruptedException {
        double requireHotelScore = 9;
        System.setProperty("webdriver.chrome.driver", "D:\\Programs\\Setups\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.booking.com/");
        System.out.println(driver.getTitle());
        WebElement searchInput = driver.findElement(By.xpath("//input[@id = 'ss']"));
        searchInput.sendKeys("Москва");
        searchInput.submit();
        Thread.sleep(3000);
        WebElement numberOfAdults = driver.findElement(By.xpath("(//select/option[@value = '2'])[1]"));
        numberOfAdults.click();
        WebElement hotelRoom = driver.findElement(By.xpath("(//select/option[@value = '1'])[3]"));
        hotelRoom.click();
        WebElement submitButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
        submitButton.click();
        WebElement maxHotelScore = driver.findElement(By.xpath("//a[@data-id = 'review_score-90']"));
        maxHotelScore.click();
        Thread.sleep(5000);
        WebElement firstMaxHotelScore = driver.findElement(By.xpath("(//a[@class ='hotel_name_link url'])[1]"));
        firstMaxHotelScore.click();
        Thread.sleep(8000);
        WebElement scoreOfHotelOnPage = driver.findElement(By.xpath("(//div[@class = 'bui-review-score__badge'])[1]"));
        String scoreText = scoreOfHotelOnPage.getText();
        String scoreModifyForDouble = scoreText.replace(',', '.');
        double score = Double.parseDouble(scoreModifyForDouble);
        boolean compareResult = score >= requireHotelScore;
        System.out.print("Score of first hotel is more than or equal to " + requireHotelScore + " : " + compareResult);
        driver.close();
        driver.quit();
    }
}
