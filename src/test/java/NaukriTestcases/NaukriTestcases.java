package NaukriTestcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import Utilities.ActionFunctions;

public class NaukriTestcases extends ActionFunctions {
    HomePage hp;
    LoginPage lp;

    @Test(priority = 1)
    public void LaunchUrl() {
	String url = prop.getProperty("url");
	launchUrl(url);
	System.out.println("Actual Url :" + url + " Expected Url : " + driver.getCurrentUrl() + "");
	Assert.assertTrue(url.contains(driver.getCurrentUrl()));
    }

    @Test(priority = 2, dependsOnMethods = ("LaunchUrl"), dataProvider = "userCredentials")
    public void user_Login(String userName, String password, String expectedUserName) throws InterruptedException {
	lp = new LoginPage(driver);
	hp = new HomePage(driver);
	lp.userLogin(userName, password);
	Thread.sleep(3000);
	String actualuserName = hp.userName.getText();
	System.out.println("Actual Name :" + actualuserName + " Expected userName : " + expectedUserName + "");
	Assert.assertTrue(actualuserName.equalsIgnoreCase(expectedUserName));
	hp.Logout();
    }



    @DataProvider(name = "userCredentials")
    public Object[][] usercrdentials() {
	String userName = prop.getProperty("emailid");
	String password = prop.getProperty("password");
	String user = prop.getProperty("userName");
	String userName1 = prop.getProperty("emailid1");
	String password1 = prop.getProperty("password1");
	String user1 = prop.getProperty("userName1");
	return new Object[][] { { userName, password, user }, { userName1, password1, user1 }, };
    }

}