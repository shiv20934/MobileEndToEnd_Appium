package com.Dunzo.Utils;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Dunzo.driverFactory.mobileDriverConfig;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CommonFunc{
	WebDriverWait wait;
	AndroidDriver<MobileElement> driver;
	
	public CommonFunc() {
		driver = mobileDriverConfig.androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, 30);
	}
	
	public boolean scrollToText(String Text, MobileElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			((AndroidElement)element).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).setMaxSearchSwipes("+10+").scrollIntoView("
					+ "new UiSelector().textContains(\""+Text+"\"));");
			return true;
		
		}
		catch(Exception e) {			
			e.printStackTrace();
			System.out.println("Element not visible");
			return false;
		}
	}
	
	public void scrollByLocation(MobileElement startElem, MobileElement endElem)  {
		wait.until(ExpectedConditions.visibilityOf(startElem));
		wait.until(ExpectedConditions.visibilityOf(endElem));
		int startX = startElem.getLocation().getX() + (startElem.getSize().getWidth() / 2);
		int startY = startElem.getLocation().getY() + (startElem.getSize().getHeight() / 2);

		int endX = endElem.getLocation().getX() + (endElem.getSize().getWidth() / 2);
		int endY = endElem.getLocation().getY() + (endElem.getSize().getHeight() / 2);
		
		new TouchAction(driver).press(point(startX, startY)).waitAction(waitOptions(ofMillis(1000)))
				.moveTo(point(endX, endY)).release().perform();

	}
	
	
	public boolean swipe(String direction,MobileElement startElem,MobileElement endElem) {
		wait.until(ExpectedConditions.visibilityOf(startElem));
		wait.until(ExpectedConditions.visibilityOf(endElem));
	    int startX = startElem.getLocation().getX() + (startElem.getSize().getWidth() / 2);
	    int endX = endElem.getLocation().getX() + (endElem.getSize().getWidth() / 2);
	    int startY = startElem.getLocation().getY() + (startElem.getSize().getHeight() / 2);
	    int endY =	endElem.getLocation().getY() + (endElem.getSize().getHeight() / 2);

	    try {
	    if(direction.equalsIgnoreCase("RIGHT")||direction.equalsIgnoreCase("LEFT")) {
	    	new TouchAction(driver).press(point(startX, startY)).waitAction(waitOptions(ofMillis(1000)))
			.moveTo(point(endX, startY)).release().perform();
	    	return true;
	    }
	    else  if(direction.equalsIgnoreCase("UP")||direction.equalsIgnoreCase("DOWN")) {
	    	new TouchAction(driver).press(point(startX, startY)).waitAction(waitOptions(ofMillis(1000)))
			.moveTo(point(endX, endY)).release().perform();
	    	return true;
	    }
	    else {
	    	System.out.println("Wrong direction sent");
	    	return false;
	    }
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    	return false;
	    	
	    }
	}
	
	public void scrollByWindowSize(String direction) {
		Dimension dimensions = driver.manage().window().getSize();
		int x1 = dimensions.getWidth() / 2;
		
		if(direction.equalsIgnoreCase("Down")) {
			int Startpoint = (int) (dimensions.getHeight() * 0.7);
			int scrollEnd = (int) (dimensions.getHeight() * 0.2);
			new TouchAction(driver).press(point(x1, Startpoint)).waitAction(waitOptions(ofMillis(1000)))
			.moveTo(point(x1, scrollEnd)).release().perform();
		}
		else if(direction.equalsIgnoreCase("Up")) {
			int Startpoint = (int) (dimensions.getHeight() * 0.2);
			int scrollEnd = (int) (dimensions.getHeight() * 0.7);
			new TouchAction(driver).press(point(x1, Startpoint)).waitAction(waitOptions(ofMillis(1000)))
			.moveTo(point(x1, scrollEnd)).release().perform();
		}
		
	}
	
	public void capturePhoto(String deviceId) throws IOException {
		//method 1
		Runtime.getRuntime().exec("adb -s "+deviceId+" shell input keyevent 'KEYCODE_VOLUME_DOWN'");
		
		//method 2
		 driver.pressKey(new KeyEvent(AndroidKey.CAMERA));
	}
}
