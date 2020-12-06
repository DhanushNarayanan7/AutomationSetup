import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChubbSmokeTest {
	
	WebDriver driver;
	
	File realFileName ;
	TakesScreenshot scrShot;
    String TestStartDateTime;
    JavascriptExecutor js;
    String linkText;
    Properties prop = new Properties();
    
	@BeforeClass
	public void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dhanush Narayanan R\\Downloads\\chromedriver_win32\\chromedriver.exe");
		try {
			prop.load(new FileReader("C:\\Users\\Dhanush Narayanan R\\Downloads\\AutomationSetup\\AutomationSetup\\BCWS\\src\\main\\resources\\PROD.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--ignore-certificate-errors");
		chromeOptions.addArguments("--start-maximized");
		driver = new ChromeDriver(chromeOptions);
		js = (JavascriptExecutor) driver;
	    scrShot =((TakesScreenshot)driver);
		TestStartDateTime =
                ""+LocalDate.now() + "_"+LocalTime.now().getHour() +"_" +LocalTime.now().getMinute() +"_"+
                        LocalTime.now().getSecond();
	}
	
	public void createFile(String fileName) {
		try {
			realFileName = new File("Test Results Prod\\"+TestStartDateTime+"\\" +fileName+ ".png");
			FileUtils.copyFile(scrShot.getScreenshotAs(OutputType.FILE),realFileName);
			Reporter.log("<br><img src = '"+realFileName.getAbsolutePath()+"' height = '800' width = '800'/><br>");
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	
	@Test
	public void BCWSPolicyDetailsOne() throws InterruptedException {
		driver.get(prop.getProperty("Papa"));
		driver.findElement(By.xpath("//a[.='Order Online']"));
		//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		//driver.findElement(By.xpath("(((//label[text()='Search for:'])[2]/following::input)[2])[1]")).sendKeys("Myname");
		createFile("BCWS_UpdatedPolicyDetailsOne");
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}

