import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
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
	
	//File realFileName ;
	//TakesScreenshot scrShot;
    //String TestStartDateTime;
    JavascriptExecutor js;
    String linkText;
    XSSFWorkbook wb;
    FileInputStream fin;
    File src ;
    Properties prop = new Properties();
    
	@BeforeClass
	public void startBrowser() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dhanush Narayanan R\\Downloads\\chromedriver_win32\\chromedriver.exe");
		try {
			prop.load(new FileReader("C:\\Users\\Dhanush Narayanan R\\git repository\\AutomationSetup\\AutomationSetup\\AutomationSetup\\BCWS\\src\\main\\resources\\PROD.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--ignore-certificate-errors");
		chromeOptions.addArguments("--start-maximized");
		src = new File ("C:\\TestFile.xlsx");
		fin= new FileInputStream(src);
		wb=new XSSFWorkbook(fin);
		driver = new ChromeDriver(chromeOptions);
		js = (JavascriptExecutor) driver;
	   // scrShot =((TakesScreenshot)driver);
		//TestStartDateTime =
          //      ""+LocalDate.now() + "_"+LocalTime.now().getHour() +"_" +LocalTime.now().getMinute() +"_"+
            //            LocalTime.now().getSecond();
	}
	
//	public void createFile(String fileName) {
//		try {
//			realFileName = new File("Test Results Prod\\"+TestStartDateTime+"\\" +fileName+ ".png");
//			FileUtils.copyFile(scrShot.getScreenshotAs(OutputType.FILE),realFileName);
//			Reporter.log("<br><img src = '"+realFileName.getAbsolutePath()+"' height = '800' width = '800'/><br>");
//		} catch (WebDriverException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
		
	
	@Test
	public void BCWSPolicyDetailsOne() throws InterruptedException {
		driver.get(prop.getProperty("FaceBook"));
		
		//FOR FACEBOOK PROJECT
		WebElement username=driver.findElement(By.xpath("//input[@id='email']"));
		WebElement password=driver.findElement(By.xpath("//input[@id='pass']"));
		WebElement login=driver.findElement(By.xpath("//button[@id='u_0_b']")); 
		username.sendKeys("narayanandhanushn2@gmail.com"); 
		password.sendKeys("DhanushDanDk07");
		login.click();
		Thread.sleep(10000);
		String name =driver.findElement(By.xpath("(//span[contains(@class,'a8c37x1j ni8dbmo4')])[1]")).getText();
		System.out.println(name);
		XSSFSheet sheet = wb.getSheetAt(0);
		if(name == "Dhanush") {
		sheet.getRow(0).createCell(0).setCellValue("Working Fine");
		}else {
		sheet.getRow(0).createCell(0).setCellValue("Some Error");	
		}
		//  FOR PAPA'S PROJECT
		//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		//driver.findElement(By.xpath("//a[.='Order Online']"));
		
		//driver.findElement(By.xpath("(((//label[text()='Search for:'])[2]/following::input)[2])[1]")).sendKeys("Myname");
		//createFile("BCWS_UpdatedPolicyDetailsOne");
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}

//import java.io.File;
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.io.FileHandler;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.Wait;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//
//public class chrome {
//
//	@Test
//	public void CaptureScreenShot() throws InterruptedException,IOException {
//		System.setProperty("webdriver.edge.driver", "C:\\Users\\Dhanush Narayanan R\\Downloads\\edgedriver\\msedgedriver.exe");
//		WebDriver driver= new EdgeDriver();
//		driver.get("https://www.facebook.com/");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		WebElement username=driver.findElement(By.xpath("//input[@id='email']"));
//		WebElement password=driver.findElement(By.xpath("//input[@id='pass']"));
//		WebElement login=driver.findElement(By.xpath("//button[@id='u_0_b']")); 
//		//Thread.sleep(1000);
//		username.sendKeys("narayanandhanushn2@gmail.com"); 
//		//Thread.sleep(1000);
//		//driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//		password.sendKeys("DhanushDanDk07");
//		WebDriverWait wait = new WebDriverWait(driver, 15);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='u_0_b']")));
//		//Thread.sleep(1000);
//		login.click();
//		String actualUrl="https://www.facebook.com/"; 
//		String expectedUrl= driver.getCurrentUrl(); 
//		Assert.assertEquals(expectedUrl,actualUrl);
//		//driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("AutomateMaven");
//		TakesScreenshot ts=(TakesScreenshot)driver;
//		File source=ts.getScreenshotAs(OutputType.FILE);
//		FileHandler.copy(source, new File ("./ScreenShots/facebook.png"));
//		System.out.println("Screenshot taken");
//		driver.quit();
//	}
//
//}


