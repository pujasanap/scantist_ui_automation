package stepDef;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
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

public class VulnerabiltyStepDef {

	private WebDriver driver;
	String mArgs;

	public VulnerabiltyStepDef() {
		this.driver = Hook.getDriver();
	}

	@When("^Click on vulnerability menu$")
	public void click_on_vulnerability_menu() throws Throwable {
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fas fa-exclamation-triangle']")));
		driver.findElement(By.xpath("//i[@class='fas fa-exclamation-triangle']")).click();
	}

	@Then("^validate the vulnerability page$")
	public void validate_the_vulnerability_page() throws Throwable {
		
		driver.navigate().refresh();

		new WebDriverWait(driver, 2000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Vulnerabilities']")));
		String pageTitle = driver.findElement(By.xpath("//a[text()='Vulnerabilities']")).getText();
		String expectedPageTitle = "Vulnerabilities";
		assertEquals(pageTitle, expectedPageTitle);

		driver.findElement(By.xpath("//div[@id='tab-0']")).click();
	}

	@When("^get and validate the overall section$")
	public void get_the_overall_section() throws Throwable {
		if (driver.findElement(By.xpath("(//h5[normalize-space(text()='Overall')])[1]")).isDisplayed()) {

			WebElement ele1 = driver.findElement(By.xpath("(//span[@class='category'])[1]"));
			String issueFound = ele1.getText();
			if (ele1.isDisplayed()) {

				if (!issueFound.equalsIgnoreCase("No Issues Found")) {

					String issueFoundValue = issueFound.replaceAll("[^0-9]", "");
					Utility.isStringInteger(issueFoundValue);
					System.out.println("\nIssueFoundValue :-" + issueFoundValue);
					if (Integer.parseInt(issueFoundValue) > 0) {
						//System.out.println("\nIssue found value is integer value and greater than zero");
						driver.findElement(By.xpath("//canvas[@id='0-chart']")).isDisplayed();

					}
				} else {

					System.out.println("\nIssue Found :-" + issueFound);
					driver.findElement(By.xpath("(//*[@id='Layer_1'])[1]")).isDisplayed();
				}

			}
		}
	}

	@When("^get and validate the security warning section$")
	public void get_and_validate_the_security_warning_section() throws Throwable {
		if (driver.findElement(By.xpath("(//h5[normalize-space(text()='Security Warning ')])[2]")).isDisplayed()) {

			WebElement ele1 = driver.findElement(By.xpath("(//span[@class='category'])[2]"));
			String issueFound = ele1.getText();
			if (ele1.isDisplayed()) {

				if (!issueFound.equalsIgnoreCase("No Issues Found")) {

					String issueFoundValue = issueFound.replaceAll("[^0-9]", "");
					Utility.isStringInteger(issueFoundValue);
					System.out.println("\nIssueFoundValue :-" + issueFoundValue);
					if (Integer.parseInt(issueFoundValue) > 0) {
						//System.out.println("\nIssue found value is integer value and greater than zero");
						driver.findElement(By.xpath("//canvas[@id='0-chart']")).isDisplayed();

					}
				} else {

					System.out.println("\nIssue Found :- " + issueFound);
					Thread.sleep(1000);
					driver.findElement(By.xpath("(//*[@id='Layer_1'])")).isDisplayed();
				}

			}
		}

	}

	@When("^get and validate the security bug section$")
	public void get_and_validate_the_security_bug_section() throws Throwable {
		if (driver.findElement(By.xpath("(//h5[normalize-space(text()='Overall')])[3]")).isDisplayed()) {

			WebElement ele1 = driver.findElement(By.xpath("(//span[@class='category'])[3]"));
			String issueFound = ele1.getText();
			if (ele1.isDisplayed()) {

				if (!issueFound.equalsIgnoreCase("No Issues Found")) {

					String issueFoundValue = issueFound.replaceAll("[^0-9]", "");
					Utility.isStringInteger(issueFoundValue);
					System.out.println("\nIssueFoundValue :-" + issueFoundValue);
					if (Integer.parseInt(issueFoundValue) > 0) {
						//System.out.println("\nIssue found value is integer value and greater than zero");
						driver.findElement(By.xpath("//canvas[@id='2-chart']")).isDisplayed();

					}
				} else {

					System.out.println("\nIssue Found :-" + issueFound);
					driver.findElement(By.xpath("(//*[@id='Layer_1'])[3]")).isDisplayed();
				}

			}
		}
	}

	@When("^get and validate the cve section$")
	public void get_and_validate_the_cve_section() throws Throwable {
		if (driver.findElement(By.xpath("(//h5[normalize-space(text()='Overall')])[4]")).isDisplayed()) {

			WebElement ele1 = driver.findElement(By.xpath("(//span[@class='category'])[4]"));
			String issueFound = ele1.getText();
			if (ele1.isDisplayed()) {

				if (!issueFound.equalsIgnoreCase("No Issues Found")) {

					String issueFoundValue = issueFound.replaceAll("[^0-9]", "");
					Utility.isStringInteger(issueFoundValue);
					System.out.println("\nIssueFoundValue :-" + issueFoundValue);
					if (Integer.parseInt(issueFoundValue) > 0) {
						//System.out.println("\nIssue found value is integer value and greater than zero");
						driver.findElement(By.xpath("//canvas[@id='3-chart']")).isDisplayed();
					}
				} else {

					System.out.println("\nIssue Found :-" + issueFound);
					driver.findElement(By.xpath("(//*[@id='Layer_1'])[4]")).isDisplayed();
				}

			}
		}
	}

	@When("^search for project \"([^\"]*)\" to view vulnerbaility details of project$")
	public void search_for_project_to_view_vulnerbaility_details_of_project(String arg1) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[1]")).clear();
		driver.findElement(By.xpath("(//input[@type='search'])[1]")).sendKeys(arg1);
		driver.findElement(By.xpath("(//input[@type='search'])[1]")).sendKeys(Keys.ENTER);
	}

