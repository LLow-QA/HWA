package com.qa.HWA.Selenium.POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {

	public static String URL = "http://localhost:8081/Index.html";
	
	@FindBy(xpath = "//*[@id=\"navbarNavLeft\"]/ul/li[2]")
	private WebElement timeTableNav;
	
	@FindBy(xpath = "//*[@id=\"navbarNavLeft\"]/ul/li[3]")
	private WebElement contactUsNav;
	
	@FindBy(xpath = "//*[@id=\"navbarNavRight\"]/ul/li[1]")
	private WebElement myAccountNav;
	
	@FindBy(xpath = "//*[@id=\"navbarNavRight\"]/ul/li[2]")
	private WebElement signUpNav;
	
	@FindBy(xpath = "//*[@id=\"navbarNavRight\"]/ul/li[4]")
	private WebElement loginNav;
	
	@FindBy(xpath = "//*[@id=\"adminmode\"]")
	private WebElement adminLoginNav;
	
	public LoginPage loginPage;
	public RegisterPage registerPage;
	public TimeTablePage timeTablePage;
	public AdminLoginPage adminLoginPage;
	
	
	public IndexPage(WebDriver driver) {
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
		this.registerPage = PageFactory.initElements(driver, RegisterPage.class);
		this.timeTablePage = PageFactory.initElements(driver, TimeTablePage.class);
		this.adminLoginPage = PageFactory.initElements(driver, AdminLoginPage.class);
	}
	
	public void loginNavUser() {
		loginNav.click();
	}
	
	public void signUpNavUser() {
		signUpNav.click();
	}
	
	public void timeTableNavUser() {
		timeTableNav.click();
	}
	
	public void adminLoginNavUser() {
		adminLoginNav.click();
	}
}
