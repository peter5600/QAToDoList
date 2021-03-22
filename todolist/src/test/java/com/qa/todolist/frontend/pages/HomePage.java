package com.qa.todolist.frontend.pages;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	public static String URL =  Paths.get("frontEndCode/html/index.html").toFile().getAbsolutePath();
	private WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		addNewListBTN = driver.findElement(By.id("AddNewList"));
		listNameInput = driver.findElement(By.id("ListName"));
		submitNewList = driver.findElement(By.id("CreateListBtn"));
		addListModal = driver.findElement(By.id("AddListModal"));
	}

	private WebElement addNewListBTN;

	private WebElement listNameInput;

	private WebElement submitNewList;
	
	private WebElement addListModal;
	
	public int GetLists() {
		WebElement ListsBox = driver.findElement(By.id("Lists"));
		int ChildCount = ListsBox.findElements(By.xpath("./child::*")).size();
		return ChildCount;
	}
	
	public boolean addList() {
		addNewListBTN = driver.findElement(By.id("AddNewList"));
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(addNewListBTN));
		addNewListBTN.click();
		
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(addListModal));
		listNameInput.sendKeys("My new list");
		submitNewList.click();
		//i belive that this line is helping with the wait time what it is doing is waiting until the page is loaded
		new WebDriverWait(driver, 10).until(
			      webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		//until the addNewListBTN is ready this means page has at least loaded
		
		//alot of time just in case it takes a while or test pc is slow
		return true;//if any of these fail it will throw an error and wont reach this line
	}
	//passes the first 6 times but once the button is offscreen
	
	//this test works as long as there aren't too many on the screen if there are it breaks
	
	
	public boolean deleteList() {
		addList();//there might not be a list to delete
		List<WebElement> deleteBtns = driver.findElements(By.className("DeleteList"));
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(deleteBtns.get(0)));
		if(deleteBtns.size() == 0) {
			return false;
		}
		
		WebElement deleteBtn = deleteBtns.get(0);
		System.out.println(deleteBtn);
		new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(deleteBtn));//wait for it to be in view
		deleteBtn.click();//submit instead of click
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		return true;//could check that child count has changed
	}
	
	
}
