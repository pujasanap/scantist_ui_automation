package stepDef;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AccessTokenStepDef {
	
	private WebDriver driver;

	public AccessTokenStepDef() {
		this.driver = Hook.getDriver();
	}

	@When("^Click on access token setting$")
	public void click_on_access_token_setting() throws Throwable {
		driver.findElement(By.xpath("(//p[@class='notification']/b[@class='caret'])[3]")).click();
		driver.findElement(By.xpath("//ul/li/a[text()='Organization']")).click();
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[text()='Access Token']")));
		driver.findElement(By.xpath("//ul/li[text()='Access Token']")).click();
	}

	@Then("^validate the access token setting page$")
	public void validate_the_access_token_setting_page() throws Throwable {
		String pageTitle = driver.findElement(By.xpath("//a[text()='Access Token']")).getText();
		String expectedPageTitle = "Access Token";
		assertEquals(pageTitle, expectedPageTitle);
	}

	@When("^Enter the access token \"([^\"]*)\"$")
	public void enter_the_access_token(String arg1) throws Throwable {
		driver.findElement(By.xpath("//input[@name='access token']")).clear();
		driver.findElement(By.xpath("//input[@name='access token']")).sendKeys(arg1);
		driver.findElement(By.xpath("//button/span[text()='Create Token']")).click();
	}

	@Then("^Validate the access token$")
	public void validate_the_access_token() throws Throwable {

		String tokenCreated = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Be sure to copy over the access token immediately. You will not be able to see it again once you navigate away.']"))).getText();
	    if (tokenCreated.contains("Be sure to copy over the access token immediately. You will not be able to see it again once you navigate away.")) {
	        System.out.println("\nToken Created");
	    } else {
	        System.out.println("Something wrong..!!");
	    }
	
	}

	@Then("^Validate the duplicate access token$")
	public void validate_the_duplicate_access_token() throws Throwable {
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		
		String duplicateToken = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='This token name already exists']"))).getText();
	    if (duplicateToken.contains("This token name already exists")) {
	        System.out.println("\n"+duplicateToken);
	    } else {
	        System.out.println("Something wrong..!!");
	    }
	    
		driver.findElement(By.xpath("//button[text()='Ok']")).click();

	
	}

	@When("^Delete the access token$")
	public void delete_the_access_token() throws Throwable {
		driver.findElement(By.xpath("//div[text()='QWERTYUI']/following::td[3]/div/i")).click();

	}

	@Then("^Validate the delete token$")
	public void validate_the_delete_token() throws Throwable {
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		
		new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Ok']"))).getText();    
		driver.findElement(By.xpath("//span[normalize-space()='Ok']")).click();

		String deleteToken = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Access token deleted successfully']"))).getText();
	    if (deleteToken.contains("Access token deleted successfully")) {
	        System.out.println("\n"+deleteToken);
	    } else {
	        System.out.println("Something wrong..!!");
	    }
	    		
	}
	
	@When("^enter empty token$")
	public void enter_empty_token() throws Throwable {
	    
	}

	@Then("^validate the validation error for blank field$")
	public void validate_the_validation_error_for_blank_field() throws Throwable {
	    
	}
	
	@When("^enter the invalid token \"([^\"]*)\"$")
	public void enter_the_invalid_token(String arg1) throws Throwable {
		driver.findElement(By.xpath("//input[@name='access token']")).clear();
		driver.findElement(By.xpath("//input[@name='access token']")).sendKeys(arg1);
	}

	@Then("^validate the valiation error for invalid token$")
	public void validate_the_valiation_error_for_invalid_token() throws Throwable {
		String invalidtoken = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//small[normalize-space()='The access token field may only contain alpha-numeric characters']"))).getText();
	    if (invalidtoken.contains("The access token field may only contain alpha-numeric characters")) {
	        System.out.println("\nInvalid token validation error showing");
	    } else {
	        System.out.println("Something wrong..!!");
	    }
	}
	
}
