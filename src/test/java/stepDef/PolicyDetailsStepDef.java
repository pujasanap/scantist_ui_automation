package stepDef;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import utility.Utility;

public class PolicyDetailsStepDef {

	private WebDriver driver;
	String policyName;
	String compName = "click";
	String compName1 = "django";
	String compName2 = "numpy";

	public PolicyDetailsStepDef() {
		this.driver = Hook.getDriver();
	}

	@When("^Get the policy details page information$")
	public void get_the_policy_details_page_information() throws Throwable {
		String policyDetailPageTitle = driver.findElement(By.xpath("//a[text()='Policy Detail']")).getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(policyDetailPageTitle, "Policy Detail");
		System.out.println("Policy Detail page Title ==>" + policyDetailPageTitle);
	}

	@When("^Get the policy name$")
	public void get_the_policy_name() throws Throwable {
		policyName = driver.findElement(By.xpath("(//h4)[1]")).getText();

	}

	@Then("^validate the policy name$")
	public void validate_the_policy_name() throws Throwable {
		//Thread.sleep(1000);
		//System.out.println(policyName);
		//String policyNm = policyName.substring(11, 21);
		//System.out.println("Policy name ==>" + policyNm);

		//SoftAssert soft = new SoftAssert();
		//soft.assertEquals(policyNm, "testPolicy");
	}

	@When("^Click on Denied Add rule$")
	public void click_on_Denied_Add_rule() throws Throwable {
		driver.findElement(By.xpath("(//span[text()='Add Rule'])[1]")).click();
		String parenthandler = driver.getWindowHandle();

		for (String winhandle : driver.getWindowHandles()) {
			driver.switchTo().window(winhandle);
		}
	}

	@When("^Create the rule with single attribute for Component$")
	public void create_the_rule_with_single_attribute_for_Component() throws Throwable {
		Thread.sleep(3000);

	/*	driver.findElement(By.xpath("(//i[@class='el-select__caret el-input__icon el-icon-arrow-up'])[1]")).click();
		List<WebElement> list = driver
				.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span"));
		for (WebElement ele : list) {
			String dropdownValue = ele.getText();
			if (dropdownValue.equalsIgnoreCase("Component")) {
				if(ele.isDisplayed()==true) {
					ele.click();
					break;
					}else {
						driver.findElement(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span")).click();
					}
			}
		}*/

		// driver.findElement(By.xpath("//input[@value='single']")).click();
		driver.findElement(By.xpath("(//i[@class='el-select__caret el-input__icon el-icon-arrow-up'])[2]")).click();

		List<WebElement> list1 = driver
				.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span"));
		for (WebElement ele1 : list1) {
			String dropdownValue = ele1.getText();
			if (dropdownValue.equalsIgnoreCase("Name")) {
				ele1.click();
			}
		}
		driver.findElement(By.xpath("//input[@role='textbox']")).sendKeys(compName);
		String composition = driver.findElement(By.xpath("//label[text()='Composition']/following::div[1]/div"))
				.getText();
		Assert.assertEquals(composition, "Deny whenever component name equals " + compName);
		System.out.println("\nCreated Rule is ==>" + composition);
		driver.findElement(By.xpath("//button[text()='Create']")).click();

	}

