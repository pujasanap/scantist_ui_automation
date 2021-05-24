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

public class MemberInvitation {
	
	private WebDriver driver;

	public MemberInvitation() {
		this.driver = Hook.getDriver();
	}

	
	@When("^Click on member setting$")
	public void click_on_member_setting() throws Throwable {
		driver.findElement(By.xpath("(//p[@class='notification']/b[@class='caret'])[3]")).click();
		driver.findElement(By.xpath("//ul/li/a[text()='Organization']")).click();
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[text()='Members']")));
		driver.findElement(By.xpath("//ul/li[text()='Members']")).click();
	}

	@Then("^validate the member setting page$")
	public void validate_the_member_setting_page() throws Throwable {
		String pageTitle = driver.findElement(By.xpath("//a[text()='Members']")).getText();
		String expectedPageTitle = "Members";
		assertEquals(pageTitle, expectedPageTitle);
	    
	}

	@When("^enter the \"([^\"]*)\" member name$")
	public void enter_the_member_name(String arg1) throws Throwable {
		driver.findElement(By.xpath("//input[@placeholder='Email']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("scantist4@gmail.com");
	}

	@Then("^Click on invite member button$")
	public void click_on_invite_member_button() throws Throwable {
		driver.findElement(By.xpath("//button/span[text()='Invite Member']")).click();
		
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		driver.findElement(By.xpath("//span[normalize-space()='Ok']")).click();
		
		String inviteSent = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Invitation sent']"))).getText();
	    if (inviteSent.contains("Invitation sent")) {
	        System.out.println("\n"+inviteSent);
	    } else {
	        System.out.println("Something wrong..!!");
	    }
	}

}
