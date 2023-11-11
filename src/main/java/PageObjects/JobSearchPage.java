package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class JobSearchPage {
    WebDriver driver;

    public JobSearchPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }
}