	@Then("^Validate the created rule$")
	public void validate_the_created_rule() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='item-style']/span")));
		List<WebElement> list1 = driver.findElements(By.xpath("//div[@class='item-style']/span"));
				
		for (WebElement ele1 : list1) {
			Utility.retryingFindClick(ele1);
			String dropdownValue = ele1.getText();
			
			if (dropdownValue.equalsIgnoreCase("Deny whenever component name equals click")) {
				System.out.println("Validated Created Rule is ==>" +dropdownValue);
			}else {
				System.out.println("not matching");
			}
		}
	}

	@When("^Click on Flagged Add rule$")
	public void click_on_Flagged_Add_rule() throws Throwable {
		driver.findElement(By.xpath("(//span[text()='Add Rule'])[2]")).click();
		String parenthandler = driver.getWindowHandle();

		for (String winhandle : driver.getWindowHandles()) {
			driver.switchTo().window(winhandle);
		}
	}

	@When("^Create the rule with single attribute for Component with flagged policy rule$")
	public void create_the_rule_with_single_attribute_for_Component_with_flagged_policy_rule() throws Throwable {
		Thread.sleep(3000);

		/*driver.findElement(By.xpath("(//i[@class='el-select__caret el-input__icon el-icon-arrow-up'])[1]")).click();
		List<WebElement> list = driver
				.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span"));
		for (WebElement ele : list) {
			String dropdownValue = ele.getText();
			if (dropdownValue.equalsIgnoreCase("Component")) {
				
				if(ele.isDisplayed()==true) {
				ele.click();
				break;
				}else {
					driver.findElement(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span")).click();
				}
			}
		}*/

		// driver.findElement(By.xpath("//input[@value='single']")).click();
		driver.findElement(By.xpath("(//i[@class='el-select__caret el-input__icon el-icon-arrow-up'])[2]")).click();

		List<WebElement> list1 = driver
				.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span"));
		for (WebElement ele1 : list1) {
			String dropdownValue = ele1.getText();
			if (dropdownValue.equalsIgnoreCase("Name")) {
				ele1.click();
			}
		}
		driver.findElement(By.xpath("//input[@role='textbox']")).sendKeys(compName1);
		String composition = driver.findElement(By.xpath("//label[text()='Composition']/following::div[1]/div"))
				.getText();
		Assert.assertEquals(composition, "Flag whenever component name equals " + compName1);
		System.out.println("\nCreated Rule is ==>" + composition);
		driver.findElement(By.xpath("//button[text()='Create']")).click();
	}

	@Then("^Validate the created rule with flagged policy rule$")
	public void validate_the_created_rule_with_flagged_policy_rule() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='item-style']/span")));
		List<WebElement> list1 = driver.findElements(By.xpath("//div[@class='item-style']/span"));
		
		for (WebElement ele1 : list1) {
			Utility.retryingFindClick(ele1);
			String dropdownValue = ele1.getText();
			if (dropdownValue.equalsIgnoreCase("Flag whenever component name equals django")) {
				System.out.println("Validated Created Rule is ==>" + dropdownValue);
			}
		}

	}

	@When("^Click on Approved Add rule$")
	public void click_on_Approved_Add_rule() throws Throwable {
		driver.findElement(By.xpath("(//span[text()='Add Rule'])[3]")).click();
		String parenthandler = driver.getWindowHandle();

		for (String winhandle : driver.getWindowHandles()) {
			driver.switchTo().window(winhandle);
		}
	}

	@When("^Create the rule with single attribute for Component with Approved policy rule$")
	public void create_the_rule_with_single_attribute_for_Component_with_Approved_policy_rule() throws Throwable {
		Thread.sleep(3000);

		/*driver.findElement(By.xpath("(//i[@class='el-select__caret el-input__icon el-icon-arrow-up'])[1]")).click();
		List<WebElement> list = driver
				.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span"));
		for (WebElement ele : list) {
			String dropdownValue = ele.getText();
			if (dropdownValue.equalsIgnoreCase("Component")) {
				
				if(ele.isDisplayed()==true) {
					ele.click();
					break;
					}else {
						driver.findElement(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span")).click();
					}
			}
		}*/

		// driver.findElement(By.xpath("//input[@value='single']")).click();
		(new WebDriverWait(driver, 100)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//i[@class='el-select__caret el-input__icon el-icon-arrow-up'])[2]")));

		driver.findElement(By.xpath("(//i[@class='el-select__caret el-input__icon el-icon-arrow-up'])[2]")).click();

		List<WebElement> list1 = driver
				.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li/span"));
		for (WebElement ele1 : list1) {
			String dropdownValue = ele1.getText();
			if (dropdownValue.equalsIgnoreCase("Name")) {
				ele1.click();
			}
		}
		driver.findElement(By.xpath("//input[@role='textbox']")).sendKeys(compName2);
		String composition = driver.findElement(By.xpath("//label[text()='Composition']/following::div[1]/div"))
				.getText();
		Assert.assertEquals(composition, "Approve whenever component name equals " + compName2);
		System.out.println("\nCreated Rule is ==>" + composition);
		driver.findElement(By.xpath("//button[text()='Create']")).click();
	}

	@Then("^Validate the created rule with Approved policy rule$")
	public void validate_the_created_rule_with_Approved_policy_rule() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='item-style']/span")));
		List<WebElement> list1 = driver.findElements(By.xpath("//div[@class='item-style']/span"));

		for (WebElement ele1 : list1) {
			Utility.retryingFindClick(ele1);
			String dropdownValue = ele1.getText();
			if (dropdownValue.equalsIgnoreCase("Approve whenever component name equals numpy")) {
				System.out.println("Validated Created Rule is ==>" + dropdownValue);
			}
		}
	}

	/*
	 * public Boolean retryingFindClick(WebElement element) { Boolean result =
	 * false; int attempts = 0; while (attempts < 2) { try { WebElement ele = null;
	 * ele.click(); result = true; break; } catch (StaleElementReferenceException e)
	 * { } attempts++; } return result; }
	 */

}
