package stepDef;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.Constant;

public class RegisterStepDef {

	public static String username;
	public static String email;
	public static String password;
	public static String confirmPswd;

	public WebDriver driver;

	public RegisterStepDef() {
		driver = Hook.getDriver();
	}

	@Given("^user is  on homepage$")
	public void user_is_on_homepage() throws Throwable {

		driver.get(Constant.WEB_URL);
	}

	@When("^user click on create account$")
	public void user_click_on_create_account() throws Throwable {
		WebElement element =driver.findElement(By.xpath("//*[text() = 'Create Account']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);

	}

	@When("^enter the less than four character username \"([^\"]*)\"$")
	public void enter_the_less_than_four_character_username(String shortUsername) throws Throwable {
		driver.findElement(By.xpath("//input[@placeholder='Enter User Name']")).sendKeys(shortUsername);
	}

	@Then("^display error message for short username$")
	public void display_error_message_for_short_username() throws Throwable {
		String shortUsername = driver
				.findElement(By.xpath("//div/small[text()='The user name field must be at least 4 characters']"))
				.getText();
		String errorMessageOfShortUsername = "The user name field must be at least 4 characters";
		assertEquals(shortUsername, errorMessageOfShortUsername);
	}

	@When("^enter the blank username \"([^\"]*)\"$")
	public void enter_the_blank_username(String blankUsername) throws Throwable {
		driver.findElement(By.xpath("//input[@placeholder='Enter User Name']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Enter User Name']")).sendKeys(blankUsername);
	}

	@Then("^display error message for blank username$")
	public void display_error_message_for_blank_username() throws Throwable {
		String blankUsername = driver.findElement(By.xpath("//*[text() = 'The user name field is required']"))
				.getText();
		String errorMessageOfBlankUsername = "The user name field is required";
		assertEquals(blankUsername, errorMessageOfBlankUsername);
	}

	@When("^enter the special char in username \"([^\"]*)\"$")
	public void enter_the_special_char_in_username(String invalidUsername) throws Throwable {
		driver.findElement(By.xpath("//input[@placeholder='Enter User Name']")).clear();

		driver.findElement(By.xpath("//input[@placeholder='Enter User Name']")).sendKeys(invalidUsername);
	}

	@Then("^display error message for invalid username$")
	public void display_error_message_for_invalid_username() throws Throwable {
		String invalidUsername = driver.findElement(By.xpath(
				"//*[text() = 'The user name field may contain alpha-numeric characters as well as dashes and underscores']"))
				.getText();
		String errorMessageOfInvalidUsername = "The user name field may contain alpha-numeric characters as well as dashes and underscores";
		assertEquals(invalidUsername, errorMessageOfInvalidUsername);
	}

	@When("^enter the invalid email id \"([^\"]*)\"$")
	public void enter_the_invalid_email_id(String invalidEmailID) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@name='email'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@name='email'])[2]"))
				.sendKeys(invalidEmailID);
	}

	@Then("^display error message for invalid email id$")
	public void display_error_message_for_invalid_email_id() throws Throwable {
		Thread.sleep(1000);

		String invalidEmailID = driver
				.findElement(By.xpath("//small[text()='The email field must be a valid email']")).getText();
		String errorMessageOfInvalidID = "The email field must be a valid email";
		assertEquals(invalidEmailID, errorMessageOfInvalidID);
	}

	@When("^enter the blank email id \"([^\"]*)\"$")
	public void enter_the_blank_email_id(String blankEmailID) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@name='email'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@name='email'])[2]"))
				.sendKeys(blankEmailID);
	}

	@Then("^display error message for blank email id field$")
	public void display_error_message_for_blank_email_id_field() throws Throwable {
		Thread.sleep(1000);

		String blankEmailID = driver
				.findElement(By.xpath("//small[text()='The email field is required']")).getText();
		String errorMessageOfBlankEmailID = "The email field is required";
		assertEquals(blankEmailID, errorMessageOfBlankEmailID);
	}

	@When("^enter the password with less than eight character \"([^\"]*)\"$")
	public void enter_the_password_with_less_than_character(String shortPassword) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@name='password'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@name='password'])[2]"))
				.sendKeys(shortPassword);
	}

	@Then("^display error message for short password$")
	public void display_error_message_for_short_password() throws Throwable {
		String shortPassword = driver
				.findElement(By.xpath("//small[text()='The password field must be at least 8 characters']")).getText();
		String errorMessageOfshortPassword = "The password field must be at least 8 characters";
		assertEquals(shortPassword, errorMessageOfshortPassword);
	}

	@When("^enter the blank password \"([^\"]*)\"$")
	public void enter_the_blank_password(String blankPassword) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@name='password'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@name='password'])[2]"))
				.sendKeys(blankPassword);
	}

	@Then("^display error message for blank password field$")
	public void display_error_message_for_blank_password_field() throws Throwable {
		String blankPassword = driver
				.findElement(By.xpath("//small[text()='The password field is required']")).getText();
		String errorMessageOfBlankPassword = "The password field is required";
		assertEquals(blankPassword, errorMessageOfBlankPassword);
	}

	@When("^enter the blank confirm password \"([^\"]*)\"$")
	public void enter_the_blank_confirm_password(String blankConfirmPassword) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='repeatPassword']")).clear();
		driver.findElement(By.xpath("//input[@name='repeatPassword']"))
				.sendKeys(blankConfirmPassword);
	}

	@Then("^display error message for blank confirm password field$")
	public void display_error_message_for_blank_confirm_password_field() throws Throwable {
		String blankConfirmPassword = driver
				.findElement(By.xpath("//small[normalize-space()='The repeatPassword field is required']")).getText();
		String errorMessageOfBlankConfirmPassword = "The repeatPassword field is required";
		assertEquals(blankConfirmPassword, errorMessageOfBlankConfirmPassword);
	}

	@When("^enter the password \"([^\"]*)\" and confirm password \"([^\"]*)\"$")
	public void enter_the_password_and_confirm_password(String password, String confirmPassword) throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@name='password'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@name='password'])[2]"))
				.sendKeys(password);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='repeatPassword']")).clear();
		driver.findElement(By.xpath("//input[@name='repeatPassword']"))
				.sendKeys(confirmPassword);
	}

	@Then("^display error message for unmatched password$")
	public void display_error_message_for_unmatched_password() throws Throwable {
		String unmatchPassword = driver
				.findElement(By.xpath("//small[normalize-space()='The repeatPassword confirmation does not match']")).getText();
		String errorMessageOfunmatchPassword = "The repeatPassword confirmation does not match";
		assertEquals(unmatchPassword, errorMessageOfunmatchPassword);
	}

	@When("^get the placeholders of all fields$")
	public void get_the_placeholders_of_all_fields() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='Enter User Name']")).clear();
		username = driver.findElement(By.xpath("//input[@placeholder='Enter User Name']")).getAttribute("placeholder");
		// System.out.println("****The username field placeholder
		// printed****==>"+username);
		driver.findElement(By.xpath("//input[@placeholder='Enter email']")).clear();
		email = driver.findElement(By.xpath("//input[@placeholder='Enter email']")).getAttribute("placeholder");
		// System.out.println("\n****The email field placeholder printed****==>"+email);
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).clear();
		password = driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).getAttribute("placeholder");
		// System.out.println("\n****The password field placeholder
		// printed****==>"+password);
		driver.findElement(By.xpath("//input[@placeholder='Confirm Password']")).clear();
		confirmPswd = driver.findElement(By.xpath("//input[@placeholder='Confirm Password']"))
				.getAttribute("placeholder");
		// System.out.println("\n****The confirm password field placeholder
		// printed****==>"+confirmPswd);

	}

	@Then("^verify the all placeholders$")
	public void verify_the_all_placeholders() throws Throwable {
		assertEquals(username, "Enter User Name");
		assertEquals(email, "Enter email");
		assertEquals(password, "Enter Password");
		assertEquals(confirmPswd, "Confirm Password");
	}

	@When("^Open yopmail in new tab and copy mail paste in email field$")
	public void open_yopmail_in_new_tab_and_copy_mail_paste_in_email_field() throws Throwable {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('http://m.yopmail.com/en/email-generator.php','_blank');"); // ArrayList<String>
																									// tabs = new																		// ArrayList<String>
																									// (driver.getWindowHandles());
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(1));
		driver.get("http://m.yopmail.com/en/email-generator.php");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login']")));
		WebElement email = driver.findElement(By.id("login"));

		Actions act = new Actions(driver);
		act.moveToElement(email).doubleClick().build().perform();
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
		Thread.sleep(3000);
		driver.close();
		Thread.sleep(1000);

		driver.switchTo().window(tabs.get(0));

	}

	@When("^enter the username and email and password \"([^\"]*)\" and confirm password \"([^\"]*)\"$")
	public void enter_the_username_and_email_and_password_and_confirm_password(String password1, String password2)
			throws Throwable {

		Thread.sleep(1000);

		Random r = new Random(); // just create one and keep it around
		String alphabet = "abcdefghijklmnopqrstuvwxyz";

		final int N = 10;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		String randomName = sb.toString();

		// System.out.println(randomName);

		driver.findElement(By.xpath("//input[@placeholder='Enter User Name']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Enter User Name']")).sendKeys(randomName);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@name='email'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@name='email'])[2]"))
				.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		Thread.sleep(1000);

		driver.findElement(By.xpath("(//input[@name='password'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@name='password'])[2]"))
				.sendKeys(password1);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//input[@name='repeatPassword']")).clear();
		driver.findElement(By.xpath("//input[@name='repeatPassword']"))
				.sendKeys(password2);

	}

	@When("^click on By signing up, I agree to Scantist's Terms and Conditions\\.$")
	public void click_on_By_signing_up_I_agree_to_Scantist_s_Terms_and_Conditions() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@class='el-checkbox__inner']")).click();

	}

	@When("^click on create account and click on terms and condition link$")
	public void click_on_create_account_and_click_on_terms_and_condition_link() throws Throwable {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Create']")).click();
	}

	@Then("^display message for successfully account created$")
	public void display_message_for_successfully_account_created() throws Throwable {
		new WebDriverWait(driver, 5000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Verification e-mail has been sent.' or text()='Registered successfully']")));
	    String mailSent = driver.findElement(By.xpath("//p[text()='Verification e-mail has been sent.' or text()='Registered successfully']")).getText();
	    if (mailSent.contains("Verification e-mail has been sent.")) {
	        System.out.println("\n"+mailSent);
	    } else if(mailSent.contains("Registered successfully")) {
	        System.out.println("\n"+mailSent);
	    }else {
	        System.out.println("Something Wrong....!!!");
	    }
	}

}
