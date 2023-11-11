package NaukriTestcases;

import org.testng.Assert;

import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import Utilities.ActionFunctions;

public class NaukriTestcases extends ActionFunctions {
    HomePage hp;
    LoginPage lp;

    @Test
    public void LaunchUrl() {
	String url = prop.getProperty("url");
	launchUrl(url);
	System.out.println("Actual Name :" + url + " Expected userName : " + driver.getCurrentUrl() + "");
	Assert.assertEquals(url, driver.getCurrentUrl());
    }

    @Test(dependsOnMethods = ("LaunchUrl"))
    public void user_Login() throws InterruptedException {
	lp = new LoginPage(driver);
	hp = new HomePage(driver);
	String userName = prop.getProperty("emailid");
	String password = prop.getProperty("password");
	lp.userLogin(userName, password);
	String expectedUserName = prop.getProperty("userName");
	Thread.sleep(3000);
	String actualuserName = hp.userName.getText();
	System.out.println("Actual Name :" + actualuserName + " Expected userName : " + expectedUserName + "");
	//Assert.assertEquals(actualuserName, expectedUserName);
	Assert.assertTrue(actualuserName.equalsIgnoreCase(expectedUserName));
    }

}