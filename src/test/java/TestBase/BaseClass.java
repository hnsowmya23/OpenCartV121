package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Sanity", "Regression", "Master", "Datadriven"})
	@Parameters({"os", "browser"})
	public void setUp(String os, String br) throws IOException {
		
		FileReader file = new FileReader("./src//test//resources//config.properties");// we can use FileInputStream
		p= new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		
		switch(br.toLowerCase()) {
		case "chrome"  : driver= new ChromeDriver(); break;
		case "firefox" : driver= new FirefoxDriver(); break;
		case "edge"    : driver= new EdgeDriver(); break;
		default : System.out.println("Invalid broswer name"); return;
		}
		
		
		
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass(groups= {"Sanity", "Regression", "Master", "Datadriven"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomStringGenerate() {
		String randomString =RandomStringUtils.randomAlphabetic(8);
		return randomString;
	}
	
	public String randomNumericGenerate() {
		String randomNumeric= RandomStringUtils.randomNumeric(10);
		return randomNumeric;
	}
	
	public String randomAlphaNumericGenerate() {
		String randomString =RandomStringUtils.randomAlphabetic(3);
		String randomNumeric= RandomStringUtils.randomNumeric(3);
		return (randomString+"@"+randomNumeric);
	}
	
	public String captureScreenshot(String tname) throws IOException {
		 SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		  Date dt = new Date();
		  String timeStamp=df.format(dt);
		  
		  TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		  File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		  String targetFilePath=System.getProperty("user.dir")+" \\screenshots" + tname + "_" + timeStamp + ".png";
		  File targetFile= new File(targetFilePath);
		  sourceFile.renameTo(targetFile);
		  
		  return targetFilePath;
		  
	}

}
