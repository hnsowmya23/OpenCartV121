package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	String repName;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
		  public void onStart(ITestContext testContext) {
		    
			  SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			  Date dt = new Date();
			  String timeStamp=df.format(dt);
			  repName="Test-Report-" + timeStamp + ".html";
			  
			  sparkReporter=new ExtentSparkReporter(".\\reports\\" + repName);
			  sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
			  sparkReporter.config().setReportName("Opencart Funtional Testing");
			  sparkReporter.config().setTheme(Theme.DARK);
			  
			  extent= new ExtentReports();
			  extent.attachReporter(sparkReporter);
			  extent.setSystemInfo("Application", "Opencart");
			  extent.setSystemInfo("User Name", System.getProperty("user.name"));
			  extent.setSystemInfo("Enviornment", "QA");
			  
			  String os = testContext.getCurrentXmlTest().getParameter("os");
			  extent.setSystemInfo("Operating System", os);
			  
			  String browser = testContext.getCurrentXmlTest().getParameter("browser");
			  extent.setSystemInfo("Operating System", browser);
			  
			 List<String>includedGroups= testContext.getCurrentXmlTest().getIncludedGroups();
			 if(!includedGroups.isEmpty()) {
				 extent.setSystemInfo("G", includedGroups.toString()); 
			 }
		  
		  }

		 
		  public void onTestSuccess(ITestResult result) {

			test=  extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.PASS, result.getName()+"got successfully exceuted");
		  }

		  public void onTestFailure(ITestResult result) {

			  test=  extent.createTest(result.getTestClass().getName());
				test.assignCategory(result.getMethod().getGroups());
				test.log(Status.FAIL, result.getName()+"got failed");
				test.log(Status.INFO, result.getThrowable().getMessage());
				
				try {
				String imgPath = new BaseClass().captureScreenshot(result.getName());
				test.addScreenCaptureFromPath(imgPath);
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			  }

		  public void onTestSkipped(ITestResult result) {
			  test=  extent.createTest(result.getTestClass().getName());
				test.assignCategory(result.getMethod().getGroups());
				test.log(Status.FAIL, result.getName()+"got skipped");
				test.log(Status.INFO, result.getThrowable().getMessage());
		  }

		  
		  public void onFinish(ITestContext testcontext) {
		    
			  extent.flush();
			  
			  String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
			  File extentReport = new File(pathOfExtentReport);
			  
			  try {
				  Desktop.getDesktop().browse(extentReport.toURI());
			  }
			  catch(IOException e1) {
				  e1.printStackTrace();
			  }
			  
			  //send report automatically whenever report is generated
			  
			  
		  }
	

}
