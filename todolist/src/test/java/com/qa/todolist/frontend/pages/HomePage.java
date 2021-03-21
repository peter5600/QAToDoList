package com.qa.todolist.frontend.pages;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
		new WebDriverWait(driver, 2).until(ExpectedConditions.presenceOfElementLocated(By.id(addNewListBTN.getAttribute("id"))));
		addNewListBTN.click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id(addListModal.getAttribute("id"))));
		listNameInput.sendKeys("My new list");
		submitNewList.click();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);//wait for refresh and to reload the js fetch
		return true;//if any of these fail it will throw an error and wont reach this line
	}
	
	
}