	@When("^search for project \"([^\"]*)\" to view vulnerability details of project$")
	public void search_for_project_to_view_vulnerability_details_of_project(String arg1) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[1]")).clear();
		driver.findElement(By.xpath("(//input[@type='search'])[1]")).sendKeys(arg1);
		driver.findElement(By.xpath("(//input[@type='search'])[1]")).sendKeys(Keys.ENTER);
		// Actions actions = new Actions(driver);
		// actions.moveByOffset(500, 500).click().build().perform();
		// actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
	}
	
	@When("^get the project details & click on view button of vulnerbaility project$")
	public void get_the_project_details_click_on_view_button_of_vulnerbaility_project() throws Throwable {
		 WebDriverWait wait = new WebDriverWait(driver, 100); wait.until(
		 ExpectedConditions.presenceOfElementLocated(By.xpath(
				 "(//table/tbody/tr[@class='el-table__row'])[1]"))); 
		 WebElement element = driver.findElement(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]")); 
				  String projectDetails = element.getText();
				  System.out.println("\n*****Project Details*****" +"\n"+ projectDetails);
				  Thread.sleep(2000);
				 
				WebElement element1 = driver.findElement(By.xpath("//p[text()='View']"));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element1);
	}

	@Then("^redirect on vulnerability scan result page$")
	public void redirect_on_vulnerability_scan_result_page() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Vulnerability Results']")));
		String pageTitle = driver.findElement(By.xpath("//a[text()='Vulnerability Results']")).getText();
		System.out.println("********************************************");
		System.out.println(pageTitle);
		System.out.println("********************************************");

		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);

	}

	@When("^Get the project details & click on action button$")
	public void get_the_project_details_click_on_action_button() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]")));
		WebElement element = driver.findElement(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]"));
		String projectDetails = element.getText();
		System.out.println("\nProject Details :-"+ "\n" + projectDetails);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//p[normalize-space(text()='example-python-master')])[15]/preceding::span[@class='el-checkbox__inner'][1]")).click();
	}
	

		@When("^click on Generate and download pdf vulnerability report$")
		public void click_on_Generate_and_download_pdf_vulnerability_report() throws Throwable {

			Thread.sleep(1000);
			driver.findElement(By.xpath("//h5[normalize-space()='Security Warning']")).click();
			driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
			Thread.sleep(1000);

			List<WebElement> reports = driver.findElements(By.xpath("//ul/li[@class='el-dropdown-menu__item']/div[1]"));
			
			for(WebElement report : reports) {
				if (report.getText().equalsIgnoreCase("Download")) {
					Thread.sleep(1000);
					report.click();
				} else if (report.getText().equalsIgnoreCase("Generate Reports")) {
					report.click();
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
				}
			}	
				
			String subWindowHandler = null;

			Set<String> handles = driver.getWindowHandles();
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()) {
				subWindowHandler = iterator.next();
			}
			driver.switchTo().window(subWindowHandler);
			Thread.sleep(1000);
			WebElement downArrow = driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div[2]/form/div/div/div/div/span/span/i"));
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

		@When("^click on download csv vulnerability report$")
		public void click_on_download_csv_vulnerability_report() throws Throwable {
			Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button/span[normalize-space()='Action']")));
			WebElement actionButton =driver.findElement(By.xpath("//button/span[normalize-space()='Action']"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", actionButton);
			
			WebElement button = driver.findElement(By.xpath("//div[text()='Download']"));
			
			JavascriptExecutor executor1 = (JavascriptExecutor)driver;
			executor1.executeScript("arguments[0].click()", button);
			
			String subWindowHandler = null;

			Set<String> handles = driver.getWindowHandles();
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()) {
				subWindowHandler = iterator.next();
			}
			driver.switchTo().window(subWindowHandler);
			Thread.sleep(1000);
			WebElement downArrow = driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div[2]/form/div/div/div/div/span/span/i"));
			if (downArrow.isDisplayed()) {
				downArrow.click();
				
				List<WebElement> list = driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
				
				for(WebElement ele1 : list) {
					
					if(ele1.getText().equalsIgnoreCase("CSV")) {
						ele1.click();
					}
				}
				
				driver.findElement(
						By.xpath("//button[@class='el-button el-button--primary']/span[normalize-space()='Confirm']"))
						.click();
			}
			
		}

		@When("^click on download xml vulnerability report$")
		public void click_on_download_xml_vulnerability_report() throws Throwable {
			Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button/span[normalize-space()='Action']")));
			WebElement actionButton =driver.findElement(By.xpath("//button/span[normalize-space()='Action']"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", actionButton);
			
			WebElement button = driver.findElement(By.xpath("//div[text()='Download']"));
			
			JavascriptExecutor executor1 = (JavascriptExecutor)driver;
			executor1.executeScript("arguments[0].click()", button);
			
			String subWindowHandler = null;

			Set<String> handles = driver.getWindowHandles();
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()) {
				subWindowHandler = iterator.next();
			}
			driver.switchTo().window(subWindowHandler);
			Thread.sleep(1000);
			WebElement downArrow = driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div[2]/form/div/div/div/div/span/span/i"));
			if (downArrow.isDisplayed()) {
				new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(downArrow));
				downArrow.click();
				
				List<WebElement> list = driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
				
				for(WebElement ele1 : list) {
					
					if(ele1.getText().equalsIgnoreCase("XML")) {
						ele1.click();
					}
				}
				
				driver.findElement(
						By.xpath("//button[@class='el-button el-button--primary']/span[normalize-space()='Confirm']"))
						.click();
			}
			
		}

		@When("^click on download json vulnerability report$")
		public void click_on_download_json_vulnerability_report() throws Throwable {
			Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button/span[normalize-space()='Action']")));
			WebElement actionButton =driver.findElement(By.xpath("//button/span[normalize-space()='Action']"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", actionButton);
			
			WebElement button = driver.findElement(By.xpath("//div[text()='Download']"));
			
			JavascriptExecutor executor1 = (JavascriptExecutor)driver;
			executor1.executeScript("arguments[0].click()", button);
			
			String subWindowHandler = null;

			Set<String> handles = driver.getWindowHandles();
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()) {
				subWindowHandler = iterator.next();
			}
			driver.switchTo().window(subWindowHandler);
			Thread.sleep(1000);
			WebElement downArrow = driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div[2]/form/div/div/div/div/span/span/i"));
			if (downArrow.isDisplayed()) {
				downArrow.click();
				
				List<WebElement> list = driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
				
				for(WebElement ele1 : list) {
					
					if(ele1.getText().equalsIgnoreCase("JSON")) {
						ele1.click();
					}
				}
				
				driver.findElement(
						By.xpath("//button[@class='el-button el-button--primary']/span[normalize-space()='Confirm']"))
						.click();
			}
			
		}


		@When("^select the tab and search for vulnerbility \"([^\"]*)\"$")
		public void select_the_tab_and_search_for_vulnerbility(String arg1) throws Throwable {
			Thread.sleep(1000);
			driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
			driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);

			new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='tab-1']")));
			
			try {
				driver.findElement(By.xpath("//div[@id='tab-1']")).click();
			  } catch (Exception e) {
			     JavascriptExecutor executor = (JavascriptExecutor) driver;
			     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='tab-1']")));
			  }
			driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).sendKeys(arg1);
			driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).sendKeys(Keys.ENTER);
			
			mArgs = arg1;
		}

	@When("^get the vulnerability details$")
	public void get_the_vulnerability_details() throws Throwable {
		new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@class='el-table__row']/td[3]/div/a[normalize-space()='CVE-2012-4431']/preceding::div[@class='el-table__expand-icon']")));
		  String vulnerabilityDetails = driver.findElement(By.xpath("//*[@id=\"pane-1\"]/div/div[2]/div/div/div[3]/table/tbody/tr[1]")).getText();
		  System.out.println("\nVulnerabillity Details :-"+"\n"+vulnerabilityDetails);	
	}

	@When("^click on Issue ID and click project details and scan result button$")
	public void click_on_Issue_ID_and_click_project_details_and_scan_result_button() throws Throwable {
        new WebDriverWait(driver, 8000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='pane-1']/div/div[2]/div/div/div[3]/table/tbody/tr/td[2]/div/div")));		
		driver.findElement(By.xpath("//*[@id='pane-1']/div/div[2]/div/div/div[3]/table/tbody/tr/td[2]/div/div")).click();

		new WebDriverWait(driver, 5000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/div[@class='col-md-4 margin-top-0 col-md-offset-1 link-style']/a")));		
		driver.findElement(By.xpath("//li/div[@class='col-md-4 margin-top-0 col-md-offset-1 link-style']/a")).click();
		String pageTitle = driver.findElement(By.xpath("//a[text()='Project details']")).getText();
		String expectedPageTitle = "Project details";
		assertEquals(pageTitle, expectedPageTitle);
		System.out.println("\nVisited to Project details page");
		
		driver.navigate().back();
		
		Thread.sleep(1000);
		
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='tab-1']")).click();
		driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).clear();
		driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).sendKeys(mArgs);
		driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).sendKeys(Keys.ENTER);

		Thread.sleep(13000);
		driver.findElement(By.xpath("//*[@id='pane-1']/div/div[2]/div/div/div[3]/table/tbody/tr/td[2]/div/div")).click();
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a/span/i)[2]")));
		WebElement ele=driver.findElement(By.xpath("(//a/span/i)[2]"));
		Utility.retryingFindClick(ele);
		
		String pageTitle1 = driver.findElement(By.xpath("//a[text()='Vulnerability Results']")).getText();
		String expectedPageTitle1 = "Vulnerability Results";
		assertEquals(pageTitle1, expectedPageTitle1);
		System.out.println("\nVisited to Vulnerability Results page");
		
		driver.navigate().back();
		
	}

	@Then("^validate the project page and individual vulnerability page$")
	public void validate_the_project_page_and_individual_vulnerability_page() throws Throwable {

	}

}
