package com.Dunzo.driverFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class mobileDriverConfig {

	
	
	public static AndroidDriver<MobileElement> androidDriver;

	@Parameters({"deviceId","portNumber"})
	@BeforeTest
	public void driverCreation(String devId,String portNum) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("automationName", "UiAutomator2");
		cap.setCapability("udid", devId);
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11");
		cap.setCapability("noReset", "true");
		cap.setCapability("app", "C:/Users/shivprsi/Downloads/Dunzo_v3.27.0.7_apkpure.apk");
		URL url =  new URL("http://127.0.0.1:"+portNum+"/wd/hub");
		androidDriver = new AndroidDriver<>(url, cap);
		androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void tearDown() {
		androidDriver.quit();
	}
	
	
}
