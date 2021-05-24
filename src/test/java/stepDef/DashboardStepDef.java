package stepDef;

import java.util.List;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DashboardStepDef {

	private WebDriver driver;

	public String userName;
	public String expectedUsername;
	String plan, expectedPlan;
	String component, expectedComponent;
	String vulnerabilities, expectedVulnerabilities;
	String compliance, expectedCompliance;
	String projectRisk, expectedprojectRisk;
	String riskOverTime, expectedriskOverTime;

	public DashboardStepDef() {
		this.driver = Hook.getDriver();
	}

	@When("^click on button to close slidebar menu$")
	public void click_on_button_to_close_slidebar_menu() throws Throwable {
		Thread.sleep(3000);

		if (driver.findElements(By.xpath("//button[@class='btn btn-fill btn-icon']")).size() > 0) {

			driver.findElement(By.xpath("//button[@class='btn btn-fill btn-icon']")).click();
		} else {
			// System.out.println("*****Element not found1******");
		}
	}

	@When("^Click on button to open menu$")
	public void click_on_button_to_open_menu() throws Throwable {
		Thread.sleep(2000);

		if (driver.findElements(By.xpath("//button[@class='btn btn-fill btn-icon']")).size() > 0) {
			driver.findElement(By.xpath("//button[@class='btn btn-fill btn-icon']")).click();
		} else {
			// System.out.println("******Element not found2******");
		}
	}

	@Then("^validate the open menu$")
	public void validate_the_open_menu() throws Throwable {
		if (driver.findElements(By.xpath("//ul[@class='nav']")).size() > 0) {
			List<WebElement> menuList = driver.findElements(By.xpath("//ul[@class='nav']"));
			for (WebElement menu : menuList) {
				int i = 0;
				menu = menuList.get(i);
				System.out.println(menu.getText());
			}
		}
	}

	@When("^Get the username of user$")
	public void get_the_username_of_user() throws Throwable {
		userName = driver.findElement(By.xpath("//h4[@class='card-title org-title']")).getText();
		expectedUsername = "scantist3";
	}

	@Then("^validate the username$")
	public void validate_the_username() throws Throwable {
		if (userName.equalsIgnoreCase(expectedUsername)) {
			System.out.println("\n *********Username showing as expected*********** :" + userName);
		} else {
			System.out.println("**********Username not showing as expected*********");
		}
	}

	/*
	 * @When("^Get the plan of user$") public void get_the_plan_of_user() throws
	 * Throwable { plan =
	 * driver.findElement(By.xpath("//a[text()='Enterprise']")).getText();
	 * expectedPlan = "Enterprise";
	 * 
	 * }
	 * 
	 * @Then("^validate the user plan$") public void validate_the_user_plan() throws
	 * Throwable { assertEquals(plan, expectedPlan);
	 * System.out.println("\n ***********User plan matching************ : " + plan);
	 * }
	 */
	
	@When("^Get the component of account$")
	public void get_the_component_of_account() throws Throwable {
		component = driver.findElement(By.xpath("(//div[text()='Components'])[1]")).getText();
		expectedComponent = "Components";
	}

	@Then("^validate the total component$")
	public void validate_the_total_component() throws Throwable {
		assertEquals(component, expectedComponent);
		String componentCount = driver.findElement(By.xpath("(//div[@class='align-center total-style'])[1]"))
				.getText();
		if (driver.findElement(By.xpath("(//div[@class='align-center total-style'])[2]")) != null) {

			System.out.println("\n ***********Components are  present************ : " + componentCount);

		}
	}

	@When("^Get the total count of vulnerability$")
	public void get_the_total_count_of_vulnerability() throws Throwable {
		vulnerabilities = driver.findElement(By.xpath("(//div[text()='Vulnerabilities'])[1]")).getText();
		expectedVulnerabilities = "Vulnerabilities";
	}

	@Then("^validate the total count of vulnerability$")
	public void validate_the_total_count_of_vulnerability() throws Throwable {
		assertEquals(vulnerabilities, expectedVulnerabilities);
		String vulnerabilityCount = driver.findElement(By.xpath("(//div[@class='align-center total-style'])[2]"))
				.getText();
		if (driver.findElement(By.xpath("(//div[@class='align-center total-style'])[3]")) != null) {

			System.out.println("\n ***********Vulnerabilities are  present************ : " +"\n"+ vulnerabilityCount);

		}
	}

	@When("^Get the total count of compliance$")
	public void get_the_total_count_of_compliance() throws Throwable {
		compliance = driver.findElement(By.xpath("(//div[text()='Compliance'])[1]")).getText();
		expectedCompliance = "Compliance";
	}

	@Then("^validate the total count of compliance$")
	public void validate_the_total_count_of_compliance() throws Throwable {
		assertEquals(compliance, expectedCompliance);
		String complianceCount = driver.findElement(By.xpath("(//div[@class='align-center total-style'])[3]"))
				.getText();
		if (driver.findElement(By.xpath("(//div[@class='align-center total-style'])[3]")) != null) {

			System.out.println("\n ***********Compliance are  present************ : "+"\n" + complianceCount);

		}
	}
	

	@When("^Get the project with risk information$")
	public void get_the_project_with_risk_information() throws Throwable {
		projectRisk = driver.findElement(By.xpath("//h4[@class='card-title'][text()='Top 10 Projects with risk']")).getText();
		expectedprojectRisk = "Top 10 Projects with risk";
	}

	@Then("^Validate the project with risk information$")
	public void validate_the_project_with_risk_information() throws Throwable {
		assertEquals(projectRisk, expectedprojectRisk);
		if (driver.findElement(By.xpath("(//div[@class='el-row-padding el-row'])[1]")) != null) {

			System.out.println("\n ***********Project with risk information present************");

			WebElement table = driver.findElement(By.xpath("(//div[@class='el-row-padding el-row'])[1]"));

			List<WebElement> allRows = table.findElements(By.tagName("tr"));

			for (WebElement row : allRows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));

				for (WebElement cell : cells) {
					System.out.println(cell.getText());

				}
			}
		}
	}

	@When("^Get the risk over the time information$")
	public void get_the_risk_over_the_time_information() throws Throwable {
		riskOverTime = driver.findElement(By.xpath("//h4[@class='card-title'][text()='Organizational risk over time']")).getText();
		expectedriskOverTime = "Organizational risk over time";
	}

	@Then("^Validate the risk over the time information$")
	public void validate_the_risk_over_the_time_information() throws Throwable {
		assertEquals(riskOverTime, expectedriskOverTime);

		if (driver.findElement(By.xpath("//canvas[@id='line-chart']")) != null) {

			System.out.println("\n ***********Organizational Risk over time Graph is present************\n");
		}
	}

}
