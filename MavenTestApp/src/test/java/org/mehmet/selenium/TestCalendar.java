package org.mehmet.selenium;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCalendar {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static WebElement element;
	
	public static int getCuttentDay(){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.DAY_OF_MONTH;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 5);
		driver.get("http://demos.smartgift.it/community/index.php/linen-blazer-610.html");
		driver.manage().window().maximize();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
		driver.findElement(By.name("add_as_gift")).click();
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nxtBtn")));
		element.click();
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recipientFirstName")));
		element.sendKeys("toName");
		driver.findElement(By.id("recipientLastName")).sendKeys("toLastName");
		driver.findElement(By.id("recipientEmail")).sendKeys("mehmet@giftjay.com");
		
		driver.findElement(By.id("senderFirstName")).sendKeys("fromLastName");
		driver.findElement(By.id("senderLastName")).sendKeys("fromLastName");
		driver.findElement(By.id("senderEmail")).sendKeys("mehmet@giftjay.com");
		
		driver.findElement(By.xpath("//section[@id='smartgift-info']/article[2]/div[2]/label[2]")).click();
		
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deliveryDate")));
		element.click();
		
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#smartgift_page2 > div.smartgift-header > div.link-close-wrapper > div.link-close")).click();
	}

	/**
	 * Select past date
	 * Expected behavior is warning message
	 * "Select a date up to 15 days in the future"
	 */
	@Test
	public void case1() {
		int day = getCuttentDay() - 1;
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("" + day)));
		element.click();
		driver.findElement(By.cssSelector("input.calendar-cancel"));
		driver.findElement(By.cssSelector("#smartgift_page2 > div.smartgift-footer > div.f-button > #nxtBtn")).click();
		try {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-flash-2")));
			System.err.println("Warning message is there");
		} catch (org.openqa.selenium.TimeoutException e) {
			System.err.println("Warning message is not there");
		}
	}
	
	/**
	 * Select current date
	 * Expected behavior is warning message
	 * "Select a date up to 15 days in the future"
	 */
	@Test
	public void case2() {
		int day = getCuttentDay();
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("" + day)));
		element.click();
		driver.findElement(By.cssSelector("input.calendar-cancel"));
		driver.findElement(By.cssSelector("#smartgift_page2 > div.smartgift-footer > div.f-button > #nxtBtn")).click();
		try {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-flash-2")));
			System.err.println("Warning message is there");
		} catch (org.openqa.selenium.TimeoutException e) {
			System.err.println("Warning message is not there");
		}		
	}
	
	/**
	 * Select a date within range
	 * Expected behavior: going to review page
	 */
	@Test
	public void case3() {
		int day = getCuttentDay() + 1;
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("" + day)));
		element.click();
		driver.findElement(By.cssSelector("input.calendar-cancel"));
		driver.findElement(By.cssSelector("#smartgift_page2 > div.smartgift-footer > div.f-button > #nxtBtn")).click();
		try {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-flash-2")));
			System.err.println("Warning message is there");
		} catch (org.openqa.selenium.TimeoutException e) {
			System.err.println("Warning message is not there");
		}	
	}
	
	/**
	 * Select a date 15 days after current date
	 * Expected behavior: warning message
	 * "Select a date up to 15 days in the future"
	 */
	@Test
	public void case4() {
		int day = getCuttentDay() + 15;
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("" + day)));
		element.click();
		driver.findElement(By.cssSelector("input.calendar-cancel"));
		driver.findElement(By.cssSelector("#smartgift_page2 > div.smartgift-footer > div.f-button > #nxtBtn")).click();
		try {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-flash-2")));
			System.err.println("Warning message is there");
		} catch (org.openqa.selenium.TimeoutException e) {
			System.err.println("Warning message is not there");
		}	
	}
	
	/**
	 * Select a date out of range
	 * Expected behavior: warning message
	 * "Select a date up to 15 days in the future"
	 */
	@Test
	public void case5() {
		int day = getCuttentDay() + 16;
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("" + day)));
		element.click();
		driver.findElement(By.cssSelector("input.calendar-cancel"));
		driver.findElement(By.cssSelector("#smartgift_page2 > div.smartgift-footer > div.f-button > #nxtBtn")).click();
		try {
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error-flash-2")));
			System.err.println("Warning message is there");
		} catch (org.openqa.selenium.TimeoutException e) {
			System.err.println("Warning message is not there");
		}	
	}
	
}
