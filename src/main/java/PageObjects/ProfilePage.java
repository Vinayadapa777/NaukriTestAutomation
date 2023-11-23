package PageObjects;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Utilities.ActionFunctions;

public class ProfilePage extends ActionFunctions {
    WebDriver driver;

    public ProfilePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(text(),'Resume')]/following-sibling::a")
    public WebElement updateResume;
    @FindBy(xpath = "//span[@class='icon-wrap']/child::i[contains(text(),'download')]")
    public WebElement downloadResume;

    public void uploadingResume(String resume) throws InterruptedException {
	click(updateResume);
	String fileuploadPath = resume;
	Thread.sleep(3000);
	try { 
	    Runtime.getRuntime().exec(fileuploadPath);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	click(downloadResume);
	File f = new File(downloadPath + "\\Resume.pdf");
	Thread.sleep(1500);
	Assert.assertTrue(f.exists());
	if (f.exists()) {
	    f.delete();
	}
    }
}
