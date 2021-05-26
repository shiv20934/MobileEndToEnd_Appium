package com.Dunzo.Pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AddressPage {
	private AndroidDriver<MobileElement> androidDriver;
	WebDriverWait wait;
	
	public AddressPage(AndroidDriver<MobileElement> androidDriver) {
		this.androidDriver = androidDriver;
		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
		wait = new WebDriverWait(androidDriver, 20);
	}
	
	@iOSXCUITFindBy(id="com.dunzo.IOS:id/deliverToAddress")
	@AndroidFindBy(id="com.dunzo.user:id/deliverToAddress")
	private MobileElement selectedAddressLoc;
	
	
	public void selectAddress(String addressName) {
		wait.until(ExpectedConditions.visibilityOf(androidDriver.findElement(By.xpath("//android.widget.TextView[@text='"+addressName+"']")))).click();
		wait.until(ExpectedConditions.visibilityOf(selectedAddressLoc));
		assertTrue(selectedAddressLoc.getText().equalsIgnoreCase("HOME"),"Home address not selected");
	}
}
