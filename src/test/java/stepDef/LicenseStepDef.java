package stepDef;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.Utility;

public class LicenseStepDef {

	private WebDriver driver;

	public LicenseStepDef() {
		this.driver = Hook.getDriver();
	}

	@When("^Click on license menu$")
	public void click_on_license_menu() throws Throwable {
		driver.findElement(By.xpath("//p[text()='Licenses']")).click();
	}

	@Then("^validate the license page$")
	public void validate_the_license_page() throws Throwable {
		Thread.sleep(1000);
		String pageTitle = driver.findElement(By.xpath("//a[text()='Licenses']")).getText();
		String expectedPageTitle = "Licenses";
		assertEquals(pageTitle, expectedPageTitle);

		Actions action = new Actions(driver);
		Thread.sleep(1000);
		action.moveByOffset(500, 500).perform();
		Thread.sleep(1000);
		action.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='tab-0']"))).click();
	}

	@When("^get and validate the License Overview section$")
	public void get_and_validate_the_License_Overview_section() throws Throwable {
		if (driver.findElement(By.xpath("(//h5[normalize-space(text()='License Overview')])[1]")).isDisplayed()) {

			WebElement ele1 = driver.findElement(By.xpath("(//span[@class='category'])[1]"));
			String issueFound = ele1.getText();
			if (ele1.isDisplayed()) {

				if (!issueFound.equalsIgnoreCase("No Issues Found")) {

					String issueFoundValue = issueFound.replaceAll("[^0-9]", "");
					Utility.isStringInteger(issueFoundValue);
					System.out.println("*****IssueFoundValue*****" + issueFoundValue);
					if (Integer.parseInt(issueFoundValue) > 0) {
						System.out.println("Issue found value is integer value and greater than zero");
						driver.findElement(By.xpath("//canvas[@id='0-chart']")).isDisplayed();

					}
				} else {

					System.out.println("In Div Showing :- " + issueFound);
					driver.findElement(By.xpath("(//*[@id='Layer_1'])[1]")).isDisplayed();
				}

			}
		}
	}

	@When("^get and validate the License Count Allowed section$")
	public void get_and_validate_the_License_Count_Allowed_section() throws Throwable {
		if (driver.findElement(By.xpath("//h5[text()='License Count']")).isDisplayed()) {

			WebElement allowedSection = driver.findElement(By.xpath("(//div[@class=\"clickable-chart\"])[1]"));
			if (allowedSection.isDisplayed()) {

				String allowedSectionount = allowedSection.getText();
				System.out.println("*****allowedSectionount***** :- " + allowedSectionount);

			}
		}
	}

	@When("^get and validate the License Count FLAGGED section$")
	public void get_and_validate_the_License_Count_FLAGGED_section() throws Throwable {
		if (driver.findElement(By.xpath("//h5[text()='License Count']")).isDisplayed()) {

			WebElement flaggedSection = driver.findElement(By.xpath("(//div[@class=\"clickable-chart\"])[2]"));
			if (flaggedSection.isDisplayed()) {

				String flaggedSectionCount = flaggedSection.getText();
				System.out.println("*****flaggedSectionCount***** :- " + flaggedSectionCount);

			}
		}
	}

	@When("^get and validate the License Count RESTRICTED section$")
	public void get_and_validate_the_License_Count_RESTRICTED_section() throws Throwable {
		if (driver.findElement(By.xpath("//h5[text()='License Count']")).isDisplayed()) {

			WebElement restrictedSection = driver.findElement(By.xpath("(//div[@class=\"clickable-chart\"])[3]"));
			if (restrictedSection.isDisplayed()) {

				String restrictedSectionCount = restrictedSection.getText();
				System.out.println("*****restrictedSectionCount***** :- " + restrictedSectionCount);

			}
		}
	}

	@When("^get and validate the License Count NO POLICY section$")
	public void get_and_validate_the_License_Count_NO_POLICY_section() throws Throwable {
		if (driver.findElement(By.xpath("//h5[text()='License Count']")).isDisplayed()) {

			WebElement nopolicySection = driver.findElement(By.xpath("(//div[@class=\"clickable-chart\"])[4]"));
			if (nopolicySection.isDisplayed()) {

				String nopolicySectionCount = nopolicySection.getText();
				System.out.println("*****nopolicySectionCount***** :- " + nopolicySectionCount);

			}
		}
	}

	@When("^Search for project to view License details of project$")
	public void search_for_project_to_view_License_details_of_project() throws Throwable {
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 1000)");

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[1]")).sendKeys("example-python-master");
		driver.findElement(By.xpath("(//input[@type='search'])[1]")).sendKeys(Keys.ENTER);
	}

	@When("^Get the project details and click on view button$")
	public void get_the_project_details_and_click_on_view_button() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]")));
		WebElement element = driver.findElement(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]"));
		String projectDetails = element.getText();
		System.out.println("\n*****Project Details*****" + projectDetails);
		driver.findElement(By.xpath("//p[text()='View']")).click();
	}

	@Then("^redirect on License scan result page$")
	public void redirect_on_License_scan_result_page() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='License Details']")));
		String pageTitle = driver.findElement(By.xpath("//a[text()='License Details']")).getText();
		System.out.println("*****pageTitle*****" + pageTitle);
		driver.navigate().back();
	}

	@When("^Get the project details with license & click on action button$")
	public void get_the_project_details_with_license_click_on_action_button() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]")));
		WebElement element = driver.findElement(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]"));
		String projectDetails = element.getText();
		System.out.println("\n*****Project Details*****" + projectDetails);
		driver.findElement(By.xpath(
				"(//p[normalize-space(text()='example-python-master')])[19]/preceding::span[@class='el-checkbox__inner'][1]"))
				.click();

	}

	@When("^click on Generate and download pdf report of License$")
	public void click_on_Generate_and_download_pdf_report_of_License() throws Throwable {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[3]")));
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		Thread.sleep(1000);

		WebElement button = driver.findElement(By.xpath("//li[@class='el-dropdown-menu__item']"));
		String buttonText = button.getText();
		if (buttonText.equalsIgnoreCase("Download")) {
			WebElement downloadBtn = driver.findElement(By.xpath("//div[text()='Download']"));
			downloadBtn.click();
		} else if (buttonText.equalsIgnoreCase("Generate Reports")) {
			driver.findElement(By.xpath("//div[text()='Generate Reports']")).click();
			WebDriverWait wait1 = new WebDriverWait(driver, 5);
			if (wait1.until(ExpectedConditions.textToBePresentInElement(button, "Download"))) {
				driver.findElement(By.xpath("//div[text()='Download']")).click();
			}
		}

		/*
		 * Actions act = new Actions(driver); WebElement ele
		 * =driver.findElement(By.xpath(
		 * "//li[@class='el-dropdown-menu__item']/div[text()='Download']"));
		 * act.moveToElement(ele).click().build().perform();
		 */

		String parentWindowHandler = driver.getWindowHandle();
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
		driver.findElement(By.xpath("//span[text()='PDF']")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div[3]/span/button[2]/span")).click();
	}

	@When("^click on download csv report of License$")
	public void click_on_download_csv_report_of_License() throws Throwable {
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//li[@class='el-dropdown-menu__item']/div[text()='Download']"));
		act.moveToElement(ele).click().build().perform();

		String parentWindowHandler = driver.getWindowHandle();
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
		driver.findElement(By.xpath("//span[text()='CSV']")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div[3]/span/button[2]/span")).click();
	}

	@When("^click on download xml report of License$")
	public void click_on_download_xml_report_of_License() throws Throwable {
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//li[@class='el-dropdown-menu__item']/div[text()='Download']"));
		act.moveToElement(ele).click().build().perform();

		String parentWindowHandler = driver.getWindowHandle();
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
		driver.findElement(By.xpath("//span[text()='XML']")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div[3]/span/button[2]/span")).click();
	}

	@When("^click on download json report of License$")
	public void click_on_download_json_report_of_License() throws Throwable {
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//li[@class='el-dropdown-menu__item']/div[text()='Download']"));
		act.moveToElement(ele).click().build().perform();

		String parentWindowHandler = driver.getWindowHandle();
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
		driver.findElement(By.xpath("//span[text()='JSON']")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div[3]/span/button[2]/span")).click();
	}

	@When("^select the by license tab and search for project$")
	public void select_the_by_license_tab_and_search_for_project() throws Throwable {
		driver.findElement(By.xpath("//div[@id='tab-1']")).click();
		driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).sendKeys("ADSL");
	}

	@When("^Get the project details$")
	public void get_the_project_details() throws Throwable {
		Thread.sleep(1000);
		String licenseDetails = driver.findElement(By.xpath("(//tr[@class=\"el-table__row\"])[2]")).getText();
		System.out.println("*****licenseDetails*****" + licenseDetails);
	}

	@When("^Drag the arrow & Click on Project button and scan result button$")
	public void drag_the_arrow_Click_on_Project_button_and_scan_result_button() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='el-table__expand-icon']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@href='/u/scantist3/org/scantist3/projects/2324']")).click();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@class='el-input__inner'])[6]")).sendKeys("ADSL");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='el-table__expand-icon']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='fab fa-wpforms icon-results']")))
				.click();
		Thread.sleep(1000);
		driver.navigate().back();

	}

}
