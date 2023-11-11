package Utilities;

import org.openqa.selenium.WebElement;

public class ActionFunctions extends Configurations {

    public void launchUrl(String url) {
	try {
	    driver.get(url);
	} catch (Exception e) {
	    System.out.println("unable to launch the url");
	}
    }

    public Boolean click(WebElement ele) {
	boolean flag = false;
	try {
	    ele.isDisplayed();
	    ele.click();
	    flag = true;
	} catch (Exception e) {
	    System.out.println("No such element");
	    flag = false;
	}
	return flag;
    }

}
