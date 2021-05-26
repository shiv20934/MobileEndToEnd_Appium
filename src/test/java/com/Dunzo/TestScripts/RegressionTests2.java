package com.Dunzo.TestScripts;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.Dunzo.Pages.AddressPage;
import com.Dunzo.Pages.HomePage;
import com.Dunzo.driverFactory.mobileDriverConfig;

public class RegressionTests2 extends mobileDriverConfig {

	
	@Test
	public void updateDefaultAddress1() {
		HomePage hp = new HomePage(androidDriver);
		hp.verifyLanding();
		hp.navigateToAddressPage();
		new AddressPage(androidDriver).selectAddress("Home");
	}
	
	
}
