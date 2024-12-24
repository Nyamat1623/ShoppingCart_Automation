import java.beans.Visibility;
import java.io.IOException;

import org.testng.ITestResult;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class mainClass {

	
	static Homepage hm;
	
	static WebDriver d;
	
	static ExtentReports extent;
    static ExtentTest test;

   @BeforeClass 
	public void setUp() {
	// Setup Extent Reports with Spark Reporter
       ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
       extent = new ExtentReports();
       extent.attachReporter(sparkReporter);
       
       
		ExtentTest test=extent.createTest("ShoppingCart_Automation","This is test to validate Functionality of Website");

		System.setProperty("driver.chtome.driver", "D:/Eclipse_Works.Chromedriver.exe");
		d=new ChromeDriver();
		d.get("https://react-shopping-cart-67954.firebaseapp.com/");
		d.manage().window().maximize();
		d.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		}
	
   @BeforeMethod
   public void init(ITestResult result) {
       // Initialize ExtentTest for each test method
       String ShoppingCart_Automation = result.getMethod().getMethodName();
       test = extent.createTest(ShoppingCart_Automation); // Create a new test instance for logging
   }
   
   
   

	@AfterClass
	public void teardown() {
		if (d != null) {
            d.quit();
           
        } extent.flush();
	}

	@Test
	public void test() throws InterruptedException {
		hm=new Homepage(d);
		
		WebDriverWait wait = new WebDriverWait(d,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(hm.sizeSelection));
		
		
		test.info("Size selection button is visible.");
		
		try 
		{ hm.selectSizeBTN();
		Thread.sleep(2000);
		
		test.pass("Size selected successfully.");
		
		hm.selectp1BTN();
		test.pass("Product 1 selected successfully.");
		hm.selectp2BTN();
		test.pass("Product 2 selected successfully.");
		
		Thread.sleep(2000);
		
		String Total=hm.Totalprice();
		System.out.println("Total Price : "+Total);
		Thread.sleep(1000);
		//adding to quantity
		d.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/div[2]/div/button[2]")).click();
		String Total1=hm.Totalprice();
		System.out.println("Total Price after addition: "+Total1);
		
		hm.checkoutBTN();
		Thread.sleep(2000);
		d.switchTo().alert().accept();
		
		
		test.pass("Checkout completed successfully.");
		Thread.sleep(2000);	
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
}
}