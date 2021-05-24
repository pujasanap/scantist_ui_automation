package stepDef;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.Constant;
import runner.TestRunner;
import utility.Utility;

public class LoginStepDef {
	// public static WebDriver driver;
	String dashhboardPageTitle;
	String projectPageTitle;
	/*
	 * public ContextSteps contextSteps; public LoginStepDef(ContextSteps
	 * contextSteps) { this.contextSteps = contextSteps; driver =
	 * this.contextSteps.getDriver(); }
	 */

	private WebDriver driver;

	public LoginStepDef() {
		driver = Hook.getDriver();
	}

	@Given("^User is on Home page$")
	public void user_is_on_Home_page() throws Throwable {


	}

	@When("^enter the username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void enter_the_username_and_password(String username, String password) throws Throwable {
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[2]/input"))
				.sendKeys(username);
		;

		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[6]/input"))
				.sendKeys(password);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[2]/button"))
				.click();
	}

	@When("^enter the invalid username \"([^\"]*)\" and valid password \"([^\"]*)\"$")
	public void enter_the_invalid_username_and_valid_password(String invalidUsername, String validPswd)
			throws Throwable {
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[2]/input"))
				.sendKeys(invalidUsername);
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[6]/input"))
				.sendKeys(validPswd);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[2]/button"))
				.click();
	}

	@Then("^redirecting on dashboard page$")
	public void redirecting_on_dashboard_page() throws Throwable {
		new WebDriverWait(driver, 2000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Dashboard']")));
		dashhboardPageTitle = driver.findElement(By.xpath("//a[text()='Dashboard']")).getText();
		String expectedDashboardPagetitle = "Dashboard";
		assertEquals(dashhboardPageTitle, expectedDashboardPagetitle);
	}

	@Then("^logout the user account$")
	public void logout_the_user_account() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/nav/div/div[2]/ul[2]/li[4]/a/p")).click();
		driver.findElement(By.xpath("//ul[2]/li[4]/ul/li[2]/a[text()='Log Out']")).click();
	}

	@Then("^display error message for invalid credentials$")
	public void display_error_message_for_invalid_credentials() throws Throwable {
		Thread.sleep(1000);
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);

		driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']")).click();

