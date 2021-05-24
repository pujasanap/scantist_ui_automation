package stepDef;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.Utility;

public class ComponentStepDef {

	private WebDriver driver;
	private String mArgs;

	public ComponentStepDef() {
		this.driver = Hook.getDriver();
	}

	@When("^Click on component menu$")
	public void click_on_component_menu() throws Throwable {
		driver.findElement(By.xpath("//p[text()='Components']")).click();

	}

	@Then("^validate the component page$")
	public void validate_the_component_page() throws Throwable {
		Thread.sleep(1000);
		String pageTitle = driver.findElement(By.xpath("//a[text()='Components']")).getText();
		String expectedPageTitle = "Components";
		assertEquals(pageTitle, expectedPageTitle);

		Actions action = new Actions(driver);
		WebElement middleElement = driver.findElement(By.xpath("(//div[@class='card-header'])[2]"));
		action.moveToElement(middleElement).build().perform();
		Thread.sleep(1000);
		action.click();
		
		driver.findElement(By.xpath("//div[@id='tab-0']")).click();
	}

	@When("^Get component statistics menu$")
	public void get_component_statistics_menu() throws Throwable {
		if (driver.findElement(By.xpath("//h5[text()='Component Statistics']")).isDisplayed()) {
			if (driver.findElement(By.xpath("//span[text()='Components Found']")).isDisplayed()) {
				if (driver.findElement(By.xpath("(//table/tbody/tr[1]/td[2])[1]")).isDisplayed()) {
					String Component_Statistics = driver.findElement(By.xpath("(//table/tbody/tr[1]/td[2])[1]"))
							.getText();
					System.out.println("\nComponent Statistics present :- " + Component_Statistics);
				}
			}

			if (driver.findElement(By.xpath("//span[text()='Vulnerable Components']")).isDisplayed()) {
				if (driver.findElement(By.xpath("(//table/tbody/tr[2]/td[2])[1]")).isDisplayed()) {
					String Vulnerable_Components = driver.findElement(By.xpath("(//table/tbody/tr[2]/td[2])[1]"))
							.getText();
					System.out.println("\nVulnerable Components present :- " + Vulnerable_Components);
				}
			}

			if (driver.findElement(By.xpath("(//td/span[normalize-space(text()='Components with License Issues')])[3]"))
					.isDisplayed()) {
				if (driver.findElement(By.xpath("(//table/tbody/tr[3]/td[2])[1]")).isDisplayed()) {
					String Component_Statistics = driver.findElement(By.xpath("(//table/tbody/tr[3]/td[2])[1]"))
							.getText();
					System.out.println("\nComponents with License Issues :- " + Component_Statistics);
				}
			}
		}
	}

	@When("^Get Component Vulnerability menu$")
	public void get_Component_Vulnerability_menu() throws Throwable {

		if (driver.findElement(By.xpath("//h5[normalize-space(text()='Component Vulnerability')]")).isDisplayed()) {

			WebElement ele1 = driver.findElement(By.xpath("(//span/span)[2]"));
			String issueFound = ele1.getText();

			if (ele1.isDisplayed()) {
				String issueFoundValue = issueFound.replaceAll("[^0-9]", "");
				Utility.isStringInteger(issueFoundValue);
				if (Integer.parseInt(issueFoundValue) > 0) {
					System.out.println("\n"+issueFound);
				}
			}
		}
		boolean vulnerablilityGraph =driver.findElement(By.xpath("//canvas[@id='doughnut-chart']")).isDisplayed();
		if(vulnerablilityGraph == true) {
		System.out.println("\nComponent Vulnerability Graph is Present");
		
		}
	}

	@When("^Get Component Licenses menu$")
	public void get_Component_Licenses_menu() throws Throwable {
		
		WebElement ele1 = driver.findElement(By.xpath("(//span[@class='category'])[2]"));
		String issueFound = ele1.getText();
		if (ele1.isDisplayed()) {

			if (!issueFound.equalsIgnoreCase("No Issues Found")) {

				String issueFoundValue = issueFound.replaceAll("[^0-9]", "");
				Utility.isStringInteger(issueFoundValue);
				System.out.println("\nIssueFoundValue :-" + issueFoundValue);
				if (Integer.parseInt(issueFoundValue) > 0) {
				//	System.out.println("\nIssue found value is integer value and greater than zero");
					driver.findElement(By.xpath("//canvas[@id='library-license-chart']")).isDisplayed();

				}
			} else {

				System.out.println("\nIssue Found :-" + issueFound);
			}
		}
	}

