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
	   // String printname=ele.getText()!=null?printname=ele.getText():printname=ele.getAttribute(printname);
	    System.out.println("clicked on : "+ ele);
	    flag = true;
	    
	} catch (Exception e) {
	    System.out.println("No suchElement Exception");
	    System.out.println("Unable to click on : "+ ele);
	    flag = false;
	}
	return flag;
    }
    public Boolean type(WebElement ele,String text) {
	boolean flag = false;
	try {
	    ele.isDisplayed();
	    ele.sendKeys(text);
	    System.out.println("Entered text : "+text);
	    flag = true;
	    
	} catch (Exception e) {
	    System.out.println("No suchElement Exception");
	    flag = false;
	}
	return flag;
    }

}
