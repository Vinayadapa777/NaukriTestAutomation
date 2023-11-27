package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Configurations {
    public static WebDriver driver;
    public static Properties prop;
    FileInputStream fis = null;
    public String downloadPath = System.getProperty("user.dir") + "\\Downloads";
    static String dataFile = System.getProperty("user.dir") + "\\data.pro";
    static String browserName;

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
	browserName = System.getProperty("browser") != null ? System.getProperty("browser")
		: prop.getProperty("browser");
	if (browserName.contains("chrome")) {
	    ChromeOptions co = new ChromeOptions();
	    co.setAcceptInsecureCerts(true);
	    // co.addArguments("incognito");
	    Map<String, Object> prefs = new HashMap<String, Object>();
	    prefs.put("download.default_directory", downloadPath);
	    co.setExperimentalOption("prefs", prefs);
	    if (browserName.contains("headless")) {
		co.addArguments("--headless", "--window-size=1920,1080",
			"--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");

	    }
	    driver = new ChromeDriver(co);

	    if (!browserName.contains("headless")) {
		driver.manage().window().maximize();
	    } else {
		driver.manage().window().setSize(new Dimension(1920, 1080));
	    }
	} else if (browserName.contains("firefox")) {
	    FirefoxOptions fo = new FirefoxOptions();
	    if (browserName.contains("headless")) {
		fo.addArguments("--headless");
	    }
	    driver = new FirefoxDriver(fo);
	    if (!browserName.contains("headless")) {
		driver.manage().window().maximize();
	    } else {
		driver.manage().window().setSize(new Dimension(1920, 1080));
	    }
	} else if (browserName.contains("edge")) {
	    EdgeOptions eo = new EdgeOptions();
	    if (browserName.contains("headless")) {
		eo.addArguments("--headless");
	    }
	    driver = new EdgeDriver(eo);
	    if (!browserName.contains("headless")) {
		driver.manage().window().maximize();
	    } else {
		driver.manage().window().setSize(new Dimension(1920, 1080));
	    }
	} else if (browserName.contains("ie")) {
	    // InternetExplorerOptions io = new InternetExplorerOptions();
	    // As of now there is no headless mode for this IE browser
	    driver = new InternetExplorerDriver();
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	return driver;
    }

    public String screenShot(WebDriver driver, String testcase) {
	String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + testcase + ".png";
	TakesScreenshot ts = (TakesScreenshot) driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
	try {
	    FileUtils.copyFile(src, new File(destination));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return destination;
    }

    public String fullScreenShot(WebDriver driver, String testcase) {
	String currentDateAndTime = new SimpleDateFormat("yyyy-MM-dd-HH").format(new Date());
	String destination = System.getProperty("user.dir") + "\\ScreenShots\\AllScreenShots\\" +testcase+ currentDateAndTime + ".png";
	Shutterbug.shootPage(driver, Capture.FULL, true).save(destination);
	return destination;
    }

    public static ExtentReports reporter() {
	Properties prop;
	FileInputStream fis = null;
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
	String currentDateAndTime = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss").format(new Date());
	String fcurrentDateAndTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	String fileName = "index_" + fcurrentDateAndTime + ".html";
	String resultpath = System.getProperty("user.dir") + "\\TestResults\\" + fcurrentDateAndTime + "\\" + fileName;
	ExtentSparkReporter sp = new ExtentSparkReporter(resultpath);
	sp.config().setDocumentTitle(prop.getProperty("DocumentTitle"));
	sp.config().setReportName(prop.getProperty("ReportName"));
	sp.config().setTheme(Theme.DARK);
	ExtentReports ex = new ExtentReports();
	ex.attachReporter(sp);
	ex.setSystemInfo("TesterName", prop.getProperty("TesterName"));
	String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
		: prop.getProperty("browser");
	ex.setSystemInfo("Browser", browserName);
	return ex;

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
