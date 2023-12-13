package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

public class ActionFunctions extends Configurations {

    // to launch the URL
    public void launchUrl(String url) {
	try {
	    driver.get(url);
	    screenCaptureFull(driver);
	    System.out.println("URL hit successfully");
	} catch (Exception e) {
	    screenCaptureFull(driver);
	    System.out.println("Unable to launch the url");
	}
    }

    // Performing the click action
    public boolean click(WebElement ele) {
	try {
	    ele.isDisplayed();
	    System.out.println("clicked on :" + ele);
	    ele.click();
	    screenCaptureFull(driver);
	    return true;
	} catch (Exception e) {
	    System.out.println("NoSuchElement Exception/ Unable to click on the element");
	    screenCaptureFull(driver);
	    return false;
	}
    }

    // performing click action using JavaScript
    public static boolean jsClick(WebElement ele) {
	try {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click()", ele);
	    System.out.println("Perform js click on :" + ele);
	    return true;
	} catch (Exception e) {
	    System.out.println("Unable to perform the jsClick");
	    return false;
	}
    }

    // performing the click action using mouse
    public boolean mouseClick(WebElement ele) {
	try {
	    Actions act = new Actions(driver);
	    ele.isDisplayed();
	    System.out.println("clicked by mouseAction on :" + ele);
	    act.moveToElement(ele).click().perform();
	    screenCaptureFull(driver);
	    return true;
	} catch (Exception e) {
	    System.out.println("NoSuchElement Exception/Unable to click by using mouse Action");
	    screenCaptureFull(driver);
	    return false;
	}
    }

    // performing the mouseHoverusing Actions Class
    public static boolean mouseHover(WebElement element) {
	Actions act = new Actions(driver);
	try {
	    act.moveToElement(element).perform();
	    return true;
	} catch (Exception e) {
	    System.out.println("Unable to perform mouse click`");
	    return false;
	}
    }

    // performing the rightClick using mouse
    public boolean contextClick(WebElement ele) {
	try {
	    Actions act = new Actions(driver);
	    ele.isDisplayed();
	    System.out.println("Righr click on :" + ele);
	    act.contextClick(ele).perform();
	    screenCaptureFull(driver);
	    return true;
	} catch (Exception e) {
	    System.out.println("Unable to perform right click");
	    screenCaptureFull(driver);
	    return false;
	}
    }

    // entering the text in a textBox
    public boolean enterText(WebElement ele, String text) {
	try {
	    ele.isDisplayed();
	    ele.sendKeys(text);
	    System.out.println("Entered text :" + text);
	    screenCaptureFull(driver);
	    return true;
	} catch (Exception e) {
	    System.out.println("NoSuchElement Exception");
	    screenCaptureFull(driver);
	    return false;
	}
    }

