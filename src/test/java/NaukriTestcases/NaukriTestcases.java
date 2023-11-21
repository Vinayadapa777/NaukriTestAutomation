package NaukriTestcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.ProfilePage;
import Utilities.ActionFunctions;

public class NaukriTestcases extends ActionFunctions {
    HomePage hp;
    LoginPage lp;
    ProfilePage pp;

    @Test(priority = 1)
    public void LaunchUrl() {
	String url = prop.getProperty("url");
	launchUrl(url);
	System.out.println("Actual Url :" + url + " Expected Url : " + driver.getCurrentUrl() + "");
	Assert.assertTrue(url.contains(driver.getCurrentUrl()));
    }

    @Test(dependsOnMethods = ("LaunchUrl"), dataProvider = "userData")
    public void user_Login(String userName, String password, String user, String resume) throws InterruptedException {
	lp = new LoginPage(driver);
	hp = new HomePage(driver);
	pp = new ProfilePage(driver);
	lp.userLogin(userName, password);
	stringValidation(user, hp.userName);
	hp.clickOnViewProfile();
	pp.uploadingResume(resume);
	hp.Logout();
    }

    @DataProvider(name = "userData")
    public Object[][] userData() {
	String userName = prop.getProperty("emailid");
	String password = prop.getProperty("password");
	String user = prop.getProperty("userName");
	String fileuploadPath = System.getProperty("user.dir") + "\\InputFiles" + "\\vinayresume.exe";
	String userName1 = prop.getProperty("emailid1");
	String password1 = prop.getProperty("password1");
	String user1 = prop.getProperty("userName1");
	String fileuploadPath1 = System.getProperty("user.dir") + "\\InputFiles" + "\\rajeswariresume.exe";
	return new Object[][] { { userName, password, user, fileuploadPath },
		{ userName1, password1, user1, fileuploadPath1 } };
    }



}