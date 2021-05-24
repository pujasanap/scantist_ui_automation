package stepDef;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Integrations {
	
	private WebDriver driver;

	public Integrations() {
		this.driver = Hook.getDriver();
	}
	
	@When("^Click on integration setting$")
	public void click_on_integration_setting() throws Throwable {
		driver.findElement(By.xpath("(//p[@class='notification']/b[@class='caret'])[3]")).click();
		driver.findElement(By.xpath("//ul/li/a[text()='Organization']")).click();
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[text()='Integrations']")));
		driver.findElement(By.xpath("//ul/li[text()='Integrations']")).click();
	}

	@Then("^Validate the integration setting page$")
	public void validate_the_integration_setting_page() throws Throwable {
		String pageTitle = driver.findElement(By.xpath("//a[text()='Integrations']")).getText();
		String expectedPageTitle = "Integrations";
		assertEquals(pageTitle, expectedPageTitle);
	}

	@Then("^Validate the status of jira$")
	public void validate_the_status_of_jira() throws Throwable {
		String statusJira = driver.findElement(By.xpath("//div[text()='Connected']")).getText();
		String expectedstatusJira = "Connected";
		assertEquals(statusJira, expectedstatusJira);
	}

	@When("^Click on Reset button$")
	public void click_on_Reset_button() throws Throwable {
	    driver.findElement(By.xpath("//span[text()='Reset']")).click();
	}

	@When("^Enter the server url$")
	public void enter_the_server_url() throws Throwable {
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("https://scantist.atlassian.net");
	    driver.findElement(By.xpath("//button/span[normalize-space()='Next']")).click();
	}

	@Then("^Validate the create integration popup$")
	public void validate_the_create_integration_popup() throws Throwable {
		String jiraIntegration = driver.findElement(By.xpath("//h5[text()='Create JIRA Cloud Integration (Step 2 of 3)']")).getText();
		String expectedJiraIntegration = "Create JIRA Cloud Integration (Step 2 of 3)";
		assertEquals(jiraIntegration, expectedJiraIntegration);
		driver.findElement(By.xpath("//div[@class='close-container']/i")).click();
	}

}
