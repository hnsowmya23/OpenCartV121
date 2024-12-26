package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="(//*[@id='content'])//h2[1]")
	WebElement myAccount;
	
	@FindBy(xpath="(//a[contains(text(),'Logout')])[2]")
	WebElement btnLogoutFromList;
	
	public boolean isMyAccountPageDisplayed() {
		try {
			
			return (myAccount.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		btnLogoutFromList.click();
	}
	

}
