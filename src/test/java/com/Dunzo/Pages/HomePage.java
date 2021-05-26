package com.Dunzo.Pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {

	private AndroidDriver<MobileElement> androidDriver;
	WebDriverWait wait;
	
	public HomePage(AndroidDriver<MobileElement> androidDriver) {
		this.androidDriver = androidDriver;
		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
		wait = new WebDriverWait(androidDriver, 20);
	}
	
	@AndroidFindBy(id="com.dunzo.user:id/deliverToAddress")
	private MobileElement selectedAddressLoc;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Use current location']")
	private MobileElement useCurrentLocation_label;
	
	
	public void verifyLanding() {
		assertTrue(wait.until(ExpectedConditions.visibilityOf(selectedAddressLoc)).isDisplayed(),"Home page landing validation failed");
	}
	
	public void navigateToAddressPage() {
		wait.until(ExpectedConditions.visibilityOf(selectedAddressLoc)).click();
		assertTrue(wait.until(ExpectedConditions.visibilityOf(useCurrentLocation_label)).isDisplayed(), "Address selection page did not load");
	}

}
