package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.tutorialsninja.qa.utils.ExtentReport;

public class MyListeners implements ITestListener {
	ExtentReports extentReports;
	ExtentTest extentTest;
	String testName;
	@Override
	public void onStart(ITestContext context) {
		
	extentReports = ExtentReport.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
	   testName = result.getName();
	   extentTest = extentReports.createTest(testName);
	   extentTest.log(Status.INFO, testName+"started executing");
	   
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, testName+"got successfully executed");
	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+"got failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+"got skipped");
		
	}
	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReports.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
}
