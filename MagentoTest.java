import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream file = new FileInputStream("./TestData/magentodata.properties");
		Properties prop = new Properties();
		prop.load(file);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait= new WebDriverWait(driver, 60);
		driver.get(url);
		
		
		WebElement myAccountEle=driver.findElement(By.xpath("//span[text()='Account']//ancestor::a"));
		myAccountEle.click();
		
		WebElement logoutEle=driver.findElement(By.xpath("//a[text()='Forgot Your Password?']"));
		logoutEle.click();
		
		WebElement submitEle=driver.findElement(By.xpath("//span[text()='Submit']//ancestor::button"));
		submitEle.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("required")));
		
		WebElement emailEle=driver.findElement(By.id("email_address"));
		emailEle.sendKeys(username);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("valid")));
	}

}