	@When("^search for project \"([^\"]*)\" to view component details of project$")
	public void search_for_project_to_view_component_details_of_project(String arg1) throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@placeholder='Search Record'])[1]")).clear();
		driver.findElement(By.xpath("(//input[@placeholder='Search Record'])[1]")).sendKeys(arg1);
		driver.findElement(By.xpath("(//input[@placeholder='Search Record'])[1]")).sendKeys(Keys.ENTER);
	}

	@When("^get the project details & click on view button$")
	public void get_the_project_details_click_on_view_button() throws Throwable {
		
		Thread.sleep(5000);
		  WebDriverWait wait = new WebDriverWait(driver, 6000); wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]"))); 
		  WebElement element =
		  driver.findElement(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]")); 		  
			  
		  String projectDetails = element.getText();
		  System.out.println("\n*****Project Details*****" +"\n"+ projectDetails);
		 
		  WebElement element1 = driver.findElement(By.xpath("//p[text()='View']"));
		  JavascriptExecutor executor = (JavascriptExecutor)driver;
		  executor.executeScript("arguments[0].click();", element1);
		  
	}

	@Then("^redirect on component scan result page$")
	public void redirect_on_component_scan_result_page() throws Throwable {
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Component Scan Result']")));
		String pageTitle = driver.findElement(By.xpath("//a[text()='Component Scan Result']")).getText();
		System.out.println("Visited to "+pageTitle);
		Assert.assertEquals(pageTitle, "Component Scan Result");
		driver.navigate().back();
	}

	@When("^get the project details & click on action button$")
	public void get_the_project_details_click_on_action_button() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]")));
		WebElement element = driver.findElement(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]"));
		String projectDetails = element.getText();
		System.out.println("\n*****Project Details*****" + "\n" + projectDetails);
	}

	@When("^click on Generate and download pdf report$")
	public void click_on_Generate_and_download_pdf_report() throws Throwable {

		Thread.sleep(1000);
		driver.findElement(By.xpath("//h5[normalize-space()='Component Vulnerability']")).click();
		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//p[normalize-space(text()='example-pip-travis-master')]/preceding::span[@class='el-checkbox__inner'][1])[1]")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		Thread.sleep(1000);

		WebElement button = driver.findElement(By.xpath("//ul/li[@class='el-dropdown-menu__item'][2]/div[1]"));
		if (button.isDisplayed()) {
			String buttonText = button.getText();
			if (buttonText.equalsIgnoreCase("Download")) {
				Thread.sleep(1000);
				button.click();
			} else if (buttonText.equalsIgnoreCase("Generate Reports")) {
				button.click();
				
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)                            
						.withTimeout(20, TimeUnit.SECONDS)          
						.pollingEvery(5, TimeUnit.SECONDS)          
						.ignoring(NoSuchElementException.class);    
						
						WebElement download= wait.until(new Function<WebDriver, WebElement>() {       
						public WebElement apply(WebDriver driver) { 
						return driver.findElement(By.xpath("//li[@class='el-dropdown-menu__item']/div[text()='Download']"));     
						 }  
						});  

						WebElement actionButton = driver.findElement(By.xpath("(//button[@type='button'])[3]"));
						Actions act = new Actions(driver);
						act.moveToElement(actionButton).build().perform();						
						new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(download));
						download.click();
				
				
//				while(true) {
//					
//					WebElement actionButton = driver.findElement(By.xpath("(//button[@type='button'])[3]"));
//					Actions act = new Actions(driver);
//					act.moveToElement(actionButton).build().perform();
//					
//					if (buttonText.equalsIgnoreCase("Download")) {
//						System.out.println("If loop"+buttonText);
//						break;
//					}
//				
//					Thread.sleep(2000);
//					System.out.println("Aftre wait"+buttonText);
//				}
//				driver.findElement(By.xpath("//li[@class='el-dropdown-menu__item']/div[text()='Download']")).click();
//			}
			}
		}

		

		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		new WebDriverWait(driver, 2000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[9]/div/div[2]/form/div/div/div/div/span/span/i")));
		WebElement downArrow = driver
				.findElement(By.xpath("/html/body/div[1]/div[9]/div/div[2]/form/div/div/div/div/span/span/i"));
		if (downArrow.isDisplayed()) {
			downArrow.click();
			
			List<WebElement> list = driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
			
			for(WebElement ele1 : list) {
				
				if(ele1.getText().equalsIgnoreCase("PDF")) {
					ele1.click();
				}
			}
			
			driver.findElement(
					By.xpath("//button[@class='el-button el-button--primary']/span[normalize-space()='Confirm']"))
					.click();
		}
	}


	@Then("^validate the downloaded pdf report$")
	public void validate_the_downloaded_pdf_report() throws Throwable {

		String downloadPath = "C:\\Users\\Pooja\\Downloads\\";
		Thread.sleep(3000);
		Assert.assertTrue(Utility.isFileDownloaded(downloadPath, "Reports.zip"));
		System.out.println("File downloaded on desired location");

		try {
			if ((new File("C:\\Users\\Pooja\\Downloads\\Reports.zip")).delete()) {
				System.out.println("Pass");
			} else {
				System.out.println("Failed");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@When("^click on download csv report$")
	public void click_on_download_csv_report() throws Throwable {
		
		Thread.sleep(5000);
	//	driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
	//	driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);

		WebElement element= driver.findElement(By.xpath("(//button[@type='button'])[3]"));
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(element)).click();
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='el-dropdown-menu__item']/div[text()='Download']")));
		WebElement ele = driver.findElement(By.xpath("//li[@class='el-dropdown-menu__item']/div[text()='Download']"));
		act.moveToElement(ele).click().build().perform();

		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div[2]/form/div/div/div/div/span/span/i")).click();
		Thread.sleep(1000);
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
		
		for(WebElement ele1 : list) {
			
			if(ele1.getText().equalsIgnoreCase("CSV")) {
				ele1.click();
			}
		}		driver.findElement(
				By.xpath("//button[@class='el-button el-button--primary']/span[normalize-space()='Confirm']")).click();
	}

	@Then("^validate the downloaded csv report$")
	public void validate_the_downloaded_csv_report() throws Throwable {
		String downloadPath = "C:\\Users\\Pooja\\Downloads\\";
		Thread.sleep(3000);
		Assert.assertTrue(Utility.isFileDownloaded(downloadPath, "Reports.zip"));
		System.out.println("File downloaded on desired location");

		try {
			if ((new File("C:\\Users\\Pooja\\Downloads\\Reports.zip")).delete()) {
				System.out.println("Pass");
			} else {
				System.out.println("Failed");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@When("^click on download xml report$")
	public void click_on_download_xml_report() throws Throwable {
		
		Thread.sleep(5000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
        Thread.sleep(1000);
		WebElement ele = driver.findElement(By.xpath("(//button[@type='button'])[3]"));
		ele.click();
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		
		WebElement element = driver.findElement(By.xpath("//li[@class='el-dropdown-menu__item']/div[text()='Download']"));
		act.moveToElement(element).click().build().perform();

		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div[2]/form/div/div/div/div/span/span/i")).click();
		Thread.sleep(1000);
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
		
		for(WebElement ele1 : list) {
			
			if(ele1.getText().equalsIgnoreCase("XML")) {
				ele1.click();
			}
		}		driver.findElement(
				By.xpath("//button[@class='el-button el-button--primary']/span[normalize-space()='Confirm']")).click();

	}

	@When("^click on download json report$")
	public void click_on_download_json_report() throws Throwable {

//		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);

		Thread.sleep(5000);
		WebDriverWait wait = new  WebDriverWait(driver, 3000);
	//	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@type='button'])[3]")));
		
		WebElement ele = driver.findElement(By.xpath("(//button[@type='button'])[3]"));
		ele.click();
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		WebElement ele1 = driver.findElement(By.xpath("//li[@class='el-dropdown-menu__item']/div[text()='Download']"));
		act.moveToElement(ele1).click().build().perform();

		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div[2]/form/div/div/div/div/span/span/i")).click();
		Thread.sleep(1000);
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
		
		for(WebElement ele2 : list) {
			
			if(ele2.getText().equalsIgnoreCase("XML")) {
				ele2.click();
			}		
		}
			driver.findElement(
				By.xpath("//button[@class='el-button el-button--primary']/span[normalize-space()='Confirm']")).click();

	}

	@When("^select the tab and search for project \"([^\"]*)\"$")
	public void select_the_tab_and_search_for_project(String arg1) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);

		WebElement tab1 = driver.findElement(By.xpath("//div[@id='tab-1']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", tab1);			
			
		
		driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).sendKeys(arg1);
		driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).sendKeys(Keys.ENTER);
		
		mArgs = arg1;
	}

	@When("^get the project details$")
	public void get_the_project_details() throws Throwable {

				Thread.sleep(1000);
				String componentDetails = driver.findElement(By.xpath("//*[@id=\"pane-1\"]/div/div[2]/div/div/div[3]/table/tbody/tr[1]")).getText();
				System.out.println("*****ComponentDetails*****"+"\n"+componentDetails);		
		
	}

	@When("^click on Project button and scan result button and view button$")
	public void click_on_Project_button_and_scan_result_button_and_view_button() throws Throwable {
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/div[2]/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[2]/div/div")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/div[2]/div/div[2]/div/div/div[3]/table/tbody/tr[2]/td/div/div[2]/div[2]/div/div[1]/a/span")).click();
		Thread.sleep(1000);
		
		String pageTitle = driver.findElement(By.xpath("//a[text()='Project details']")).getText();
		String expectedPageTitle = "Project details";
		assertEquals(pageTitle, expectedPageTitle);
		System.out.println("\nVisited to Project details page");
		
		driver.navigate().back();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).sendKeys(mArgs);
		driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/div[2]/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[2]/div/div")));
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div/div[2]/div/div/div[2]/div/div/div[2]/div[2]/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[2]/div/div")).click();
		Thread.sleep(1000);
		if (driver.findElement(By.xpath("//*[@id='pane-1']/div/div[2]/div/div/div[3]/table/tbody/tr[2]/td/div/div[2]/div[2]/div/div[2]/a/span")).isDisplayed()) {
			WebElement arrow = driver.findElement(By.xpath("//*[@id='pane-1']/div/div[2]/div/div/div[3]/table/tbody/tr[2]/td/div/div[2]/div[2]/div/div[2]/a/span"));
			arrow.click();
		}
		
		Thread.sleep(1000);
		String pageTitle2 = driver.findElement(By.xpath("//a[text()='Component Scan Result']")).getText();
		String expectedPageTitle2 = "Component Scan Result";
		assertEquals(pageTitle2, expectedPageTitle2);
		System.out.println("\nVisited to Component scan result page");
		
		driver.navigate().back();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).sendKeys(mArgs);
		driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='pane-1']/div/div[2]/div/div/div[3]/table/tbody/tr[1]/td[2]/div/div")).click();
		new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='pane-1']/div/div[2]/div/div/div[3]/table/tbody/tr[2]/td/div/div[2]/div[2]/div/div[3]/a/span")));
		driver.findElement(By.xpath("//*[@id='pane-1']/div/div[2]/div/div/div[3]/table/tbody/tr[2]/td/div/div[2]/div[2]/div/div[3]/a/span")).click();
		
		Thread.sleep(1000);
		String pageTitle3 = driver.findElement(By.xpath("//a[text()='Component Details']")).getText();
		String expectedPageTitle3 = "Component Details";
		assertEquals(pageTitle3, expectedPageTitle3);
		System.out.println("\nVisited to Component Details page");
		
		driver.navigate().back();
		Thread.sleep(1000);
	}

	@Then("^validate the project page and scan result page and individual component page$")
	public void validate_the_project_page_and_scan_result_page_and_individual_component_page() throws Throwable {

	}

	/*
	 * public static boolean isStringInteger(String number) { try {
	 * Integer.parseInt(number); } catch (Exception e) { return false; } return
	 * true; }
	 */

	/*
	 * public boolean isFileDownloaded(String downloadPath, String fileName) {
	 * boolean flag = false; File dir = new File(downloadPath); File[] dir_contents
	 * = dir.listFiles();
	 * 
	 * for (int i = 0; i < dir_contents.length; i++) { if
	 * (dir_contents[i].getName().equals(fileName)) return flag = true; }
	 * 
	 * return flag; }
	 */
}
