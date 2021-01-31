package com.qa.HWA.Selenium.POM.Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage {

	
public static String URL = "http://localhost:8081/Signup.html";
	
	@FindBy(xpath = "//*[@id=\"firstName1\"]")
	private WebElement fnameIn;
	
	@FindBy(xpath = "//*[@id=\"lastName1\"]")
	private WebElement lnameIn;
	
	@FindBy(xpath = "//*[@id=\"address1\"]")
	private WebElement addressIn;
	
	@FindBy(xpath = "//*[@id=\"postcode1\"]")
	private WebElement postcodeIn;
	
	@FindBy(xpath = "//*[@id=\"email1\"]")
	private WebElement emailIn;
	
	@FindBy(xpath = "//*[@id=\"password1\"]")
	private WebElement passwordIn;
	
	@FindBy(xpath = "//*[@id=\"Check1\"]")
	private WebElement checkIn;
	
	
	public void registerUser(String fname, String lname, String address, String postcode, String email, String password) {
		fnameIn.sendKeys(fname);
		lnameIn.sendKeys(lname);
		addressIn.sendKeys(address);
		postcodeIn.sendKeys(postcode);
		emailIn.sendKeys(email);
		passwordIn.sendKeys(password);
		checkIn.click();
		
	}
	
	
}
