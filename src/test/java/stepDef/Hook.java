package stepDef;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import utility.Constant;

public class Hook {
	public static WebDriver driver;


	@Before("@selenium")
	public void setUp() throws Exception {

		System.out.println("---------Browser is opening--------");

		System.out.println("\n" + Instant.now().toString() + " Initializing the setup");

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Pooja\\eclipse-workspace\\scantist_ui_automation\\Drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();

		options.addArguments(Constant.START_MAXIMIZED);
		options.addArguments(Constant.DISABLE_INFOBAR);
		options.setExperimentalOption(Constant.USE_AUTOMATION_EXTENTION, false);
		options.setExperimentalOption(Constant.EXCLUDE_SWITCHES, Collections.singletonList(Constant.ENABLE_AUTOMATION));
		options.addArguments(Constant.NO_SANDBOX);

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put(Constant.CREDENTIALS_ENABLE_SERVICE, false);
		prefs.put(Constant.PASSWORD_MANAGER_ENABLED, false);

		options.setExperimentalOption(Constant.PREFS, prefs);

		driver = new ChromeDriver(options);
		
		Properties prop=new Properties(); 
	//	FileInputStream ip= new FileInputStream("C:\\Users\\Pooja\\eclipse-workspace\\scantist_ui_automation\\src\\test\\java\\testData\\staging.properties"); 
	//	FileInputStream ip= new FileInputStream("C:\\Users\\Pooja\\eclipse-workspace\\scantist_ui_automation\\src\\test\\java\\testData\\deputy.properties"); 

    	  FileInputStream ip= new FileInputStream("C:\\Users\\Pooja\\eclipse-workspace\\scantist_ui_automation\\src\\test\\java\\testData\\prod.properties");
		
	//   FileInputStream ip= new FileInputStream("C:\\Users\\Pooja\\eclipse-workspace\\scantist_ui_automation\\src\\test\\java\\testData\\huawei.properties");

	//	FileInputStream ip= new FileInputStream("C:\\Users\\Pooja\\eclipse-workspace\\scantist_ui_automation\\src\\test\\java\\testData\\on-prem.properties");

	    prop.load(ip);
		  		 
	 // String baseUrl = prop.getProperty("stagingUrl");
	 // String baseUrl = prop.getProperty("deputyUrl");

		 
	  String baseUrl = prop.getProperty("prodUrl");
	   // String baseUrl = prop.getProperty("huaweiUrl");

	      
	  //String baseUrl = prop.getProperty("on-premUrl");

		  driver.get(baseUrl);

		  driver.manage().timeouts().implicitlyWait(Constant.IMPICIT_WAIT_TIME, TimeUnit.SECONDS);

	
	}

	/**
	 * This method is used get WebDriver object in whole project
	 * 
	 * @return
	 */

	public static WebDriver getDriver() {
		return driver;
	}

	@After("@selenium1")
	public void closeBrowser() throws Exception {
		driver.quit();
	}

}
