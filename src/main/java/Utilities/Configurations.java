package Utilities;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;



public class Configurations {
    public static WebDriver driver;
    public Properties prop;
    FileInputStream fis = null;
    public String downloadPath = System.getProperty("user.dir") + "\\Downloads";
    String dataFile = System.getProperty("user.dir") + "\\data.pro";

    public WebDriver openBrowser() {
	try {
	    fis = new FileInputStream(dataFile);
	} catch (FileNotFoundException e) {
	    System.out.println("File not found");
	    e.printStackTrace();
	}
	prop = new Properties();
	try {
	    prop.load(fis);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
		: prop.getProperty("browser");
	if (browserName.contains("chrome")) {
	    ChromeOptions co = new ChromeOptions();
	    co.setAcceptInsecureCerts(true);
	    // co.addArguments("incognito");
	    Map<String, Object> prefs = new HashMap<String, Object>();
	    prefs.put("download.default_directory", downloadPath);
	    co.setExperimentalOption("prefs", prefs);
	    if (browserName.contains("headless")) {
		co.addArguments("--headless");
		co.addArguments("headless");

	    }
	    driver = new ChromeDriver(co);
	} else if (browserName.contains("firefox")) {
	    FirefoxOptions fo = new FirefoxOptions();
	    if (browserName.contains("headless")) {
		fo.addArguments("--headless");
	    }
	    driver = new FirefoxDriver(fo);

	} else if (browserName.contains("edge")) {
	    EdgeOptions eo = new EdgeOptions();
	    if (browserName.contains("headless")) {
		eo.addArguments("--headless");
	    }
	    driver = new EdgeDriver(eo);

	} else if (browserName.contains("ie")) {
	    // InternetExplorerOptions io = new InternetExplorerOptions();
	    // As of now there is no headless mode for this IE browser
	    driver = new InternetExplorerDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//	Dimension ds = new Dimension(1920, 1080);
//	driver.manage().window().setSize(ds);
	return driver;
    }
    public void getScreenshot(String test) {
	TakesScreenshot ts = (TakesScreenshot) driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
	String destination = System.getProperty("user.dir") + "\\Captures"+test+".jpg";
	try {
	    FileUtils.copyFile(src, new File(destination));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
}
    @BeforeTest
    public void launchBrowser() {
	driver = openBrowser();
    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
	Thread.sleep(5000);
	driver.quit();
    }

}
