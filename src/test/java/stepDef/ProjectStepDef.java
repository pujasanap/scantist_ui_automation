package stepDef;

import static org.junit.Assert.assertEquals;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.Utility;

public class ProjectStepDef {

	private static WebDriver driver;
	private String binaryProjectName;

	public String searchSourceProject1;
	public ProjectStepDef() {
		this.driver = Hook.getDriver();
	}

	/*
	 * public static boolean retryingFindClick(By by) { boolean result = false; int
	 * attempts = 0; while (attempts < 2) { try { driver.findElement(by).click();
	 * result = true; break; } catch (StaleElementReferenceException e) { } } return
	 * result; }
	 * 
	 * static boolean isNumber(String s) { for (int i = 0; i < s.length(); i++) if
	 * (Character.isDigit(s.charAt(i)) == false) return false;
	 * 
	 * return true; }
	 */

	@When("^Click on project menu$")
	public void click_on_project_menu() throws Throwable {
		new WebDriverWait(driver, 6000).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href='/u/scantist3/org/scantist3/projects'])[1]")));
		driver.findElement(By.xpath("(//a[@href='/u/scantist3/org/scantist3/projects'])[1]")).click();
	}

	@Then("^validate the Project page$")
	public void validate_the_Project_page() throws Throwable {
		Thread.sleep(1000);
		String pageTitle = driver.findElement(By.xpath("//a[text()='Projects']")).getText();
		String expectedPageTitle = "Projects";
		assertEquals(pageTitle, expectedPageTitle);

		Actions action = new Actions(driver);
		WebElement card = driver.findElement(By.xpath("(//div[@class='clickable-chart'])[1]"));
		action.moveToElement(card).build().perform();
		Thread.sleep(1000);
		action.click();
	}

	@When("^Get the overview section information$")
	public void get_the_overview_section_information() throws Throwable {
		if (driver.findElement(By.xpath("//div[text()='Total Projects']")).isDisplayed()) {
			String countTotalProject = driver.findElement(By.xpath("(//h3/span)[1]")).getText();
			if (countTotalProject != null) {
				System.out.println("\n Total projects :- " + countTotalProject);
			}
		}

		if (driver.findElement(By.xpath("//div[text()='Binary Usage']")).isDisplayed()) {
			String binaryUsage = driver.findElement(By.xpath("(//h3/span)[2]")).getText();
			if (binaryUsage != null) {
				System.out.println("\n Binary usage present :- " + binaryUsage);
			}
		}

		if (driver.findElement(By.xpath("//div[text()='Scan Usage']")).isDisplayed()) {
			String scanUsage = driver.findElement(By.xpath("(//h3/span)[3]")).getText();
			// int scanUsage1 = Integer.parseInt(scanUsage);
			if (scanUsage != null) {
				System.out.println("\n Scan usage :- " + scanUsage);
			}
		}

		/*
		 * if (driver.findElement(By.xpath("//div[text()='CVE Coverage Limit']")).
		 * isDisplayed()) { String cveCoverageLmit =
		 * driver.findElement(By.xpath("(//h3/span)[4]")).getText(); if (cveCoverageLmit
		 * != null) { System.out.println("\n CVE coverage limit present :- " +
		 * cveCoverageLmit); } }
		 */
	}

	@Then("^validate the overview section$")
	public void validate_the_overview_section() throws Throwable {
		if (driver.findElements(By.xpath("//canvas[@id='doughnut-chart']")).size() > 0) {
			System.out.println("\n *****Project chart is present*****");
		}

		if (driver.findElement(By.xpath("//h6[text()='Up-to-date Scans']")).isDisplayed()) {

			String uptoDate = driver.findElement(By.xpath("(//div[@class='clickable-chart'])[1]")).getText();
			if (!uptoDate.isEmpty() && Utility.isNumber(uptoDate)) {
				System.out.println("\n Up To Date scanned card  displaying Count :-" + uptoDate);
			}

			if (driver.findElement(By.xpath("//h6[text()='Outdated Scans']")).isDisplayed()) {
				String outDated = driver.findElement(By.xpath("(//div[@class='clickable-chart'])[2]")).getText();
				if (!outDated.isEmpty() && Utility.isNumber(outDated)) {
					System.out.println("\n Outdated scanned card  displaying Count :-" + outDated);
				}

				if (driver.findElement(By.xpath("//h6[text()='Never Scanned']")).isDisplayed()) {
					String nevered = driver.findElement(By.xpath("(//div[@class='clickable-chart'])[3]")).getText();
					if (!nevered.isEmpty() && Utility.isNumber(nevered)) {
						System.out.println("\n Never scanned card  displaying Count :-" + nevered);
					}

					System.out.println("\n *****All cards are displaying*****");
				}

			}
		}
	}

	@When("^enter the binary project name \"([^\"]*)\" and hit enter$")
	public void enter_the_binary_project_name_and_hit_enter(String searchText) throws Throwable {
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(searchText);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);
		binaryProjectName = searchText;
	}

	@Then("^validate the project details$")
	public void validate_the_project_details() throws Throwable {
		Thread.sleep(2000);
		if (driver.findElement(By.xpath("//table[@class='el-table__body']")).isDisplayed()) {
			String projectDetails = driver.findElement(By.xpath("//table[@class='el-table__body']")).getText();
			System.out.println("\n *****project details*****" + "\n" + projectDetails);
		}
	}

	@When("^click on scan button$")
	public void click_on_scan_button() throws Throwable {

		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"v-step-scan\"]/div[3]/table/tbody/tr/td[10]/div/div/button")).click();
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//span[@class='el-radio__inner'])[2]")).click();
		driver.findElement(By.xpath("//button/span[text()='Next']")).click();
		// System.out.println(driver.switchTo().window(parentWindowHandler));
		driver.findElement(
				By.xpath("/html/body/div[1]/div[5]/div/div[2]/div/div[1]/div/form/div[5]/div/div/div[1]/span/span/i"))
				.click();
		Thread.sleep(1000);

		List<WebElement> list = driver
				.findElements(By.xpath("(//ul[@class='el-scrollbar__view el-select-dropdown__list'])[4]"));
		for (WebElement ele : list) {
			if (ele.getText().equals("1.0")) {
				Thread.sleep(1000);
				ele.click();
				break;
			}
		}

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//span[text()='Scan'])[1]")).click();
	}

	@When("^wait until the scan completion$")
	public void wait_until_the_scan_completion() throws Throwable {

		/*
		 * WebElement view1 = driver.findElement(By.
		 * xpath("(//a/p[@class='p-style titlelink-style capitalize'])[1]")); WebElement
		 * view_1 = driver.findElement(By.
		 * xpath("(//a/p[@class='p-style titlelink-style capitalize'])[1]"));
		 * 
		 * WebElement view2 = driver.findElement(By.
		 * xpath("(//a/p[@class='p-style titlelink-style capitalize'])[2]")); WebElement
		 * view3 = driver.findElement(By.
		 * xpath("(//a/p[@class='p-style titlelink-style capitalize'])[3]")); WebElement
		 * tableRow =
		 * driver.findElement(By.xpath("(//tbody/tr[@class=\"el-table__row\"])[1]"));
		 * final WebElement progressBar = driver .findElement(By.xpath(
		 * "//*[@id='v-step-scan']/div[3]/table/tbody/tr/td[7]/div/div/div/div"));
		 */

		// New added condition

//		  final Timer t = new Timer(); 
//		  
//		  t.schedule(new TimerTask() {
//		  
//		  @Override public void run() { 
//			  
//			  		
//		  	} 
//		  }, 0 ,2000);

		while (true) {
			if (Utility.isElementPresent(By.xpath("(//a/span[@class='titlelink-style capitalize'])[1]"))) {
				//System.out.println("View is present");
				break;
			} else {
				//System.out.println("View is not present");
			}

		}
		// System.out.println("CHECK ELEMENT IS
		// PRESENT"+isElementPresent(By.xpath("(//a/p[@class='p-style titlelink-style
		// capitalize'])[1]")));
		for (int j = 0; j < 5; j++)
			try {
				new WebDriverWait(driver, 6000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a/span[@class='titlelink-style capitalize'])[1]")));
				WebElement elementName = driver
						.findElement(By.xpath("(//a/span[@class='titlelink-style capitalize'])[1]"));
				elementName.click();
				break;
			} catch (StaleElementReferenceException e) {
				e.toString();
				System.out.println("Stale element error, trying ::  " + e.getMessage());
			}

		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(binaryProjectName);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a/span[@class='titlelink-style capitalize color-red'])[1]")).click();
		Thread.sleep(2000);
		driver.navigate().back();

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(binaryProjectName);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a/span[@class='titlelink-style capitalize'])[2]")).click();
		Thread.sleep(2000);
		driver.navigate().back();

	}

	@Then("^validate the scan result$")
	public void validate_the_scan_result() throws Throwable {
		Thread.sleep(1000);
		String projectDetails = driver.findElement(By.xpath("(//tbody/tr[@class=\"el-table__row\"])[1]")).getText();

		if (projectDetails.equals("UP-TO-DATE")) {
			System.out.println("*****Values*****" + projectDetails);
		}
	}

	@When("^enter the github project name \"([^\"]*)\" and hit enter$")
	public void enter_the_github_project_name_and_hit_enter(String ciProjectSearch) throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(ciProjectSearch);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);
	}

	@When("^trigger the build through CI tool$")
	public void trigger_the_build_through_CI_tool() throws Throwable {

		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		driver.findElement(By.linkText("urlLink")).sendKeys(selectLinkOpeninNewTab);

	}

	@When("^enter the source project name \"([^\"]*)\" and hit enter$")
	public void enter_the_source_project_name_and_hit_enter(String searchSourceProject) throws Throwable {
		Thread.sleep(1000);

			driver.findElement(By.xpath("//input[@type='search']")).clear();
			driver.findElement(By.xpath("//input[@type='search']")).sendKeys(searchSourceProject);
			driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);
		
		searchSourceProject1=searchSourceProject;
	}

	@When("^click on scan button to scan source code$")
	public void click_on_scan_button_to_scan_source_code() throws Throwable {
		new WebDriverWait(driver, 3000).until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='el-tooltip round-btn actionBtn item'])[1]")));
		WebElement ele = driver.findElement(By.xpath("(//button[@class='el-tooltip round-btn actionBtn item'])[1]"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", ele);
		
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='el-radio__inner'])[2]")));
		driver.findElement(By.xpath("(//span[@class='el-radio__inner'])[2]")).click();
		driver.findElement(By.xpath("//button/span[text()='Next']")).click();
		driver.findElement(By.xpath("(//i[@class='el-select__caret el-input__icon el-icon-arrow-up'])[4]")).click();
		driver.findElement(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span[text()='master']")).click();

		driver.findElement(By.xpath("(//span[@class='el-input__suffix-inner'])[5]")).click();
		String policyName = CreatePolicyStepDef.policyName;
		List<WebElement> list1 = driver
				.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span"));
		try {
		for (WebElement ele1 : list1) {
			String dropdownValue = ele1.getText();
			if (dropdownValue.equalsIgnoreCase(policyName)) {
				ele1.click();
			}
		}}catch(StaleElementReferenceException e) {
			
		}

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//span[text()='Scan'])[1]")).click();
	}

	@When("^wait until the scan completion of source code$")
	public void wait_until_the_scan_completion_of_source_code() throws Throwable {


		// New added condition

//		  final Timer t = new Timer(); 
//		  
//		  t.schedule(new TimerTask() {
//		  
//		  @Override public void run() { 
//			  
//			  		
//		  	} 
//		  }, 0 ,2000);

		while (true) {
			if (Utility.isElementPresent(By.xpath("(//a/span[@class='titlelink-style capitalize'])[1]"))) {
				// System.out.println("View is present");
				break;
			} else {
				 //System.out.println("View is not present");
			}

		}

		// ProjectStepDef.isElementPresent(By.xpath("(//a/p[@class='p-style
		// titlelink-style capitalize'])[1]"));
		for (int j = 0; j < 5; j++)
			try {
				new WebDriverWait(driver, 2000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a/span[@class='titlelink-style capitalize'])[1]")));
				WebElement elementName = driver
						.findElement(By.xpath("(//a/span[@class='titlelink-style capitalize'])[1]"));
				Thread.sleep(2000);
				elementName.click();
				break;
			} catch (StaleElementReferenceException e) {
				e.toString();
				System.out.println("Stale element error, trying ::  ");
			}

		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(searchSourceProject1);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a/span[@class='titlelink-style capitalize color-red'])[1]")).click();
		Thread.sleep(2000);
		driver.navigate().back();

		/*
		 * Thread.sleep(1000);
		 * driver.findElement(By.xpath("//input[@type='search']")).clear();
		 * Thread.sleep(1000);
		 * driver.findElement(By.xpath("//input[@type='search']")).sendKeys(
		 * searchSourceProject1);
		 * driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);
		 * 
		 * WebDriverWait wait = new WebDriverWait(driver, 1000);
		 * wait.until(ExpectedConditions .visibilityOfElementLocated(By.
		 * xpath("(//a/span[@class='titlelink-style capitalize color-red'])[2]")));
		 * 
		 * Utility.retryingFindClick(By.
		 * xpath("(//a/span[@class='titlelink-style capitalize' or @class='titlelink-style capitalize color-red'])[2]"
		 * )); //driver.findElement(By.
		 * xpath("(//a/span[@class='titlelink-style capitalize color-red'])[2]")).click(
		 * ); Thread.sleep(2000); driver.navigate().back();
		 */
	}
	

	@When("^Select the project \"([^\"]*)\" to generate report$")
	public void select_the_project_to_generate_report(String arg1) throws Throwable {
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(arg1);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);
	}

	@Then("^generate the report and download the pdf report$")
	public void generate_the_report_and_download_the_pdf_report() throws Throwable {
		Thread.sleep(1000);

		driver.findElement(By.xpath("(//span[@class='el-checkbox__inner'])[4]")).click();
		new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[3]")));
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[@class='el-dropdown-menu__item']/div[1]")));

		WebElement button = driver.findElement(By.xpath("//ul/li[@class='el-dropdown-menu__item']/div[1]"));
		if (button.isDisplayed()) {
			String buttonText = button.getText();
			if (buttonText.equalsIgnoreCase("Download")) {
				Thread.sleep(1000);
				button.click();
			} else if (buttonText.equalsIgnoreCase("Generate Reports")) {
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", button);				
				WebDriverWait wait = new WebDriverWait(driver, 5);
				if (wait.until(ExpectedConditions.textToBePresentInElement(button, "Download"))) {
					executor.executeScript("arguments[0].click();", button);				
					
				}
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


	@Then("^download the csv report$")
	public void download_the_csv_report() throws Throwable {
		Thread.sleep(5000);
		//	driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
		//	driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);

			WebElement element= driver.findElement(By.xpath("(//button[@type='button'])[3]"));
			element.click();
			Thread.sleep(1000);
			Actions act = new Actions(driver);
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

	@Then("^download the xml report$")
	public void download_the_xml_report() throws Throwable {
		Thread.sleep(5000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
        Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//li[@class='el-dropdown-menu__item']/div[text()='Download']"));
		act.moveToElement(ele).click().build().perform();

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

	@Then("^download the json report$")
	public void download_the_json_report() throws Throwable {
		Thread.sleep(5000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
        Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//li[@class='el-dropdown-menu__item']/div[text()='Download']"));
		act.moveToElement(ele).click().build().perform();

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
			
			if(ele1.getText().equalsIgnoreCase("JSON")) {
				ele1.click();
			}
		}		driver.findElement(
				By.xpath("//button[@class='el-button el-button--primary']/span[normalize-space()='Confirm']")).click();

	}



	@When("^select the projects to group scan$")
	public void select_the_projects_to_group_scan() throws Throwable {
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		driver.findElement(By.xpath(
				"(//p[normalize-space(text()) = 'empty_Project'])[2]/preceding::span[@class='el-checkbox__inner'][1]"))
				.click();
		Thread.sleep(1000);

		driver.findElement(By.xpath(
				"(//p[normalize-space(text()) = 'example-python'])[2]/preceding::span[@class='el-checkbox__inner'][1]"))
				.click();
		Thread.sleep(1000);

		driver.findElement(By.xpath(
				"(//p[normalize-space(text()) = 'NestedZipFile'])[2]/preceding::span[@class='el-checkbox__inner'][1]"))
				.click();
		Thread.sleep(1000);

		driver.findElement(By
				.xpath("(//p[normalize-space(text()) = 'jfinal'])[2]/preceding::span[@class='el-checkbox__inner'][1]"))
				.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("(//button[@tabindex='0'])[1]")).click();
		Thread.sleep(1000);

		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()='Group Scan']")).click();

		Thread.sleep(3000);
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		Thread.sleep(1000);

		String emptyProjectName = driver.findElement(By.xpath("//div[text()='empty_Project'][@class='cell']"))
				.getText();
		if (emptyProjectName.equalsIgnoreCase("empty_Project")) {
			String uploadProjectError = driver.findElement(By.xpath(
					"//div[@class='cell']/div[normalize-space()='Upload is empty, please upload a file to scan']"))
					.getText();
			assertEquals(uploadProjectError, "Upload is empty, please upload a file to scan");
		}

		new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(By.xpath(
				"(//span[@class='el-input__suffix-inner']/i[@class='el-select__caret el-input__icon el-icon-arrow-up'])[5]")))
				.click();
		Thread.sleep(1000);

		List<WebElement> list = driver
				.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
		for (WebElement ele : list) {
			if (ele.getText().equalsIgnoreCase("1.0")) {
				ele.click();
			}
		}
		new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(By.xpath(
				"(//span[@class='el-input__suffix-inner']/i[@class='el-select__caret el-input__icon el-icon-arrow-up'])[7]")))
				.click();
		Thread.sleep(1000);

		List<WebElement> list1 = driver
				.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
		for (WebElement ele : list1) {
			if (ele.getText().equalsIgnoreCase("1.0")) {
				ele.click();
			}
		}

//		     String CIProjectName = driver.findElement(By.xpath("//div[text()='jfinal'][@class='cell']")).getText();
//			 if(CIProjectName.equalsIgnoreCase("jfinal")) {
//				 String CIProjectError = driver.findElement(By.xpath("//div[@class='cell']/div[normalize-space()='Upload is empty, please upload a file to scan']")).getText();
//				 assertEquals(CIProjectError,"This is a CI project, you can't trigger a scan here");
//			 } 
		
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='el-button el-button--primary'])[2]")));
		driver.findElement(By.xpath("(//button[@class='el-button el-button--primary'])[2]")).click();

	}

	@Then("^validate the projects details$")
	public void validate_the_projects_details() throws Throwable {

	}
	
	

	@When("^cancel the scan$")
	public void cancel_the_scan() throws Throwable {
		if (driver.findElement(By.xpath("(//button[text()='Cancel'])[1]")).isDisplayed()) {
			driver.findElement(By.xpath("(//button[text()='Cancel'])[1]")).click();
		}
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//button[@class='el-button el-button--default el-button--small el-button--primary ']/span[normalize-space(text()='OK')]"))
				.click();
		driver.switchTo().window(parentWindowHandler);
	}

	@Then("^Validate the scan status$")
	public void validate_the_scan_status() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div/p[@class='category p-style capitalize'])[1]")));

		String scanStatus = driver.findElement(By.xpath("(//div/p[@class='category p-style capitalize'])[1]"))
				.getText();
		Assert.assertEquals(scanStatus, "Cancelled");
	}

	/*
	 * public static boolean isElementPresent(By by) { try { driver.findElement(by);
	 * return true; } catch (NoSuchElementException e) { return false; } }
	 */
	
	@When("^Click on add project$")
	public void click_on_add_project() throws Throwable {
	    driver.findElement(By.xpath("//button[text()='Add Projects']")).click();
	}

	/*
	 * @When("^Upload project$") public void upload_project() throws Throwable {
	 * driver.findElement(By.xpath("//button[text()='New Project']")).click(); new
	 * WebDriverWait(driver,
	 * 1000).until(ExpectedConditions.presenceOfElementLocated(By.
	 * xpath("//span[text()='Upload Project']")));
	 * driver.findElement(By.xpath("//input[@name='name']")).sendKeys(
	 * "example-pip-travis-master"); driver.findElement(By.
	 * xpath("(//i[@class='el-select__caret el-input__icon el-icon-arrow-up'])[2]"))
	 * .click(); List<WebElement> list1 = driver.findElements(By.
	 * xpath("(//ul[@class='el-scrollbar__view el-select-dropdown__list'])[3]"));
	 * for(WebElement ele:list1) { String projectType = ele.getText();
	 * Thread.sleep(1000);
	 * driver.findElement(By.xpath("//li/span[text()='Source Code']")).click(); }
	 * driver.findElement(By.xpath("//input[@name='version']")).sendKeys("1.0");
	 * WebElement uploadClick =
	 * driver.findElement(By.xpath("(//button/span[normalize-space()='Create'])[1]")
	 * ); Thread.sleep(3000); uploadClick.click();
	 * driver.findElement(By.xpath("(//input[@name='searchRepo'])[2]")).sendKeys(
	 * "example-pip-travis-master");
	 * driver.findElement(By.xpath("(//button[text()='Search'])[2]")).click();
	 * driver.findElement(By.xpath("//a/p[text()='example-pip-travis-master']")).
	 * click();
	 * driver.findElement(By.xpath("//button[text()='New Upload']")).click();
	 * driver.findElement(By.xpath(
	 * "(//div[@class='el-input']/input[@class='el-input__inner'])[3]")).sendKeys(
	 * "1.1"); WebElement upload
	 * =driver.findElement(By.xpath("//i[@class='el-icon-upload']"));
	 * JavascriptExecutor executor = (JavascriptExecutor)driver;
	 * executor.executeScript("arguments[0].click();", upload); Thread.sleep(2000);
	 * 
	 * StringSelection ss = new
	 * StringSelection("C:\\Users\\Pooja\\Downloads\\jfinal-master.zip");
	 * Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	 * Thread.sleep(2000);
	 * 
	 * Robot robot = new Robot(); robot.keyPress(KeyEvent.VK_ENTER);
	 * robot.keyRelease(KeyEvent.VK_ENTER); robot.keyPress(KeyEvent.VK_CONTROL);
	 * robot.keyPress(KeyEvent.VK_V); robot.keyRelease(KeyEvent.VK_V); //
	 * robot.keyRelease(KeyEvent.VK_CONTROL); // robot.keyPress(KeyEvent.VK_ENTER);
	 * // robot.keyRelease(KeyEvent.VK_ENTER);
	 * 
	 * // driver.findElement(By.xpath("(//span[text()='Save'])[1]")).click(); }
	 */

	@When("^Add project$")
	public void add_project() throws Throwable {
		Thread.sleep(1000);
	    driver.findElement(By.xpath("//input[@type='search']")).sendKeys("scantist3/example-pip-travis");
		Thread.sleep(5000);
	    driver.findElement(By.xpath("(//button[text()='Search'])[1]")).click();
	    
	    new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//div/span[text()='scantist3/example-pip-travis']")));
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//i[@class='fas fa-plus']")).click();
	    driver.findElement(By.xpath("//button/span[text()='Add']")).click();
	    driver.findElement(By.xpath("//button/span[text()='Not now']")).click();
	}
	
	@Then("^Validate the added project$")
	public void validate_the_added_project() throws Throwable {
	    
	}

	@When("^Add project \"([^\"]*)\"$")
	public void add_project(String arg1) throws Throwable {
		Thread.sleep(1000);
	    driver.findElement(By.xpath("//input[@type='search']")).clear();
	    driver.findElement(By.xpath("//input[@type='search']")).sendKeys("scantist3/"+arg1+"");
		Thread.sleep(5000);
	    driver.findElement(By.xpath("(//button[text()='Search'])[1]")).click();
	    
	   // new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//div/span[text()='scantist3/'"+arg1+"")));
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//i[@class='fas fa-plus']")).click();
	    driver.findElement(By.xpath("//button/span[text()='Add']")).click();
	    driver.findElement(By.xpath("//button/span[text()='Not now']")).click();
	}
	
}
