package com.qa.todolist.frontend.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.qa.todolist.frontend.helper.ScreenshotHelper;
import com.qa.todolist.frontend.pages.HomePage;
import com.qa.todolist.frontend.pages.TasksPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FrontEndTasksPageSeleniumTest {

	private static WebDriver driver;
	
	//reports
		private static ExtentReports extent;
		private static ExtentTest test;
		
		//Get page
		private static TasksPage TP;
		private static HomePage HP;
		
		@BeforeEach
		public void init() {
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			try {
				System.out.println(HomePage.URL);
				driver.get(HomePage.URL);
				HP = new HomePage(driver);
				 TP = new TasksPage(driver, HP);
			} catch (TimeoutException e) {
				System.out.println("Page: " + HomePage.URL + " did not load within 30 seconds!");
			}
			//try to load the page within 30 seconds
		}
		
		@BeforeAll //each test
		public static void setup() {
			extent = new ExtentReports("src/test/resources/reports/tasksReport.html", true);
			
			//setup the chrome driver
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
	        ChromeOptions cOptions = new ChromeOptions();
	        cOptions.setHeadless(false);//wanna set it for now 
	        //disable cookies 
	        cOptions.setCapability("profile.default_content_setting_values.cookies",2);
	        cOptions.setCapability("network.cookie.cookieBehavior", 2);
	        cOptions.setCapability("profile.block_third_party_cookies", true);
	        driver = new ChromeDriver(cOptions);
	        driver.manage().window().maximize();
		}
		
		@Test
		public void displayTasksTest() throws Exception {
			test = extent.startTest("Display task test");
			
			boolean TasksFound= TP.displayTasks();//wont be returned if theres an error
			assertThat(TasksFound).isTrue();
			ScreenshotHelper.screenShot(driver, "src/test/resources/screenshots/Tasks.png");
			
			test.log(LogStatus.PASS, test.addScreenCapture(Paths.get("src/test/resources/screenshots/Tasks.png").toFile().getAbsolutePath()));
			extent.endTest(test);
		}
		
		@Test
		public void addTaskTest() throws Exception {
			test = extent.startTest("add task test");
			boolean taskWasAdded = TP.addTask();
			
			assertThat(taskWasAdded).isTrue();
			ScreenshotHelper.screenShot(driver, "src/test/resources/screenshots/addedTask.png");
			
			test.log(LogStatus.PASS, test.addScreenCapture(Paths.get("src/test/resources/screenshots/addedTask.png").toFile().getAbsolutePath()));
			extent.endTest(test);
		}
		
		@Test
		public void modifyTaskTest() throws Exception {
			test = extent.startTest("modify task test");
			boolean taskWasChanged = TP.modifyTask();
			
			assertThat(taskWasChanged).isTrue();
			ScreenshotHelper.screenShot(driver, "src/test/resources/screenshots/changedTask.png");
			
			test.log(LogStatus.PASS, test.addScreenCapture(Paths.get("src/test/resources/screenshots/changedTask.png").toFile().getAbsolutePath()));
			extent.endTest(test);
		}
		
		@Test
		public void deleteTaskTest() throws Exception{
			test = extent.startTest("delete task test");
			boolean taskWasDeleted = TP.deleteTask();
			assertThat(taskWasDeleted).isEqualTo(true);
			
			ScreenshotHelper.screenShot(driver, "src/test/resources/screenshots/deletedTask.png");
			
			test.log(LogStatus.PASS, test.addScreenCapture(Paths.get("src/test/resources/screenshots/deletedTask.png").toFile().getAbsolutePath()));
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
