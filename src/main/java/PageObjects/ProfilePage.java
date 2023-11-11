package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    WebDriver driver;

    public ProfilePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }
    
  
}
