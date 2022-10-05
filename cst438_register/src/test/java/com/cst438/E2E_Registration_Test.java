package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.http.HttpStatus;

public class E2E_Registration_Test {
	public static final String CHROME_DRIVER_FILE_LOCATION 
                          = "/Users/bradley/Desktop/CSUMB/cst438/module5/exercise/chromedriver";
	public static final String URL = "http://localhost:3000";
	public static final String STUDENT_NAME = "jack";
	public static final String EMAIL = "jkennedy@csumb.edu";
	public static final int SLEEP_DURATION = 1000; // 1 second.

	@Test
	public void addStudent() throws Exception {

		// set the driver location and start driver
		//@formatter:off
		//
		// browser	property name 				Java Driver Class
		// -------  ------------------------    ----------------------
		// Edge 	webdriver.edge.driver 		EdgeDriver
		// FireFox 	webdriver.firefox.driver 	FirefoxDriver
		// IE 		webdriver.ie.driver 		InternetExplorerDriver
		// Chrome   webdriver.chrome.driver     ChromeDriver
		//
		//@formatter:on

		//TODO update the property name for your browser 
		System.setProperty("webdriver.chrome.driver",
                     CHROME_DRIVER_FILE_LOCATION);
		//TODO update the class ChromeDriver()  for your browser
		WebDriver driver = new ChromeDriver();
		
		try {
			WebElement we;
			
			
			driver.get(URL);
			// must have a short wait to allow time for the page to download 
			Thread.sleep(SLEEP_DURATION);

			// find and click the Add Student button on Schedule page
			we = driver.findElement(By.id("addStudentSched"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
						
			// enter a Student Name on the Add Student page
			we = driver.findElement(By.name("studentName"));
			we.sendKeys(STUDENT_NAME);
			
			// enter an email on the Add Student page
			we = driver.findElement(By.name("studentEmail"));
			we.sendKeys(EMAIL);
			
			// find and click the Add Student button on the Add Student page
			we = driver.findElement(By.id("addStudent"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			// verify the correct message
			we = driver.findElement(By.id("message"));
			String message = we.getText();
			assertEquals("Student Added!", message);
			Thread.sleep(SLEEP_DURATION);			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
			
		} finally {
			driver.quit();
		}
	}
		
}

