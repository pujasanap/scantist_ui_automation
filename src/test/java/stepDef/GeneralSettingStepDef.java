package stepDef;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GeneralSettingStepDef {

	private WebDriver driver;

	public GeneralSettingStepDef() {
		this.driver = Hook.getDriver();
	}
	
	
	@When("^Click on organization setting$")
	public void click_on_organization_setting() throws Throwable {
		driver.findElement(By.xpath("(//p[@class='notification']/b[@class='caret'])[3]")).click();
		driver.findElement(By.xpath("//ul/li/a[text()='Organization']")).click();
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[text()='General']")));
		driver.findElement(By.xpath("//ul/li[text()='General']")).click();
	}
	
	@Then("^validate the general setting page$")
	public void validate_the_general_setting_page() throws Throwable {
		String pageTitle = driver.findElement(By.xpath("//a[text()='General']")).getText();
		String expectedPageTitle = "General";
		assertEquals(pageTitle, expectedPageTitle);
	}
	
	@When("^Click on description to update the organization information$")
	public void click_on_description_to_update_the_organization_information() throws Throwable {
		driver.findElement(By.xpath("//textarea")).clear();
		driver.findElement(By.xpath("//textarea")).sendKeys("Updated default organization");
	}

	@Then("^Click to update button$")
	public void click_to_update_button() throws Throwable {
	    driver.findElement(By.xpath("//button[text()='Update']")).click();
	    String organizationUpdate = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Updated successfully']"))).getText();
	    if (organizationUpdate.contains("Updated successfully")) {
	        System.out.println("\nInformation " + organizationUpdate);
	    } else {
	        System.out.println("Something wrong..!!");
	    }
	}


	
}
