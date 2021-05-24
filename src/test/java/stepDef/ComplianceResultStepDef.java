package stepDef;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.Utility;

public class ComplianceResultStepDef {

	private WebDriver driver;

	public ComplianceResultStepDef() {
		this.driver = Hook.getDriver();
	}

	@When("^Click on Compliance Result Policy menu$")
	public void click_on_Compliance_Result_Policy_menu() throws Throwable {
		driver.findElement(By.xpath("//p[text()='Compliance Result']")).click();

	}

	@Then("^validate the Compliance Result Policy page$")
	public void validate_the_Compliance_Result_Policy_page() throws Throwable {
		Thread.sleep(1000);
		String pageTitle = driver.findElement(By.xpath("//a[text()='Compliance Results']")).getText();
		String expectedPageTitle = "Compliance Results";
		assertEquals(pageTitle, expectedPageTitle);

		Actions action = new Actions(driver);
		action.moveByOffset(500, 500).perform();
		Thread.sleep(1000);
		action.click();
	}

	@When("^Get the Compliance Result Policy details$")
	public void get_the_Compliance_Result_Policy_details() throws Throwable {
		String policydetails = driver.findElement(By.xpath("//div[@class='card-content detail-height']")).getText();
		System.out.println(policydetails);
	}

	@Then("^validate the Compliance Result Policy details$")
	public void validate_the_Compliance_Result_Policy_details() throws Throwable {
		new WebDriverWait(driver, 3000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='View']")));
		driver.findElement(By.xpath("//p[text()='View']")).click();
		Thread.sleep(1000);
		driver.navigate().back();

		System.out.println(driver.findElement(By.xpath("//table[@class='el-table__body']")).getText());
		
		WebElement view1 = driver.findElement(By.xpath("(//p[text()='View'])[2]"));
		Utility.retryingFindClick(view1);
		System.out.println(driver.findElement(By.xpath("//div[@class='card-content detail-height']")).getText());
		System.out.println(driver.findElement(By.xpath("//table[@class='el-table__body']")).getText());
		new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[text()='View'])[2]")));
		WebElement view = driver.findElement(By.xpath("(//p[text()='View'])[2]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", view);
		new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Component Details']")));
		String compDetailPageTitle = driver.findElement(By.xpath("//a[text()='Component Details']")).getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(compDetailPageTitle, "Component Details");
		System.out.println("\nComponent Details page Title ==>" + compDetailPageTitle);
	}

}
