package com.Dunzo.driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class webDriverConfig {

	@BeforeTest
	public void createWebDriver() {
		//create web driver
		System.getProperty("webdriver.chrome.driver", "");
		WebDriver driver = new ChromeDriver();
		
	}
}
