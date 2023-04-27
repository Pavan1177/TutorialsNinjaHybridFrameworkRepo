package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport  {

	public static ExtentReports generateExtentReport() {
		
	ExtentReports extentReports = new ExtentReports();
	File file = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReports.html");
	ExtentSparkReporter sparkReport = new ExtentSparkReporter(file);
	sparkReport.config().setTheme(Theme.DARK);
	sparkReport.config().setReportName("TutorialsNinja Test Automation Results");
	sparkReport.config().setDocumentTitle("TN Automation Report");
	sparkReport.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	extentReports.attachReporter(sparkReport);
	
	Properties configProp = new Properties();
	File configFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
	try{
		FileInputStream fis = new FileInputStream(configFile);
		configProp.load(fis);
	}catch(Throwable e) {
	     e.printStackTrace();
	}
	
	
	extentReports.setSystemInfo("Application URL", configProp.getProperty("url"));
	extentReports.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
	extentReports.setSystemInfo("Email", configProp.getProperty("validEmail"));
	extentReports.setSystemInfo("Password", configProp.getProperty("validPassword"));
	extentReports.setSystemInfo("Operating system", System.getProperty("os.name"));
	extentReports.setSystemInfo("UserName", System.getProperty("user.name"));
	extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	
	return extentReports;
	}
}
