package stepDef;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LicenseDetailsStepDef {

	private WebDriver driver;

	public LicenseDetailsStepDef() {
		this.driver = Hook.getDriver();
	}

	@Then("^Redirect on License scan result page$")
	public void redirect_on_License_scan_result_page() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='License Details']")));
		String pageTitle = driver.findElement(By.xpath("//a[text()='License Details']")).getText();
		System.out.println("*****pageTitle*****" + pageTitle);
		Assert.assertEquals(pageTitle, "License Details");
	}

	@When("^Download reports in pdf,xml,json and csv format$")
	public void download_reports_in_pdf_xml_json_and_csv_format() throws Throwable {
		driver.findElement(By.xpath("//button[@class='round-btn downloadBtn']")).click();
		driver.findElement(By.xpath("(//ul[@id='dropdown']/li/a)[1]")).click();

		driver.findElement(By.xpath("//button[@class='round-btn downloadBtn']")).click();
		driver.findElement(By.xpath("(//ul[@id='dropdown']/li/a)[2]")).click();

		driver.findElement(By.xpath("//button[@class='round-btn downloadBtn']")).click();
		driver.findElement(By.xpath("(//ul[@id='dropdown']/li/a)[3]")).click();

		driver.findElement(By.xpath("//button[@class='round-btn downloadBtn']")).click();
		driver.findElement(By.xpath("(//ul[@id='dropdown']/li/a)[4]")).click();

	}

	@When("^Search for license to view License issue details of license$")
	public void search_for_license_to_view_License_issue_details_of_license() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='search'])[1]")).sendKeys("Apache License 2.0");
		driver.findElement(By.xpath("(//input[@type='search'])[1]")).sendKeys(Keys.ENTER);
	}

	@When("^Get the license details and click on view button$")
	public void get_the_license_details_and_click_on_view_button() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]")));
		WebElement element = driver.findElement(By.xpath("(//table/tbody/tr[@class='el-table__row'])[1]"));
		String licenseDetails = element.getText();
		System.out.println("\n*****License Details*****" + licenseDetails);
		driver.findElement(By.xpath("//a[text()='View']")).click();
	}

	@Then("^Redirect on License issue detail page$")
	public void redirect_on_License_issue_detail_page() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='License Issue Detail']")));
		String pageTitle = driver.findElement(By.xpath("//a[text()='License Issue Detail']")).getText();
		System.out.println("\n*****pageTitle*****" + pageTitle);
		Assert.assertEquals(pageTitle, "License Issue Detail");
	}

	@When("^Get the license issue details information$")
	public void get_the_license_issue_details_information() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='card issue-card']")));
		String licenseIssueDetails = driver.findElement(By.xpath("//div[@class='card issue-card']")).getText();
		System.out.println("\n*****licenseIssueDetails*****" + licenseIssueDetails);
	}

	@When("^Get the license attribute$")
	public void get_the_license_attribute() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='card license-card'])[1]")));
		String allowedLicenseAttribute = driver.findElement(By.xpath("(//div[@class='card license-card'])[1]"))
				.getText();
		System.out.println("\n*****allowedLicenseAttribute*****" + allowedLicenseAttribute);

		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='card license-card'])[2]")));
		String requiredLicenseAttribute = driver.findElement(By.xpath("(//div[@class='card license-card'])[2]"))
				.getText();
		System.out.println("\n*****requiredLicenseAttribute*****" + requiredLicenseAttribute);

		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='card license-card'])[3]")));
		String restrictedLicenseAttribute = driver.findElement(By.xpath("(//div[@class='card license-card'])[3]"))
				.getText();
		System.out.println("\n*****restrictedLicenseAttribute*****" + restrictedLicenseAttribute);

	}

	@Then("^save the issue action$")
	public void save_the_issue_action() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys("medium");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("To be fixed");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("scantist3");
		driver.findElement(By.xpath("//textarea[@type='text']")).sendKeys("No Comment");
		driver.findElement(By.xpath("//button[@class='round-btn saveBtn pull-right']")).click();

	}

}
