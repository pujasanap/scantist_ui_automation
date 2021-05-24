package stepDef;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.Utility;

public class CreatePolicyStepDef {

	private WebDriver driver;
	String policyTable, parentHandle;
	public static String policyName;

	public CreatePolicyStepDef() {
		this.driver = Hook.getDriver();
	}

	@When("^Click on Organization menu$")
	public void click_on_Organization_menu() throws Throwable {

		driver.findElement(By.xpath("(//p[@class='notification']/b[@class='caret'])[3]")).click();
		driver.findElement(By.xpath("//ul/li/a[text()='Organization']")).click();
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[text()='Policies']")));
		driver.findElement(By.xpath("//ul/li[text()='Policies']")).click();

	}

	@Then("^validate the organization tab opened$")
	public void validate_the_organization_tab_opened() throws Throwable {

		String policyPageTitle = driver.findElement(By.xpath("//a[text()='Policies']")).getText();
		Assert.assertEquals(policyPageTitle, "Policies");
		System.out.println("\n************************************");
		System.out.println(policyPageTitle + " Page");
		System.out.println("************************************");

	}

	@When("^Get the Organization policy information$")
	public void get_the_Organization_policy_information() throws Throwable {
		policyTable = driver.findElement(By.xpath("//table[@class='el-table__body']")).getText();

	}

	@Then("^Displaying organization policy information$")
	public void displaying_organization_policy_information() throws Throwable {
		//System.out.println("Policy Table ==>" + "\n" + policyTable);

	}

	@When("^Enter policy name \"([^\"]*)\"$")
	public void enter_policy_name(String arg1) throws Throwable {
	   driver.findElement(By.xpath("//input[@type='search']")).sendKeys(arg1);
	}

	@When("^Click on Create Policy$")
	public void click_on_Create_Policy() throws Throwable {
		driver.findElement(By.xpath("//button/span[text()='Create Policy']")).click();

	}

	@When("^Redirect on create policy popup$")
	public void redirect_on_create_policy_popup() throws Throwable {
		parentHandle = driver.getWindowHandle(); // get the current window handle

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}


	
	@When("^Enter policy name$")
	public void enter_policy_name() throws Throwable {
		policyName = Utility.createPolicyname();
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(policyName);
		Thread.sleep(1000);
	}

	@Then("^Validate the duplicate policy$")
	public void validate_the_duplicate_policy() throws Throwable {

		String parentHandle = driver.getWindowHandle();

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		String duplicatePolicy = driver
				.findElement(By.xpath("//h2[text()='Compliance Policy with the same name already exists!']")).getText();
		Assert.assertEquals(duplicatePolicy, "Compliance Policy with the same name already exists!");
		driver.findElement(By.xpath("//button[text()='Ok']")).click();
	}

	@When("^Click on create buton$")
	public void click_on_create_buton() throws Throwable {
		driver.findElement(By.xpath("//button[text()='Create']")).click();
	}

	@Then("^Validate the error message for policy name and description$")
	public void validate_the_error_message_for_policy_name_and_description() throws Throwable {

		new WebDriverWait(driver, 5000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='The policy name field can not be empty']")));
	    String compPolicyValidationError = driver.findElement(By.xpath("//p[text()='The policy name field can not be empty']")).getText();
	    if (compPolicyValidationError.contains("The policy name field can not be empty")) {
	        System.out.println("\n"+compPolicyValidationError);
	    }else {
	        System.out.println("Something Wrong....!!!");
	    }
	
	}

	@When("^Click to select default policy$")
	public void click_to_select_default_policy() throws Throwable {

		if (driver.findElement(By.xpath("//tr[@class='el-table__row']/td/div/a[text()='International']")) != null) {
			if (driver.findElement(By.xpath("//span[@class='el-checkbox__input is-disabled is-checked']"))
					.isSelected()) {

			}
		} else {
			System.out.println("International policy not present");
		}

	}

	@Then("^validate the tooltip of default policy$")
	public void validate_the_tooltip_of_default_policy() throws Throwable {

	}

	@Then("^Click on new policy to assign rule$")
	public void click_on_new_policy_to_assign_rule() throws Throwable {
		driver.navigate().refresh();
		WebElement sCellValue = driver.findElement(By.xpath("//tr/td/div/a/p[text()=" + "'" + policyName + "'" + "]/following::td[3]/div/a/i"));
		//System.out.println(sCellValue);
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(sCellValue));
		sCellValue.click();
	}

	/*
	 * public static String createPolicyname() { String alphabet =
	 * "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	 * 
	 * StringBuilder sb = new StringBuilder();
	 * 
	 * Random random = new Random();
	 * 
	 * int length = 7;
	 * 
	 * for (int i = 0; i < length; i++) {
	 * 
	 * int index = random.nextInt(alphabet.length());
	 * 
	 * char randomChar = alphabet.charAt(index);
	 * 
	 * sb.append(randomChar); }
	 * 
	 * String randomString = sb.toString(); return randomString;
	 * 
	 * }
	 */

}
