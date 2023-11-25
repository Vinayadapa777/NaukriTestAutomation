package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ActionFunctions extends Configurations {

    public void launchUrl(String url) {
	try {
	    driver.get(url);
	    screenCapture(driver);
	} catch (Exception e) {
	    System.out.println("unable to launch the url");
	    screenCapture(driver);
	}
    }

    public boolean click(WebElement ele) {
	boolean flag = false;
	try {
	    ele.isDisplayed();
	    System.out.println("clicked on : " + ele.getText());
	    ele.click();
	    screenCapture(driver);
	    flag = true;
	} catch (Exception e) {
	    System.out.println("No suchElement Exception");
	    flag = false;
	    screenCapture(driver);
	}
	return flag;
    }

    public Boolean enter(WebElement ele, String text) {
	boolean flag = false;
	try {
	    ele.isDisplayed();
	    System.out.println("Entered text : " + text);
	    ele.sendKeys(text);
	    flag = true;
	    screenCapture(driver);
	} catch (Exception e) {
	    System.out.println("No suchElement Exception");
	    flag = false;
	    screenCapture(driver);
	}
	return flag;
    }

    public boolean stringValidation(String expected, WebElement ele) {
	boolean flag = false;
	try {
	    String expectedString = expected.toLowerCase();
	    Thread.sleep(3000);
	    String actualString = ele.getText().toLowerCase();
	    System.out.println("Actual Name :" + actualString + " Expected userName : " + expectedString + "");
	    Assert.assertTrue(actualString.contains(expectedString));
	    flag = true;
	} catch (Exception e) {
	    flag = false;
	}
	return flag;
    }

    public void screenCapture(WebDriver driver) {
	String currentDateAndTime = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss").format(new Date());
	String fcurrentDateAndTime = new SimpleDateFormat("yyyy-MM-dd-HH_mm").format(new Date());
	String fileName = "ScreenShots" + fcurrentDateAndTime;
	String destination = System.getProperty("user.dir") + "\\ScreenShots\\AllScreenShots\\" + fileName + "\\"
		+ currentDateAndTime + ".png";
	TakesScreenshot ts = (TakesScreenshot) driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
	try {
	    FileUtils.copyFile(src, new File(destination));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public String decodeBase64(String encodedString) {
	byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
	return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    public void explicitvisible(WebElement ele, long time) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void explicitClickable(WebElement ele, long time) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	wait.until(ExpectedConditions.elementToBeClickable(ele));
    }
}
