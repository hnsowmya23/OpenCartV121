package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;


public class TC002_LoginTest extends BaseClass{
	
	
	@Test(groups={"Sanity", "Master"})
	public void verifyLogin() throws IOException {
		
//		logger.info("Testcase started");
		
		try {
		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = new LoginPage(driver);
		MyAccountPage myAccountPage= new MyAccountPage(driver);
		
		p = new Properties();
		FileInputStream file= new FileInputStream("./src//test//resources//config.properties");
		p.load(file);
		
		
		homePage.clickMyAccount();
		homePage.clickLogin();
		loginPage.setEmail(p.getProperty("email"));
		loginPage.setPassword(p.getProperty("password"));
		loginPage.clickLogin();
		boolean targetPage=myAccountPage.isMyAccountPageDisplayed();
		Assert.assertTrue(targetPage);
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		
	}
	
	

}