		// System.out.println(driver.switchTo().window(parentWindowHandler));
	}

	@When("^enter the valid username \"([^\"]*)\" and invalid password \"([^\"]*)\"$")
	public void enter_the_valid_username_and_invalid_password(String validUsername, String invalidPswd)
			throws Throwable {
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[2]/input"))
				.clear();
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[2]/input"))
				.sendKeys(validUsername);
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[6]/input"))
				.clear();
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[6]/input"))
				.sendKeys(invalidPswd);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[2]/button"))
				.click();
	}

	@When("^enter the invalid username \"([^\"]*)\" and invalid password \"([^\"]*)\"$")
	public void enter_the_invalid_username_and_invalid_password(String invalidUsername, String invalidPswd)
			throws Throwable {
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[2]/input"))
				.clear();
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[2]/input"))
				.sendKeys(invalidUsername);
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[6]/input"))
				.clear();
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[6]/input"))
				.sendKeys(invalidPswd);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[2]/button"))
				.click();
	}

	@When("^enter the not confirm user username\"([^\"]*)\" and password \"([^\"]*)\"$")
	public void enter_the_not_confirm_user_username_and_password(String nonConfirmUser, String nonConfirmUserPswd)
			throws Throwable {
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[2]/input"))
				.clear();
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[2]/input"))
				.sendKeys(nonConfirmUser);
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[6]/input"))
				.clear();
		driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[6]/input"))
				.sendKeys(nonConfirmUserPswd);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[2]/button"))
				.click();
	}

	@Then("^Display message email is not verified$")
	public void display_message_email_is_not_verified() throws Throwable {

		Thread.sleep(1000);
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		Thread.sleep(1000);
		// String emailVerifiedMsg = driver.findElement(By.xpath("//*[text()='E-mail is
		// not verified.']")).getText();
		// String expectedEmailVerifiedMsg = "E-mail is not verified.";
		// (emailVerifiedMsg, expectedEmailVerifiedMsg);
		// driver.findElement(By.xpath("//button[text()='Cancel']")).click();
		// System.out.println(driver.switchTo().window(parentWindowHandler));

	}

	@When("^click on resend verification email$")
	public void click_on_resend_verification_email() throws Throwable {
	
		WebElement element = driver.findElement(By.xpath("//*[text()='Resend Verification Email']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	@Then("^Display Verification email has been sent\\.$")
	public void display_Verification_email_has_been_sent() throws Throwable {

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(
				By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[1]/div/div[2]/div/div/div[2]/button"))).click()
				.build().perform();

		new WebDriverWait(driver, 5000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Verification email has been sent.']")));
		WebElement emailSentMessage1 = driver.findElement(By.xpath("//h2[text()='Verification email has been sent.']"));
		Utility.retryingFindClick(emailSentMessage1);
		String emailSentMessage = null;
		
		boolean staleElement = true; 
		while(staleElement){
		  try{
			 emailSentMessage=emailSentMessage1.getText();
		     staleElement = false;

		  } catch(StaleElementReferenceException e){
		    staleElement = true;
		  }
		}
		
		String expectedEmailSentMessage = "Verification email has been sent.";
		assertEquals(emailSentMessage, expectedEmailSentMessage);
		new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Ok']")));
		WebElement okButton= driver.findElement(By.xpath("//button[text()='Ok']"));
		Utility.retryingFindClick(okButton);
	}

	@When("^Do not enter email address and password$")
	public void do_not_enter_email_address_and_password() throws Throwable {

		driver.findElement(By.xpath("//input[@type='email']")).clear();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(" ");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(" ");

	}

	@When("^click on signin button$")
	public void click_on_signin_button() throws Throwable {
		new WebDriverWait(driver, 5000).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Then("^display validation error for email and password$")
	public void display_validation_error_for_email_and_password() throws Throwable {
		Thread.sleep(1000);
		String emailErrorMsg = driver.findElement(By.xpath("//small[text()='The email field is required']")).getText();
		String ExpectedemailErrorMsg = "The email field is required";
		assertEquals(emailErrorMsg, ExpectedemailErrorMsg);
		Thread.sleep(1000);
		String passwordErrorMsg = driver
				.findElement(By.xpath("//small[normalize-space()='The password field is required']")).getText();
		String ExpectedpasswordErrorMsg = "The password field is required";
		assertEquals(passwordErrorMsg, ExpectedpasswordErrorMsg);

	}

	@When("^click on login with github$")
	public void click_on_login_with_github() throws Throwable {
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Login with Github']")));
		driver.findElement(By.xpath("//div[text()='Login with Github']")).click();
	}

	@When("^enter username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void enter_username_and_password(String githubUsername, String githubPassword) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='login']")).sendKeys(githubUsername);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(githubPassword);
		driver.findElement(By.xpath("//input[@name='commit']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('https://github.com','_blank');"); // ArrayList<String>
																									// tabs = new
																									// ArrayList<String>
																									// (driver.getWindowHandles());
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(1));
		driver.get("https://github.com");
		driver.findElement(By.xpath("//summary[@aria-label='View profile and more']")).click();
	    driver.findElement(By.xpath("(//button[@type='submit'][normalize-space()='Sign out'])[2]")).click();
		Thread.sleep(3000);
		driver.close();
		Thread.sleep(1000);

		driver.switchTo().window(tabs.get(0));
	}

	@Then("^redirect on dashboard page$")
	public void redirect_on_dashboard_page() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Dashboard']")));
		dashhboardPageTitle = driver.findElement(By.xpath("//a[text()='Dashboard']")).getText();
		String expectedDashboardPagetitle = "Dashboard";
		assertEquals(dashhboardPageTitle, expectedDashboardPagetitle);
		WebDriverWait wait1 = new WebDriverWait(driver, 10);

		Boolean isPresent = driver.findElements(By.xpath("//div[@class='enjoyhint_close_btn'][1]")).size() > 0;
		if (isPresent) {
			wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='enjoyhint_close_btn'][1]")));
			driver.findElement(By.xpath("//div[@class='enjoyhint_close_btn'][1]")).click();
		}

		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@class='notification'])[5]")));
		driver.findElement(By.xpath("(//p[@class='notification'])[5]")).click();
		Thread.sleep(1000);
		if (driver.findElement(By.xpath("//a[text()='Log Out']")).isDisplayed()) {
			driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		}
		// System.out.print("*****logout*****");

	}

	@Then("^redirect on project page$")
	public void redirect_on_project_page() throws Throwable {
		Boolean isPresent1 = driver.findElements(By.xpath("//a[text()='Projects']")).size() > 0;
		
		if(isPresent1) {
		projectPageTitle = driver.findElement(By.xpath("//a[text()='Projects']")).getText();
		String expectedProjectPagetitle = "Projects";
		assertEquals(projectPageTitle, expectedProjectPagetitle);
		WebDriverWait wait1 = new WebDriverWait(driver, 10);

		Boolean isPresent = driver.findElements(By.xpath("//div[@class='enjoyhint_close_btn']")).size() > 0;
		if (isPresent) {
			wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='enjoyhint_close_btn']")));
			driver.findElement(By.xpath("//div[@class='enjoyhint_close_btn']")).click();
		}}

		Thread.sleep(3000);
		driver.findElement(By.xpath("(//p[@class='notification'])[5]")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		// System.out.print("*****logout*****");
	}

	@When("^click on login with gitlab$")
	public void click_on_login_with_gitlab() throws Throwable {
		new WebDriverWait(driver, 200).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Login with Gitlab']")));
		driver.findElement(By.xpath("//div[text()='Login with Gitlab']")).click();
	}

	@When("^enter gitlab username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void enter_gitlab_username_and_password(String gitlabUsername, String gitlabPassword) throws Throwable {
		new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_login']")));
		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys(gitlabUsername);
		new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='user_password']")));

		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(gitlabPassword);
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
		
		
	}

	@When("^click on login with bitbucket$")
	public void click_on_login_with_bitbucket() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Login with Bitbucket']")).click();

	}

	@When("^enter bitbucket username \"([^\"]*)\" and bitbucket password \"([^\"]*)\"$")
	public void enter_bitbucket_username_and_bitbucket_password(String bitbuketUsername, String bitbucketPassword)
			throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(bitbuketUsername);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[text()='Continue']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(bitbucketPassword);
		driver.findElement(By.xpath("//button[@id='login-submit']")).click();
	}
}
