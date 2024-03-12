package com.HMS.genericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenClass implements ITestListener
{

	ExtentReports report;
	ExtentTest Test;
	public void onTestStart(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		Test = report.createTest(methodName);
		Reporter.log(methodName+"---->Execution started");
		
	}

	public void onTestSuccess(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		Test.log(Status.PASS,methodName+"---->Passed");
		Reporter.log(methodName+"----->Executed sucessfully");
	}

	public void onTestFailure(ITestResult result) {
		String MethodName = result.getMethod().getMethodName();
		WebDriverUtilities w=new WebDriverUtilities();
		try {
			String screenShot = w.screenShot(BaseClass.driver, MethodName);
			Test.addScreenCaptureFromPath(screenShot);
			Test.log(Status.FAIL,MethodName+"----->TestScript  failed");
			Test.log(Status.FAIL, result.getThrowable());
			Reporter.log(MethodName+"execution failed");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		Test.log(Status.SKIP,methodName+"---->testScript skipped" );
		Test.log(Status.SKIP,result.getThrowable());
		Reporter.log(methodName+"----> Execution skipped");
	}

	public void onStart(ITestContext context)
	{
		
		ExtentSparkReporter htmlReport=new ExtentSparkReporter(".//ExtentReport//Report1.html");
		htmlReport.config().setDocumentTitle("Hospital Mangement system");
		htmlReport.config().setTheme(Theme.STANDARD);
		htmlReport.config().setReportName("HMS");
		
	    report=new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("BasePlatform","windows 10");
		report.setSystemInfo("BaseBrowser", "chrome");
		report.setSystemInfo("BaseUrl", "http://rmgtestingserver/domain/Hospital_Management_System/");
		report.setSystemInfo("Reporter Name", "Rajashekar");
		
		
	}

	public void onFinish(ITestContext context) {
	report.flush();
	}

	
}
