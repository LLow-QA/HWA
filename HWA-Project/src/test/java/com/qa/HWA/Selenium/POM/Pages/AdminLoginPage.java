package com.qa.HWA.Selenium.POM.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage {

	public static String URL = "http://localhost:8081/adminLogin.html";
	
	
	@FindBy(xpath = "//*[@id=\"adminuser\"]")
	private WebElement adminUserIn;
	
	@FindBy(xpath = "//*[@id=\"adminpass\"]")
	private WebElement adminPassIn;
	
	@FindBy(xpath = "//*[@id=\"adminSubmit\"]")
	private WebElement submitBtn;
	
	public void loginAdmin(String username, String password) {
		
		adminUserIn.sendKeys(username);
		adminPassIn.sendKeys(password);
		submitBtn.click();
		
	}
	
}
