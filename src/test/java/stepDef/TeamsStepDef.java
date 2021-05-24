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

public class TeamsStepDef {

	private WebDriver driver;

	public TeamsStepDef() {
		this.driver = Hook.getDriver();
	}
	
	@When("^Click on team setting$")
	public void click_on_team_setting() throws Throwable {
		driver.findElement(By.xpath("(//p[@class='notification']/b[@class='caret'])[3]")).click();
		driver.findElement(By.xpath("//ul/li/a[text()='Organization']")).click();
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li[text()='Teams']")));
		driver.findElement(By.xpath("//ul/li[text()='Teams']")).click();
	}

	@Then("^validate the team setting page$")
	public void validate_the_team_setting_page() throws Throwable {
		String pageTitle = driver.findElement(By.xpath("//a[text()='Teams']")).getText();
		String expectedPageTitle = "Teams";
		assertEquals(pageTitle, expectedPageTitle);
	}

	@When("^Click the \"([^\"]*)\" team name$")
	public void click_the_team_name(String arg1) throws Throwable {
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(arg1);
	}

	@Then("^Click on create team button$")
	public void click_on_create_team_button() throws Throwable {
		 driver.findElement(By.xpath("//button/span[text()='Create Team']")).click();
		    String teamAdd = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Successfully added']"))).getText();
		    if (teamAdd.contains("Successfully added")) {
		        System.out.println("\nTeam " + teamAdd);
		    } else {
		        System.out.println("Something wrong..!!");
		    }
		
	}
	
	@Then("^Delete the team$")
	public void delete_the_team() throws Throwable {
			driver.findElement(By.xpath("//p[text()='testTeam']/following::i[@class='el-tooltip el-icon-delete delete-icon action-icon']")).click();
			String subWindowHandler = null;

			Set<String> handles = driver.getWindowHandles();
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()) {
				subWindowHandler = iterator.next();
			}
			driver.switchTo().window(subWindowHandler);
			
			driver.findElement(By.xpath("(//span[normalize-space()='OK'])[3]")).click();
			
		    String teamDeleteMessage = new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Team deleted successfully']"))).getText();
		    if (teamDeleteMessage.contains("Team deleted successfully")) {
		        System.out.println("\n"+teamDeleteMessage);
		    } else {
		        System.out.println("Something wrong..!!");
		    }
		}
	
}
