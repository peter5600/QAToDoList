package com.qa.todolist.frontend.tests;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.todolist.frontend.helper.ScreenshotHelper;
import com.qa.todolist.frontend.pages.HomePage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FrontEndHomePageSeleniumTest {

	private static WebDriver driver;
	
	//reports
	private static ExtentReports extent;
	private static ExtentTest test;
	
	//Get page
	private static HomePage HP;
	
	@BeforeEach
	public void init() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		try {
			System.out.println(HomePage.URL);
			driver.get(HomePage.URL);
			 HP = new HomePage(driver);
		} catch (TimeoutException e) {
			System.out.println("Page: " + HomePage.URL + " did not load within 30 seconds!");
		}
		//try to load the page within 30 seconds
	}
	
	@BeforeAll //each test
	public static void setup() {
		extent = new ExtentReports("src/test/resources/reports/report1.html", true);
		
		//setup the chrome driver
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions cOptions = new ChromeOptions();
        cOptions.setHeadless(false);//wanna set it for now 
        //disable cookies 
        cOptions.setCapability("profile.default_content_setting_values.cookies",2);
        cOptions.setCapability("network.cookie.cookieBehavior", 2);
        cOptions.setCapability("profile.block_third_party_cookies", true);
        driver = new ChromeDriver(cOptions);
        driver.manage().window().setSize(new Dimension(1920, 1080));
	}
	
	@Test
	public void getListsTest() throws Exception {
		test = extent.startTest("Get lists test");
		new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(By.id("Lists")));
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		//give 5 seconds to js to fetch the results from the api shouldn't take any longer
		
		//screenshot
		ScreenshotHelper.screenShot(driver, "src/test/resources/screenshots/LoadedLists.png");
		
		int ChildCount = HP.GetLists();
		//by default listsbox is empty if no lists are found then a h2 is placed inside saying no lists are found and if 
		//lists are found then they are placed inside so to pass the contents must be greater than 1
		assertThat(ChildCount).isGreaterThan(0);
		
		test.log(LogStatus.PASS, test.addScreenCapture(Paths.get("src/test/resources/screenshots/LoadedLists.png").toFile().getAbsolutePath()));
		extent.endTest(test);
	}
	
	@Test
	public void addListTest() throws Exception {
		test = extent.startTest("Add list test");

		boolean addedList = HP.addList();
		ScreenshotHelper.screenShot(driver, "src/test/resources/screenshots/NewListAdded.png");
		assertThat(addedList).isEqualTo(true);
		test.log(LogStatus.PASS, test.addScreenCapture(Paths.get("src/test/resources/screenshots/NewListAdded.png").toFile().getAbsolutePath()));
		extent.endTest(test);
		
	}
	
	@AfterAll //runs once
	public static void teardown() {
		driver.quit();
		//end the test and delete the resources
		extent.flush();
		extent.close();
	}
}
