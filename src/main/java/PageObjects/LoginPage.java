package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.ActionFunctions;

public class LoginPage extends ActionFunctions {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath="//label[text()='Email ID / Username']/following-sibling::input")
    public WebElement emailid;
    @FindBy(xpath="//label[text()='Password']/following-sibling::input")
    public WebElement password;
    @FindBy(xpath="//button[text()='Login']")
    public WebElement login;
    @FindBy(xpath="//a[@id='login_Layer']")
    public WebElement InitialLoginButton;
    
    public void userLogin(String userEmail,String Password) throws InterruptedException {
	click(InitialLoginButton);
	emailid.sendKeys(userEmail);
	password.sendKeys(Password);
	login.click();
    }

}
