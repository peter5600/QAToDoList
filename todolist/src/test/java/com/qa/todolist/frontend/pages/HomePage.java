package com.qa.todolist.frontend.pages;

import java.nio.file.Paths;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	public static String URL =  Paths.get("frontEndCode/html/index.html").toFile().getAbsolutePath();
	
	@FindBy(id = "AddNewList")
	private WebElement AddNewListBTN;
}
