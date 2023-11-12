package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.ActionFunctions;
import Utilities.Configurations;

public class HomePage extends ActionFunctions {
    
    WebDriver driver;

    public HomePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath="//div[@class='name-wrapper']/child::div")
    public WebElement userName;
    @FindBy(xpath="//div[@class='nI-gNb-drawer__icon-img-wrapper']/child::img")
    public WebElement profileIcon;
    @FindBy(xpath="//a[@title='Logout']")
    public WebElement logoutButton;
    
    public void Logout() {
	click(profileIcon);
	click(logoutButton);
    }
}
