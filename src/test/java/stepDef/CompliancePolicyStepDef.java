package stepDef;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CompliancePolicyStepDef {

	private WebDriver driver;

	public CompliancePolicyStepDef() {
		this.driver = Hook.getDriver();
	}

	@When("^Click on CompliancePolicy menu$")
	public void click_on_CompliancePolicy_menu() throws Throwable {
		driver.findElement(By.xpath("//p[text()='Compliance']")).click();

	}

	@Then("^validate the CompliancePolicy page$")
	public void validate_the_CompliancePolicy_page() throws Throwable {
		Thread.sleep(1000);
		driver.navigate().refresh();
		
		Thread.sleep(1000);
		String pageTitle = driver.findElement(By.xpath("//a[text()='Compliance']")).getText();
		String expectedPageTitle = "Compliance";
		assertEquals(pageTitle, expectedPageTitle);
	}

	@When("^enter the project name \"([^\"]*)\" and hit enter$")
	public void enter_the_project_name_and_hit_enter(String searchProject) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(searchProject);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);
	}

	@Then("^validate the compliance policy details$")
	public void validate_the_compliance_policy_details() throws Throwable {
		String projectWisePolicyDetails = driver.findElement(By.xpath("//table[@class='el-table__body']")).getText();
		System.out.println("Project Policy Details :-" + projectWisePolicyDetails);
	}

	@When("^Click on result button$")
	public void click_on_result_button() throws Throwable {
		driver.findElement(By.xpath("//a/p[text()='Result']")).click();
		Thread.sleep(2000);
		driver.navigate().back();
	}

	@When("^Clcik on view button$")
	public void clcik_on_view_button() throws Throwable {
		driver.findElement(By.xpath("//a/p[text()='View']")).click();

	}

}
