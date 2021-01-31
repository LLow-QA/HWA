package com.qa.HWA.Selenium.POM.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	public static String URL = "http://localhost:8081/login.html";
	
	
	@FindBy(xpath = "//*[@id=\"username1\"]")
	private WebElement userInput;
	
	@FindBy(xpath = "//*[@id=\"password1\"]")
	private WebElement passwordInput;
	
	@FindBy(xpath = "//*[@id=\"loginSubmit\"]")
	private WebElement submitButton;
	
	public void loginUser(String username, String password) {
		
		userInput.sendKeys(username);
		passwordInput.sendKeys(password);
		submitButton.click();
		
	}
	
	
}
