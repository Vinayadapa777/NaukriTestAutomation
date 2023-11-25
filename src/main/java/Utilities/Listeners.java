package Utilities;

import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends Configurations implements ITestListener {
    ExtentTest test;
    ExtentReports ext = Configurations.reporter();
    ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
	test = ext.createTest(result.getMethod().getMethodName());
	extent.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
	extent.get().log(Status.PASS, "Test Pass");
	screenShot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
	extent.get().log(Status.FAIL, "Test Fail");
	screenShot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub
	//ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	// TODO Auto-generated method stub
	ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
	// TODO Auto-generated method stub
	ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
	// TODO Auto-generated method stub
	ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
	ext.flush();
    }
    
    public void screenShot(ITestResult result) {
	String testcaseName = result.getMethod().getMethodName();
	WebDriver driver = null;
	try {
	    driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (IllegalArgumentException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NoSuchFieldException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	extent.get().addScreenCaptureFromPath(screenShot(driver, testcaseName),testcaseName);
    }

}
