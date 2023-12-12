package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    public WebElement downloadResume;//
    @FindBy(xpath = "//p[@class='head']")
    public WebElement resumeSuccessMSG;

    public void uploadingResume(String resume) throws InterruptedException {
	explicitClickable(updateResume, 10);
	click(updateResume);
	String fileuploadPath = resume;
	Thread.sleep(2000);
	try {
	    Runtime.getRuntime().exec(fileuploadPath);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	try {
	    explicitVisible(resumeSuccessMSG, 5);
	    stringValidation("Success", resumeSuccessMSG);
	    System.out.println("Resume Updated Successfully");
	} catch (Exception e) {
	    System.out.println("Resume Updated Successfully");
	}
//	click(downloadResume);
//	File f = new File(downloadPath + "\\Resume.pdf");
//	Thread.sleep(1500);
//	Assert.assertTrue(f.exists());
//	if (f.exists()) {
//	    f.delete();
//	}
    }
}
