package testCases;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void verifyLoginDDT(String email, String pwd, String exp) throws IOException {
		
		try {
		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = new LoginPage(driver);
		MyAccountPage myAccountPage= new MyAccountPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		homePage.clickMyAccount();
		homePage.clickLogin();
		loginPage.setEmail(email);
		loginPage.setPassword(pwd);
		loginPage.clickLogin();
		boolean targetPage=myAccountPage.isMyAccountPageDisplayed();
		Assert.assertTrue(targetPage);
		
		if(exp.equalsIgnoreCase("Valid")) {
			
		if(targetPage==true) {
			myAccountPage.clickLogout();
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
		}
		if(exp.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				myAccountPage.clickLogout();
			Assert.assertTrue(false);
		}
			else {
				Assert.assertTrue(true);
			}
		}
	}
		catch(Exception e){
			Assert.fail();
		}
	}

}
