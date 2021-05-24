package stepDef;

import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utility.TokenGenerator;

public class TagStepDef {

	private WebDriver driver;

	public TagStepDef() {
		this.driver = Hook.getDriver();
	}
	
	@When("^Click on tag setting$")
	public void click_on_tag_setting() throws Throwable {
		driver.findElement(By.xpath("(//p[@class='notification']/b[@class='caret'])[3]")).click();
		driver.findElement(By.xpath("//ul/li/a[text()='Organization']")).click();
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[text()='Tags']")));
		driver.findElement(By.xpath("//ul/li[text()='Tags']")).click();
	}

	@Then("^validate the tag setting page$")
	public void validate_the_tag_setting_page() throws Throwable {
		String pageTitle = driver.findElement(By.xpath("//a[text()='Tags']")).getText();
		String expectedPageTitle = "Tags";
		assertEquals(pageTitle, expectedPageTitle);
	}

	@When("^Enter the tag \"([^\"]*)\"$")
	public void enter_the_tag(String arg1) throws Throwable {
		driver.findElement(By.xpath("//input[@name='tag name']")).clear();
		driver.findElement(By.xpath("//input[@name='tag name']")).sendKeys(arg1);
		driver.findElement(By.xpath("//button/span[text()='Create Tag']")).click();
	}

	@Then("^Validate the tag$")
	public void validate_the_tag() throws Throwable {

		String tagCreated = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Tag created successfully']"))).getText();
	    if (tagCreated.contains("Tag created successfully")) {
	        System.out.println("\nTag Created");
	    } else {
	        System.out.println("Something wrong..!!");
	    }
	}

	@Then("^Validate the duplicate tag$")
	public void validate_the_duplicate_tag() throws Throwable {
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		
		String duplicateTag = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Tag name has been used in this organization']"))).getText();
	    if (duplicateTag.contains("Tag name has been used in this organization")) {
	        System.out.println("\n"+duplicateTag);
	    } else {
	        System.out.println("Something wrong..!!");
	    }
	    
		driver.findElement(By.xpath("//button[text()='Ok']")).click();
	}

	@When("^Modify the tag$")
	public void modify_the_tag() throws Throwable {
		driver.findElement(By.xpath("(//div[text()='testTag']/following::div/i[1])[1]")).click();
	}

	@Then("^Validate the modified tag$")
	public void validate_the_modified_tag() throws Throwable {
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("1234");
		driver.findElement(By.xpath("//span[text()='Update']")).click();

		String modifyTag = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Tag name updated successfully']"))).getText();
	    if (modifyTag.contains("Tag name updated successfully")) {
	        System.out.println("\n"+modifyTag);
	    } else {
	        System.out.println("Something wrong..!!");
	    }
	    		
	}

	@When("^Delete the tag$")
	public void delete_the_tag() throws Throwable {
		driver.findElement(By.xpath("(//div[text()='testTag']/following::div/i[2])[1]")).click();

	}

	@Then("^Validate the deleted tag$")
	public void validate_the_deleted_tag() throws Throwable {
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		
		new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Ok']"))).getText();    
		driver.findElement(By.xpath("//span[normalize-space()='Ok']")).click();

		String deleteTag = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Tag deleted successfully']"))).getText();
	    if (deleteTag.contains("Tag deleted successfully")) {
	        System.out.println("\n"+deleteTag);
	    } else {
	        System.out.println("Something wrong..!!");
	    }
	    		
	}

	/*
	 * @When("^enter empty tag$") public void enter_empty_tag() throws Throwable {
	 * driver.findElement(By.xpath("//button[text()='Create Tag']")).click(); }
	 * 
	 * @Then("^validate the validation error for blank tag field$") public void
	 * validate_the_validation_error_for_blank_tag_field() throws Throwable { String
	 * deleteTag = new WebDriverWait(driver,
	 * 1000).until(ExpectedConditions.visibilityOfElementLocated(By.
	 * xpath("//p[text()='Tag deleted successfully']"))).getText(); if
	 * (deleteTag.contains("Tag deleted successfully")) {
	 * System.out.println("\n"+deleteTag); } else {
	 * System.out.println("Something wrong..!!"); } }
	 * 
	 * 
	 */
	
	@When("^remove project \"([^\"]*)\"$")
	public void remove_project(String arg1) throws Throwable {
		driver.findElement(By.xpath("(//input[@placeholder='Search a Project'])[1]")).clear();
		driver.findElement(By.xpath("(//input[@placeholder='Search a Project'])[1]")).sendKeys(arg1);
		Thread.sleep(5000);
	    driver.findElement(By.xpath("(//button[text()='Search'])[1]")).click();
	    new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='el-button el-tooltip setting-btn item el-button--danger el-button--mini'])[1]")));
		driver.findElement(By.xpath("(//button[@class='el-button el-tooltip setting-btn item el-button--danger el-button--mini'])[1]")).click();
		driver.findElement(By.xpath("(//button/span[normalize-space()='OK'])[2]")).click();
	}


	@Then("^Validate the removed project$")
	public void validate_the_removed_project() throws Throwable {
		String deleteProject = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Your project is deleted']"))).getText();
	    if (deleteProject.contains("Your project is deleted")) {
	    } else {
	        System.out.println("Something wrong..!!");
	    }  
	}


}
