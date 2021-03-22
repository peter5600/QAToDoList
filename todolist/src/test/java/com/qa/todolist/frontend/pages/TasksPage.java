package com.qa.todolist.frontend.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TasksPage {
	private WebDriver driver;
	public TasksPage(WebDriver driver, HomePage hp) {
		this.hp = hp;
		this.driver = driver;
	}
	
	public static String URL = HomePage.URL;//needs to start on the same page as home page
	
	private List<WebElement> viewTasksBtns;
	
	private WebElement addTaskBtn;
	
	private HomePage hp;
	
	public boolean displayTasks() {
		hp.addList();//need a list there
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.className("ViewTasks"))); 
		viewTasksBtns = driver.findElements(By.className("ViewTasks"));
		WebElement viewTaskBtn = viewTasksBtns.get(0);
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(viewTaskBtn)); 
		viewTaskBtn.click();
		new WebDriverWait(driver, 10).until(
			      webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		//this line above waits until the page is ready
		return true;
	}
}
