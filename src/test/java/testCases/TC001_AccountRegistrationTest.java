package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {	
	
	
	@Test(groups={"Regression", "Master"})
	public void verifyAccountRegistration() {
		
//		logger.info("****Starting****************");
		
		
		try {
		
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);
		accountRegistrationPage.setFirstName(randomStringGenerate());
		accountRegistrationPage.setLastName(randomStringGenerate());
		accountRegistrationPage.setEmail(randomStringGenerate() + "@gmail.com");
		
		accountRegistrationPage.setPhoneNumber(randomNumericGenerate());
		
		String password = randomAlphaNumericGenerate();
		accountRegistrationPage.setPassword(password);
		accountRegistrationPage.setConfirmPassword(password);
		accountRegistrationPage.setPolicy();
		accountRegistrationPage.clickContinue();
		
		String confirmmsg=accountRegistrationPage.getConfirmationMsg();
		Assert.assertEquals(confirmmsg, "Your Account Has Been Created!");
//		logger.info("validated expected message");
		}
		
		catch(Exception e) {
			logger.error("test failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
		
		
	}
	

	

}
