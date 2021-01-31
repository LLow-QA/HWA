package com.qa.HWA.Selenium.POM;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.HWA.Selenium.POM.Pages.IndexPage;

public class QACoaches {

	private static RemoteWebDriver driver;


	private static Logger LOGGER = Logger.getGlobal();

	@BeforeAll
	public static void initialise() throws Exception {

		LOGGER.setLevel(Level.ALL);

		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");

	}

	@BeforeEach
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		// timeouts
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test
	public void userLogin() throws Exception {

		LOGGER.warning("Connecting to QA Coaches....");

		driver.get("http://localhost:8081/Index.html");
		Thread.sleep(2000);
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);

		website.loginNavUser();
		Thread.sleep(2000);

		LOGGER.info("Logging in as user...\n");
		website.loginPage.loginUser("aa@bb.com", "Pass1234");
		Thread.sleep(500);
		driver.switchTo().alert().accept();

		assertEquals("QA Coaches - Home", driver.getTitle());

	}

	@Test
	public void createUser() throws Exception {
		LOGGER.warning("Connecting to QA Coaches....");

		driver.get("http://localhost:8081/Index.html");
		Thread.sleep(2000);
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		
		LOGGER.info("Creating a new user...\n");
		website.signUpNavUser();
		Thread.sleep(2000);
		
		website.registerPage.registerUser("Lloyd", "Low", "20 Fal Road", "BE34 4RF", "ll@qa.com", "Admin12");
		Thread.sleep(2000);
		WebElement submitButton = driver.findElement(By.xpath("/html/body/div/div[2]/div/form/fieldset/button[1]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", submitButton);
		driver.switchTo().alert().accept();
		
		assertEquals("Login",driver.getTitle());
		
	}
	
	@Test
	public void buyTicket() throws Exception {
		
		LOGGER.warning("Connecting to QA Coaches....");

		driver.get("http://localhost:8081/Index.html");
		Thread.sleep(2000);
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		
		LOGGER.info("Logging in...\n");
		website.loginNavUser();
		Thread.sleep(2000);
		
		website.loginPage.loginUser("aa@bb.com", "Pass1234");
		Thread.sleep(500);
		driver.switchTo().alert().accept();
		
		LOGGER.info("Navigating to the Time-Table...\n");
		website.timeTableNavUser();
		

		JavascriptExecutor js = (JavascriptExecutor) driver;

		Thread.sleep(500);
		WebElement ticketBtn = driver.findElement(By.xpath("//*[@id=\"buyticket\"]"));
		js.executeScript("arguments[0].click()", ticketBtn);
		Thread.sleep(500);


		Thread.sleep(500);
		WebElement plus = driver.findElement(By.xpath("//*[@id=\"more\"]"));
		js.executeScript("arguments[0].click()",plus);
		Thread.sleep(500);
		WebElement purchaseBtn = driver.findElement(By.xpath("//*[@id=\"purchaseSubmit\"]"));
		js.executeScript("arguments[0].click()",purchaseBtn);
		Thread.sleep(500);
		WebElement checkout = driver.findElement(By.xpath("//*[@id=\"checkoutbutton\"]/button"));
		js.executeScript("arguments[0].click()",checkout);
		driver.switchTo().alert().accept();
	
		assertEquals("QA Coaches - Home", driver.getTitle());
	}
	
	@Test
	public void adminLogin() throws Exception {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		LOGGER.warning("Connecting to QA Coaches....");

		driver.get("http://localhost:8081/Index.html");
		Thread.sleep(2000);
		IndexPage website = PageFactory.initElements(driver, IndexPage.class);
		
		WebElement adminBtn = driver.findElement(By.xpath("//*[@id=\"adminmode\"]"));
		js.executeScript("arguments[0].click()", adminBtn);
		Thread.sleep(500);

		LOGGER.info("Logging in as admin...\n");
		website.adminLoginPage.loginAdmin("ll@qa.com", "Admin12");
		Thread.sleep(500);
		driver.switchTo().alert().accept();

		assertEquals("Admin Settings", driver.getTitle());
		
	}
	
	
	@AfterEach
	public void tearDown() {
		LOGGER.warning("Closing webdriver instance!");

		driver.close();

		LOGGER.info("!!! Webdriver closed successfully !!!");
	}
}
