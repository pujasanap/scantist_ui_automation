package stepDef;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.Utility;
import utils.dbConnect;
import utils.onPrem;
import utils.prodDB;

public class ProjectDetailsStepDef {

	private static WebDriver driver;

	public ProjectDetailsStepDef() {
		this.driver = Hook.getDriver();
	}

	@Given("^clear the project name from serach box$")
	public void clear_the_project_name_from_serach_box() throws Throwable {
		Thread.sleep(1000);
		
			driver.findElement(By.xpath("//input[@type='search']")).clear();
		
	}

	@When("^Click on project to go project details page$")
	public void click_on_project_to_go_project_details_page() throws Throwable {
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("(//a/p[normalize-space()='example-pip-travis'])[2]")));
		driver.findElement(By.xpath("(//a/p[normalize-space()='example-pip-travis'])[2]")).click();
	}

	@Then("^validate project details page$")
	public void validate_project_details_page() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='navigation']")));

		String breadcrumb = driver.findElement(By.xpath("//div[@role='navigation']")).getText();
//	    if(breadcrumb != null) {
//	    String projectname = breadcrumb.substring(10,35);
//	    System.out.println("Project Name:-"+projectname);
//	    }else {
//	    	Thread.sleep(1000);
//	    }

		String pageTitle = driver.findElement(By.xpath("//a[text()='Project details']")).getText();
		Assert.assertEquals(pageTitle, "Project details");

	}

	@When("^Add tag to project$")
	public void add_tag_to_project() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("(//div[@class='card-content detail-height']/div/div/div/span/i[@class='far fa-edit'])[2]"))
				.click();

		String parentHandle = driver.getWindowHandle();
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);

			driver.findElement(By.xpath("(//span/i[@class='el-select__caret el-input__icon el-icon-arrow-up'])[7]"))
					.click();
			List<WebElement> list = driver
					.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
			for (WebElement ele : list) {
				if (ele.getText().equalsIgnoreCase("tag1")) {
					ele.click();
				}
			}

			driver.findElement(By.xpath("(//button/span[text()='Save'])[1]")).click();
		}

	}

	@When("^Click on Action button$")
	public void click_on_Action_button() throws Throwable {
		new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button/span[normalize-space()='Actions'])[1]")));
		driver.findElement(By.xpath("(//button/span[normalize-space()='Actions'])[1]")).click();

	}

	@When("^Click on the scan button$")
	public void click_on_the_scan_button() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@class='el-dropdown-menu el-popper']/div/li/div")));

		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='el-dropdown-menu el-popper']/div/li/div"));

		for (WebElement ele : list) {
			if (ele.getText().equalsIgnoreCase("Scan")) {
				ele.click();
			}
		}

		Thread.sleep(3000);
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}

		driver.switchTo().window(subWindowHandler);
		WebDriverWait wait1 = new WebDriverWait(driver, 120);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='el-radio__inner'])[2]")));
		driver.findElement(By.xpath("(//span[@class='el-radio__inner'])[2]")).click();
		driver.findElement(By.xpath("//button/span[text()='Next']")).click();

		Thread.sleep(1000);

		driver.findElement(
				By.xpath("/html/body/div[1]/div[5]/div/div[2]/div/div[1]/div/form/div[5]/div/div/div[1]/span/span/i"))
				.click();
		List<WebElement> list1 = driver
				.findElements(By.xpath("(//ul[@class='el-scrollbar__view el-select-dropdown__list'])[8]/li/span"));
		for (WebElement ele : list1) {
			if (ele.getText().equalsIgnoreCase("master")) {
				Thread.sleep(1000);
				ele.click();
				break;
			}
		}

		driver.findElement(By.xpath("//label[text()='Policy']/following::span[@class='el-input__suffix-inner']/i"))
				.click();
		// String policyName=CreatePolicyStepDef.policyName;
		// System.out.println("Dropdown policy**********"+policyName);
