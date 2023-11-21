package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.ActionFunctions;

public class HomePage extends ActionFunctions {

    WebDriver driver;

    public HomePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='name-wrapper']/child::div")
    public WebElement userName;
    @FindBy(xpath = "//div[@class='nI-gNb-drawer__bars']")
    public WebElement logoutHamburger;
    @FindBy(xpath = "//a[@class='nI-gNb-list-cta']/child::span[contains(@class,'logout')]")
    public WebElement logoutButton;
    @FindBy(xpath = "//div[@class='view-profile-wrapper']/child::a")
    public WebElement viewProfile;

    public void Logout() throws InterruptedException {
	click(logoutHamburger);
	Thread.sleep(2000); 
	click(logoutButton);
    }

    public void clickOnViewProfile() throws InterruptedException{
	Thread.sleep(2000);
	click(viewProfile);
    }

}
