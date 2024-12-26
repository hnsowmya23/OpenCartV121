package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	
	WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	
	
	@FindBy(id="input-firstname")
	WebElement txtFirstName;
	
	@FindBy(id="input-lastname")
	WebElement txtLastName;
	
	
	@FindBy(id="input-email")
	WebElement txtEmailId;
	
	@FindBy(id="input-telephone")
	WebElement textPhoneNumber;
	
	
	@FindBy(name="password")
	WebElement txtPassword;
	
	@FindBy(xpath="(//input[@type='password'])[2]")
	WebElement txtConfirmPassword;
	
	
	@FindBy(xpath="//input[@value='0']")
	WebElement radioButtonNewsLetter;
	
	
	@FindBy(name="agree")
	WebElement chkPolicy;
	
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement confirmationMsg;
	
	public void setFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);;
	}

	public void setLastName(String lastName) {
		txtLastName.sendKeys(lastName);;
	}
	
	public void setEmail(String emailId) {
		txtEmailId.sendKeys(emailId);
	}
	

	public void setPhoneNumber(String phoneNo) {
		textPhoneNumber.sendKeys(phoneNo);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String confirmPwd) {
		txtConfirmPassword.sendKeys(confirmPwd);
	}
	
	public void setNewsLetter() {
		radioButtonNewsLetter.click();
	}
	
	public void setPolicy() {
		chkPolicy.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
	}
	
	public String getConfirmationMsg() {
		try {
		return	confirmationMsg.getText();
		}
		catch(Exception e ) {
			return e.getMessage();
		}
	}
}