    public static boolean jsEnterText(WebElement ele, String text) {
	try {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].value='" + text + "'", ele);
	    System.out.println("Enter text using JavaScript :" + ele);
	    return true;
	} catch (Exception e) {
	    System.out.println("Unable to enter text through JavaScript");
	    return false;
	}
    }

    public static boolean isDisplayed(WebElement element) {
	try {
	    element.isDisplayed();
	    return true;
	} catch (Exception e) {
	    System.out.println(element + "element is not displayed");
	    return false;
	}
    }

    public static boolean isSelected(WebElement element) {
	try {
	    element.isSelected();
	    return true;
	} catch (Exception e) {
	    System.out.println(element + "element is not selected");
	    return false;
	}
    }

    public static boolean isEnabled(WebElement element) {
	try {
	    element.isEnabled();
	    return true;
	} catch (Exception e) {
	    System.out.println(element + "element is not Enabled");
	    return false;
	}
    }

    public static String getCurrentUrl() {
	boolean flag = false;
	String url = null;
	try {
	    url = driver.getCurrentUrl();
	    flag = true;
	} catch (Exception e) {
	    flag = false;
	} finally {
	    if (!flag) {
		System.out.println("unable to get the current url");
	    }
	}
	return url;
    }

    public static String getText(WebElement element) {
	try {
	    String text = element.getText();
	    return text;
	} catch (Exception e) {
	    System.out.println("Unable to getText");
	    return null;
	}

    }

    public static String getTitle() {
	boolean flag = false;
	String title = null;
	try {
	    title = driver.getTitle();
	    flag = true;
	} catch (Exception e) {
	    flag = false;
	} finally {
	    if (!flag) {
		System.out.println("unable to get the Title");
	    }
	}
	return title;
    }

    public static boolean switchToFrameById(int id) {
	try {
	    driver.switchTo().frame(id);
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    public static boolean switchToFrameByName(String name) {
	try {
	    driver.switchTo().frame(name);
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    public static boolean switchToFrameByElement(WebElement element) {
	try {
	    driver.switchTo().frame(element);
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    public static boolean swithToDefaultFrame() {
	try {
	    driver.switchTo().defaultContent();
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    // Below function is used to scroll the view into the
    public boolean scrollIntoView(WebElement element) {
	try {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    return true;
	} catch (Exception e) {
	    System.out.println("unable to scrollIntoView ");
	    return false;
	}
    }

    public static boolean scrollToTop() {
	try {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scorllTo(0,0)");
	    return true;
	} catch (Exception e) {
	    System.out.println("unable to scroll to Top of the page");
	    return false;
	}
    }

    public static boolean scrollToBottom() {
	try {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scorllTo(0,document.body.scrollHeight)");
	    return true;
	} catch (Exception e) {
	    System.out.println("unable to scroll to bottom of the page");
	    return false;
	}
    }

    public static boolean selectByValue(WebElement element, String value) {
	try {
	    Select sc = new Select(element);
	    sc.selectByValue(value);
	    return true;
	} catch (Exception e) {
	    System.out.println("unable to select by value");
	    return false;
	}
    }

    public static boolean selectByVisibleText(WebElement element, String text) {
	try {
	    Select sc = new Select(element);
	    sc.selectByVisibleText(text);
	    return true;
	} catch (Exception e) {
	    System.out.println("unable to select by visibleText");
	    return false;
	}
    }

    public static boolean selectByIndex(WebElement element, int index) {
	try {
	    Select sc = new Select(element);
	    sc.selectByIndex(index);
	    return true;
	} catch (Exception e) {
	    System.out.println("unable to select by index");
	    return false;
	}
    }

    public static boolean openNewTab() {
	try {
	    driver.switchTo().newWindow(WindowType.TAB);
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    public static boolean openNewWindow() {
	try {
	    driver.switchTo().newWindow(WindowType.WINDOW);
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    public static boolean dragAndDrop(WebElement src, WebElement target) {

	try {
	    Actions act = new Actions(driver);
	    act.dragAndDrop(src, target);
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    public static boolean isAlertPresent() {
	try {
	    driver.switchTo().alert();
	    return true;
	} catch (NoAlertPresentException e) {
	    System.out.println("No Alert is present");
	    return false;
	}
    }

    public static boolean acceptAlert() {
	try {
	    driver.switchTo().alert().accept();
	    return true;
	} catch (NoAlertPresentException e) {
	    System.out.println("Unable to accept the Alert");
	    return false;
	}
    }

    public static boolean dismissAlert() {
	try {
	    driver.switchTo().alert().dismiss();
	    return true;
	} catch (NoAlertPresentException e) {
	    System.out.println("Unable to accept the Alert");
	    return false;
	}
    }

    public static void switchToNextWindow() {
	String currentWindow = driver.getWindowHandle();
	Set<String> windows = driver.getWindowHandles();
	Iterator<String> it = windows.iterator();
	while (it.hasNext()) {
	    String window = it.next();
	    if (!window.equals(currentWindow)) {
		driver.switchTo().window(window);
		break;
	    }
	}
    }

    public static boolean swithToParentWindow() {
	try {
	    Set<String> windows = driver.getWindowHandles();
	    Object arr[] = windows.toArray();
	    driver.switchTo().window(arr[0].toString());
	    return true;
	} catch (Exception e) {
	    System.out.println("unable to switch to window");
	    return false;
	}
    }

    public static boolean swithToWindowByIndex(int index) {
	try {
	    Set<String> windows = driver.getWindowHandles();
	    Object arr[] = windows.toArray();
	    driver.switchTo().window(arr[index].toString());
	    return true;
	} catch (Exception e) {
	    System.out.println("unable to switch to window");
	    return false;
	}
    }

    public static boolean swithToWindowByTitle(String text) {
	try {
	    driver.switchTo().window(text);
	    return true;
	} catch (Exception e) {
	    System.out.println("unable to switch to window by text");
	    return false;
	}
    }

    // below function is used to decode the encoded String
    public String decodeBase64(String encodedString) {
	byte[] decodedString = Base64.getDecoder().decode(encodedString);
	return new String(decodedString, StandardCharsets.UTF_8);
    }

    // Implicit wait where the wait applies for implicitly to entire flow
    public static void implicitWait(long timeOut) {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
    }

    public void explicitVisible(WebElement ele, long time) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public static void explicitinvisible(WebElement element, long timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void explicitClickable(WebElement ele, long time) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public void explicitVisibiltyOfAllElements(List<WebElement> elelist, long time) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	wait.until(ExpectedConditions.visibilityOfAllElements(elelist));
    }

    // fluent wait is the wai it will polling over the given time
    public static void fluentait(WebElement element, long timeOut, long pollingTimeout) {
	Wait<WebDriver> w = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
		.pollingEvery(Duration.ofSeconds(pollingTimeout)).ignoring(NoSuchElementException.class);
	w.until(driver -> element);
    }

    public static String getcurrenttime() {
	String currenttime = new SimpleDateFormat("yyyy-mm-dd-hh:mm:ss").format(new Date());
	return currenttime;
    }

    // Validating the string by using below function
    public boolean stringValidation(String expected, WebElement ele) {
	try {
	    Thread.sleep(3000);
	    String actual = ele.getText().toLowerCase();
	    String expecting = expected.toLowerCase();
	    System.out.println("Actual Name :" + actual + " Expected userName : " + expecting + "");
	    Assert.assertTrue(actual.equalsIgnoreCase(expecting));
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    public void screenCapture(WebDriver driver) {
	TakesScreenshot ts = (TakesScreenshot) driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
	String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	String currentdateAndTime = new SimpleDateFormat("yyyy-MM-dd-HH_MM_ss").format(new Date());
	String destination = System.getProperty("user.dir") + "\\Screenshots\\AllScreenshots" + currentdate + "\\"
		+ currentdateAndTime + ".png";
	try {
	    FileUtils.copyFile(src, new File(destination));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	// return destination; // we can retune when we want to use this on the itest
	// listeners to capture bases On testMethods
    }

    public static void screenCaptureFull(WebDriver driver) {
	String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	String currentdateAndTime = new SimpleDateFormat("yyyy-MM-dd-HH_MM_ss").format(new Date());
	String destination = System.getProperty("user.dir") + "\\Screenshots\\AllScreenshots" + currentdate + "\\"
		+ currentdateAndTime + ".png";
	Shutterbug.shootPage(driver, Capture.FULL, true).save(destination);
	// return destination; // we can return when we want to use this on the i test
	// listeners to capture bases On testMethods
    }

    // Below function is the data path reader
    public static String dataSheetPath() {
	String sourcePath = System.getProperty("user.dir") + "\\InputFiles\\PDPTesting.xlsx";
	return sourcePath;
    }

    public static String getData(int row, int column) throws IOException {
	String path = dataSheetPath();
	FileInputStream fis = new FileInputStream(path);
	try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
	    XSSFSheet sheet = wb.getSheetAt(0);
	    XSSFRow currentRow = sheet.getRow(row);
	    String cellValue = currentRow.getCell(column).getStringCellValue();
	    return cellValue;
	}
    }

    public static boolean setData(int row, int column, String value) throws IOException {
	try {
	    String path = dataSheetPath();
	    FileInputStream fis = new FileInputStream(path);
	    XSSFWorkbook wb = new XSSFWorkbook(fis);
	    XSSFSheet sheet = wb.getSheetAt(0);
	    XSSFRow currentrow = sheet.getRow(row);
	    currentrow.getCell(column).setCellValue(value);
	    FileOutputStream fos = new FileOutputStream(path);
	    wb.write(fos);
	    wb.close();
	    return true;
	} catch (Exception e) {
	    System.out.println("Unable to set the data for the cell");
	    return false;
	}
    }

    public static String getDataOfColumn(int row, String columnName) throws Exception {
	String cellValue = null;
	String path = dataSheetPath();
	FileInputStream fis = new FileInputStream(path);
	try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
	    XSSFSheet sheet = wb.getSheetAt(0);
	    Iterator<Row> rows = sheet.iterator();
	    Row topRow = rows.next();
	    int column = -1;
	    for (Cell cell : topRow) {
		if (columnName.equalsIgnoreCase(cell.getStringCellValue())) {
		    column = cell.getColumnIndex();
		    break;
		}
	    }
	    if (column != 1) {
		XSSFRow currentRow = sheet.getRow(row);
		XSSFCell currentCell = currentRow.getCell(column);
		String data = currentCell.getStringCellValue();
		cellValue = data;
	    } else {
		System.out.println("Column '" + columnName + "' not found.");
	    }
	}
	return cellValue;
    }

    public static boolean setDataByColumnName(int row, String columnName, String setValue) throws IOException {
	String path = dataSheetPath();
	FileInputStream fis = new FileInputStream(path);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet sheet = wb.getSheetAt(0);
	Iterator<Row> rows = sheet.iterator();
	Row topRow = rows.next();
	int column = -1;
	for (Cell cell : topRow) {
	    if (columnName.equalsIgnoreCase(cell.getStringCellValue())) {
		column = cell.getColumnIndex();
		break;
	    }
	}
	if (column != -1) {
	    XSSFRow currentRow = sheet.getRow(row);
	    XSSFCell currentCell = currentRow.createCell(column);
	    currentCell.setCellValue(setValue);

	    FileOutputStream fos = new FileOutputStream(path);
	    wb.write(fos);
	    wb.close();
	    fis.close();
	    fos.close();
	    return true;
	} else {
	    System.out.println("Column '" + columnName + "' not found.");
	    wb.close();
	    fis.close();
	    return false;
	}
    }

    public static int calculateDaysBetween(String eDate) {
	try {
	    String currentdate = new SimpleDateFormat("dd_MM_yyyy").format(new Date());
	    String[] currentD = currentdate.split("_");
	    int cdate = Integer.parseInt(currentD[0]);
	    int cmonth = Integer.parseInt(currentD[1]);
	    int cyear = Integer.parseInt(currentD[2]);

	    String eventdate = eDate;
	    String[] eventD = eventdate.split("/");
	    int edate = Integer.parseInt(eventD[0]);
	    int emonth = Integer.parseInt(eventD[1]);
	    int eyear = Integer.parseInt(eventD[2]);

	    LocalDate eventDate = LocalDate.of(eyear, emonth, edate);
	    LocalDate currentDate = LocalDate.of(cyear, cmonth, cdate);

	    int daysBetween = (int) ChronoUnit.DAYS.between(currentDate, eventDate);
	    // System.out.println("Days between " + currentDate + " and " + eventDate + " is
	    // " + daysBetween);
	    return daysBetween;
	} catch (Exception e) {
	    System.out.println("Unable to get the date and find the Difference");
	    return 0;
	}
    }
}