//			 List<WebElement> list2 = driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span"));
//			    for(WebElement ele1:list2) {
//			    	String dropdownValue = ele1.getText();
//			    	if(dropdownValue.equalsIgnoreCase(policyName)) {
//			    		ele1.click();
//			    	}
//			    }

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//span[text()='Scan'])[1]")).click();
	}

	@Then("^Validate the scan started$")
	public void validate_the_scan_started() throws Throwable {
		Thread.sleep(2000);

		String scanStatus = driver.findElement(By.xpath("(//tr[@class='el-table__row'])[1]")).getText();

		System.out.println("\nCurrent scan =>" + "\n" + scanStatus);

		driver.findElement(By.xpath("//h4[text()='Scan Settings']")).click();
		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
	}

	@Then("^Validate the scan completed$")
	public void validate_the_scan_completed() throws Throwable {

		while (true) {
			if (Utility.isElementPresent(By.xpath("(//tr[@class='el-table__row'])[1]"))) {
				try {
					if (Utility.isElementPresent(By.xpath(
							"/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[4]/div/div[2]/div[2]/div/div/div[3]/table/tbody/tr[1]/td[9]/div/div/div"))) {
						//System.out.println("progress bar" + driver.findElement(By.xpath(
							//	"/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[4]/div/div[2]/div[2]/div/div/div[3]/table/tbody/tr[1]/td[9]/div/div/div"))
							//	.getText());
						//System.out.println("View is not present");
					} else {
						//System.out.println("View is present");
						Thread.sleep(2000);
						break;

					}
				} catch (StaleElementReferenceException s) {
					//System.out.println("Exception View is present");
					Thread.sleep(2000);

					break;
				} catch ( NoSuchElementException e) {
					//System.out.println("NoSuchElementException View is present");
					Thread.sleep(2000);

					break;
				}
			}
		}

		Thread.sleep(2000);
		
		driver.navigate().refresh();
		
		Thread.sleep(2000);


		String scanStatus = driver.findElement(By.xpath("(//tr[@class='el-table__row'])[1]")).getText();

		System.out.println("Current scan =>" + "\n" + scanStatus);
		
	}

	@When("^Click on scheduled scan of source code$")
	public void click_on_scheduled_scan_of_source_code() throws Throwable {
		boolean checkbox = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).isSelected();
		if (checkbox == true) {
			System.out.println("\nScheduled scan already selected");
		} else {
			new WebDriverWait(driver, 20).until(
					ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='el-checkbox__inner'])[1]")))
					.click();
		}

	}

	@When("^validate th compliance policy$")
	public void validate_th_compliance_policy() throws Throwable {
		driver.findElement(By.xpath("//button/span[text()='Action']")).click();

		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='el-dropdown-menu el-popper']/li/div"));
		for (WebElement ele : list) {
			if (ele.getText().equalsIgnoreCase("New Policy")) {
				ele.click();
			}

			// driver.navigate().back();

		}
		driver.findElement(By.xpath("//button/span[text()='Action']")).click();

		List<WebElement> list2 = driver.findElements(By.xpath("//ul[@class='el-dropdown-menu el-popper']/li/div"));
		Thread.sleep(1000);
		for (WebElement ele : list2) {
			if (ele.getText().equalsIgnoreCase("Edit")) {
				ele.click();

				Thread.sleep(1000);
			}
			// driver.navigate().back();
		}
	}

	@When("^Click on upload file$")
	public void click_on_upload_file() throws Throwable {
		driver.findElement(By.xpath("//button[text()='New Upload']")).click();
	}

	@When("^Enter the file version and upload the file$")
	public void enter_the_file_version_and_upload_the_file() throws Throwable {
		String parentHandler = driver.getWindowHandle();
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);

			driver.findElement(By.xpath("(//input[@type='text'])[8]")).sendKeys("1.2");
			driver.findElement(By.xpath("//i[@class='el-icon-upload']")).click();
			Thread.sleep(2000);

			StringSelection ss = new StringSelection("C:\\Users\\Pooja\\Downloads\\jfinal-master.zip");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		}
	}

	@Then("^Click on Save file$")
	public void click_on_Save_file() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='el-button default-btn el-button--primary']/span")).click();
	}

	@Then("^Validate the version file$")
	public void validate_the_version_file() throws Throwable {
		
	}

	@When("^Select the current scan$")
	public void select_the_current_scan() throws Throwable {
		Actions action = new Actions(driver);
		Thread.sleep(3000);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,2500)", "");
		// driver.findElement(By.xpath("(//tr[@class='el-table__row'])[1]/td/div/label/span/span")).click();
	}

	@When("^Click on rescan$")
	public void click_on_rescan() throws Throwable {
		
		new WebDriverWait(driver, 2000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Rescan'])[1]")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement rescan = driver.findElement(By.xpath("(//button[text()='Rescan'])[1]"));
		js.executeScript("arguments[0].click()", rescan);
		System.out.println("\nRescan started");
	}

	@Then("^Validate the latest scan information$")
	public void validate_the_latest_scan_information() throws Throwable {
		while (true) {
			if (Utility.isElementPresent(By.xpath("(//a/p[@class='titlelink-style capitalize'])[1]"))) {
				// System.out.println("View is present");
				break;
			} else {
				// System.out.println("View is not present");
			}

		}

		Thread.sleep(2000);
		String getRow = driver.findElement(By.xpath("(//tr[@class='el-table__row'])[1]")).getText();
		System.out.println("\nlatest scan =>" + getRow);
	}

	@When("^Click on action button$")
	public void click_on_action_button() throws Throwable {

		
	}

	@When("^Click on genarate report$")
	public void click_on_genarate_report() throws Throwable {
			Thread.sleep(5000);
			WebElement checkbox = driver.findElement(By.xpath("(//td[@rowspan='1']/div/label/span)[1]"));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", checkbox);

			driver.findElement(By.xpath("(//button/span[normalize-space()='Action'])[3]")).click();
			List<WebElement> list2 = driver.findElements(By.xpath("//ul[@class='el-dropdown-menu el-popper']/div/li"));
			Thread.sleep(1000);
			for (WebElement ele : list2) {
				if (ele.getText().equalsIgnoreCase("Export")) {
					ele.click();

					Thread.sleep(1000);
				}
			}
		

		Thread.sleep(5000);
		driver.navigate().refresh();

		Thread.sleep(2000);

		driver.navigate().refresh();

		Thread.sleep(1000);

		driver.findElement(By.xpath("//div[@class='breadcrumb-style']")).click();
		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);

					
		
	}
		@Then("^Download json report$")
		public void download_json_report() throws Throwable {
			Thread.sleep(2000);
				WebElement checkbox = driver.findElement(By.xpath("(//td[@rowspan='1']/div/label/span)[1]"));

				JavascriptExecutor js1 = (JavascriptExecutor) driver;
				js1.executeScript("arguments[0].click()", checkbox);
				
				WebElement actionBtn1 = driver.findElement(By.xpath("(//button/span[normalize-space()='Action'])[3]"));

				JavascriptExecutor js2 = (JavascriptExecutor) driver;
				js2.executeScript("arguments[0].click()", actionBtn1);

				Thread.sleep(1000);

				driver.findElement(By.xpath("(//button/span[normalize-space()='Action'])[3]"));

				List<WebElement> list2 = driver
						.findElements(By.xpath("//ul[@class='el-dropdown-menu el-popper']/div/div/li"));
				for (WebElement ele : list2) {
					if (ele.getText().equalsIgnoreCase("JSON")) {
						ele.click();
					}

				}

				
		}

		@Then("^Download xml report$")
		public void download_xml_report() throws Throwable {

			WebElement actionBtn1 = driver.findElement(By.xpath("(//button/span[normalize-space()='Action'])[3]"));

			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].click()", actionBtn1);

			Thread.sleep(1000);

			driver.findElement(By.xpath("(//button/span[normalize-space()='Action'])[3]"));

			List<WebElement> list2 = driver
					.findElements(By.xpath("//ul[@class='el-dropdown-menu el-popper']/div/div/li"));
			for (WebElement ele : list2) {
				if (ele.getText().equalsIgnoreCase("XML")) {
					ele.click();
				}

			}


		}

		@Then("^Download csv report$")
		public void download_csv_report() throws Throwable {
			WebElement actionBtn1 = driver.findElement(By.xpath("(//button/span[normalize-space()='Action'])[3]"));

			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].click()", actionBtn1);

			Thread.sleep(1000);

			driver.findElement(By.xpath("(//button/span[normalize-space()='Action'])[3]"));

			List<WebElement> list2 = driver
					.findElements(By.xpath("//ul[@class='el-dropdown-menu el-popper']/div/div/li"));
			for (WebElement ele : list2) {
				if (ele.getText().equalsIgnoreCase("CSV")) {
					ele.click();
				}

			}

		}

		@Then("^Download pdf report$")
		public void download_pdf_report() throws Throwable {
			Thread.sleep(1000);

			driver.findElement(By.xpath("(//button/span[normalize-space()='Action'])[3]")).click();

			List<WebElement> list3 = driver
					.findElements(By.xpath("//ul[@class='el-dropdown-menu el-popper']/div/div/li"));
			for (WebElement ele : list3) {
				if (ele.getText().equalsIgnoreCase("PDF")) {
					ele.click();
				}

			}

		
		}		
	

	@When("^Click on compare button$")
	public void click_on_compare_button() throws Throwable {
		Thread.sleep(5000);
		WebElement checkbox = driver.findElement(By.xpath(
				"(//span[@class='el-checkbox__inner'])[4]"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", checkbox);
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//span[@class='el-checkbox__inner'])[5]")).click();
		
		Thread.sleep(1000);

		driver.findElement(By.xpath("(//button/span[normalize-space()='Actions'])[2]")).click();

		Thread.sleep(1000);

		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='el-dropdown-menu el-popper']/li/div"));
		for (WebElement ele : list) {
			if (ele.getText().equalsIgnoreCase("Compare")) {
				ele.click();
			}
		}

	}

	@Then("^Validate the compare page$")
	public void validate_the_compare_page() throws Throwable {

		String comparePageTitle = driver.findElement(By.xpath("//a[text()='Scan Comparison']")).getText();
		Assert.assertEquals(comparePageTitle, "Scan Comparison");

		driver.findElement(By.xpath("//div[@class='col-lg-12 breadcrumb-style']")).click();
		Thread.sleep(1000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);

		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Components']")));
		driver.findElement(By.xpath("//div[text()='Components']")).click();

//		dbConnect dbConnect = new dbConnect();
//		dbConnect.deleteUploadFile();
		
//		prodDB prodDB = new prodDB();
//		prodDB.deleteUploadFile();
		
//		onPrem onPrem = new onPrem();
//		onPrem.deleteUploadFile();
	}

}