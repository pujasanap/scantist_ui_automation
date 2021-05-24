package stepDef;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.Utility;

public class ComponentScanResultStepDef {

	private WebDriver driver;
	private String allowedLicenseAttributeDetails,restrictedLicenseAttributeDetails,requiredLicenseAttributeDetails;
	
	public ComponentScanResultStepDef() {
		this.driver = Hook.getDriver();
	}
	
	@When("^search for project \"([^\"]*)\" in searchbox$")
	public void search_for_project_in_searchbox(String arg1) throws Throwable {
		driver.findElement(By.xpath("(//input[@placeholder='Search Record'])[1]")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@placeholder='Search Record'])[1]")).sendKeys(arg1);
		driver.findElement(By.xpath("(//input[@placeholder='Search Record'])[1]")).sendKeys(Keys.ENTER);
	}

	@When("^Get component statistics information$")
	public void get_component_statistics_information() throws Throwable {
		if (driver.findElement(By.xpath("//h5[@class='card-title'][text()='Component Statistics']")).isDisplayed()) {
			if (driver.findElement(By.xpath(
					"/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/div[2]/table/tbody/tr[1]/td[1]/span"))
					.isDisplayed()) {
				if (driver.findElement(By.xpath("(//table/tbody/tr[1]/td[2])[1]")).isDisplayed()) {
					String Component_Statistics = driver.findElement(By.xpath("(//table/tbody/tr[1]/td[2])[1]"))
							.getText();
					System.out.println("\nComponent Found ==>" + Component_Statistics);
				}
			}

			if (driver.findElement(By.xpath("(//span[normalize-space(text()='Vulnerable Components Found')])[22]"))
					.isDisplayed()) {
				if (driver.findElement(By.xpath("(//table/tbody/tr[2]/td[2])[1]")).isDisplayed()) {
					String Vulnerable_Components = driver.findElement(By.xpath("(//table/tbody/tr[2]/td[2])[1]"))
							.getText();
					System.out.println("\nVulnerable Components Found ==>" + Vulnerable_Components);
				}
			}

			if (driver.findElement(By.xpath("(//td/span[normalize-space(text()='Components with License Issues')])[3]"))
					.isDisplayed()) {
				if (driver.findElement(By.xpath("(//table/tbody/tr[3]/td[2])[1]")).isDisplayed()) {
					String Component_Statistics = driver.findElement(By.xpath("(//table/tbody/tr[3]/td[2])[1]"))
							.getText();
					System.out.println("\nComponents with License Issues ==>" + Component_Statistics);
				}
			}
		}
	}

	@When("^Get Component Vulnerability information$")
	public void get_Component_Vulnerability_information() throws Throwable {
		if (driver.findElement(By.xpath("(//h5[normalize-space(text()='Component Vulnerability')])[2]")).isDisplayed()) {

			WebElement ele1 = driver.findElement(By.xpath("(//span[@class='category'])[1]"));
			String issueFound = ele1.getText();
			if (ele1.isDisplayed()) {

				if (!issueFound.equalsIgnoreCase("\n No Issues Found")) {
                    
					String issueFoundValue = issueFound.replaceAll("[^0-9]", "");
					Utility.isStringInteger(issueFoundValue);
					if (Integer.parseInt(issueFoundValue) > 0) {
					
						driver.findElement(By.xpath("//canvas[@id='library-chart']")).isDisplayed();

					}
				} else {

					System.out.println("\n"+issueFound);
					driver.findElement(By.xpath("(//*[@id=\"Layer_1\"])[1]")).isDisplayed();
				}

			}
		}

	}

	@When("^Get Component Licenses information$")
	public void get_Component_Licenses_information() throws Throwable {
		if (driver.findElement(By.xpath("(//h5[normalize-space(text()='Component Licenses')])[3]")).isDisplayed()) {

			WebElement ele1 = driver.findElement(By.xpath("(//div/div[1]/span)[7]"));
			String issueFound = ele1.getText();
			if (ele1.isDisplayed()) {

				if (!issueFound.equalsIgnoreCase("No Issues Found")) {

					String issueFoundValue = issueFound.replaceAll("[^0-9]", "");
					Utility.isStringInteger(issueFoundValue);
					System.out.println("\nIssueFoundValue ==>" + issueFoundValue);
					if (Integer.parseInt(issueFoundValue) > 0) {
						// System.out.println("Issue found value is integer value and greater than
						// zero");
						driver.findElement(By.xpath("//canvas[@id='library-chart'][2]")).isDisplayed();
					}
				} else {
					driver.findElement(By.xpath("(//div/div[1]/span)[7]")).isDisplayed();
					driver.findElement(By.xpath("//*[@id='Layer_1']")).isDisplayed();
					System.out.println("\nComponent License No Issue Found");

				}
			}
		}
	}

	@When("^Get Cloned Files Analysis information$")
	public void get_Cloned_Files_Analysis_information() throws Throwable {
		if (driver.findElement(By.xpath("(//h5[normalize-space(text()='Cloned Files Analysis')])[4]")).isDisplayed()) {

			WebElement ele1 = driver.findElement(By.xpath("(//div/div[1]/span)[8]"));
			String issueFound = ele1.getText();
			if (ele1.isDisplayed()) {

				if (!issueFound.equalsIgnoreCase("No Issues Found")) {

					String issueFoundValue = issueFound.replaceAll("[^0-9]", "");
					Utility.isStringInteger(issueFoundValue);
					System.out.println("\nIssueFoundValue ==>" + issueFoundValue);
					if (Integer.parseInt(issueFoundValue) > 0) {
						// System.out.println("Issue found value is integer value and greater than
						// zero");
						driver.findElement(By.xpath("//canvas[@id='library-chart'][3]")).isDisplayed();
					}
				} else {
					driver.findElement(By.xpath("(//div/div[1]/span)[8]")).isDisplayed();
					driver.findElement(By.xpath("(//*[@id='Layer_1'])[2]")).isDisplayed();
					System.out.println("\nCloned File Analysis No Issue Found");

				}
			}
		}

	}

	@When("^Get dependency graph information$")
	public void get_dependency_graph_information() throws Throwable {
		if (driver.findElement(By.xpath("(//h4[text()='Dependency Chart'])[1]")).isDisplayed()) {
			driver.findElement(By.xpath("//span[@class='el-checkbox__inner']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//span[@class='el-checkbox__inner']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//span[@class='el-checkbox__inner']")).click();

			if (driver.findElement(By.xpath("(//label[@class='el-form-item__label'])[1]")).isDisplayed()) {
				String component = driver.findElement(By.xpath("(//div[@class='el-form-item__content'])[1]")).getText();
				System.out.println("\n*****Component*****" + "\n"+component);

				if (driver.findElement(By.xpath("(//label[text()='Direct Impact'])[1]")).isDisplayed()) {
					String directImpact = driver.findElement(By.xpath("(//div[@class='summary-column'])[1]")).getText();
					System.out.println("\nDirect Impact ==>" + directImpact);

					/*
					 * if
					 * (driver.findElement(By.xpath("(//label[@class='el-form-item__label'])[3]")).
					 * isDisplayed()) {
					 * driver.findElement(By.xpath("//h5[normalize-space()='Component Licenses']")).
					 * click(); driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
					 * WebDriverWait wait = new WebDriverWait(driver, 1000); WebElement el =
					 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					 * "//span[@class='category'][normalize-space()='proj_3094_1604551859-example-pip-travis-master.zip']"
					 * ))); String dependencyTree = el.getText();
					 * //System.out.println("\n*****DependencyTree*****" + dependencyTree); }
					 */
				}
			}
		}
	}

	@Then("^validate the dependency graph information$")
	public void validate_the_dependency_graph_information() throws Throwable {
		String dependencyChart = driver.findElement(By.xpath("//div[@id='dependency-chart']")).getText();
		System.out.println("\n*****dependencyChart****" + "\n"+dependencyChart);
	}
	
	@When("^verify the sorting of vulnerability$")
	public void verify_the_sorting_of_vulnerability() throws Throwable {
	   

	}

	@When("^search for component \"([^\"]*)\" and enter$")
	public void search_for_component_and_enter(String arg1) throws Throwable {
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(arg1);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(Keys.ENTER);
	}

	@When("^get the component detail$")
	public void get_the_component_detail() throws Throwable {
		String componentDetails = driver.findElement(By.xpath("//tr[@class='el-table__row']")).getText();
		System.out.println("\n****Component Details****"+"\n" +componentDetails);

	}

	@When("^click on vulnerability fiter$")
	public void click_on_vulnerability_fiter() throws Throwable {
    
	}

	@When("^click on download button$")
	public void click_on_download_button() throws Throwable {

		WebElement button = driver.findElement(By.xpath("//button[@class='round-btn downloadBtn']"));
		String buttonText = button.getText();
		if (buttonText.equalsIgnoreCase("Download")) {
			button.click();
		} else if (buttonText.equalsIgnoreCase("Export BOM")) {
			button.click();
			WebDriverWait wait = new WebDriverWait(driver, 5000);
			if (wait.until(ExpectedConditions.textToBePresentInElement(button, "Download"))) {
				button.click();
			}
		}

	}

	@When("^download pdf version$")
	public void download_pdf_version() throws Throwable {
		new WebDriverWait(driver, 2000).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dropdown']/li[4]/a")));
		driver.findElement(By.xpath("//*[@id='dropdown']/li[4]/a")).click();

	}

	@When("^download csv version$")
	public void download_csv_version() throws Throwable {
		driver.findElement(By.xpath("//button[@class='round-btn downloadBtn']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='dropdown']/li[3]/a")).click();
	}

	@When("^download xml version$")
	public void download_xml_version() throws Throwable {
		driver.findElement(By.xpath("//button[@class='round-btn downloadBtn']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dropdown\"]/li[2]/a")).click();
	}

	@When("^download json version$")
	public void download_json_version() throws Throwable {
		driver.findElement(By.xpath("//button[@class='round-btn downloadBtn']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='dropdown']/li[1]/a")).click();
	}

	@When("^click on view button$")
	public void click_on_view_button() throws Throwable {
		driver.findElement(By.xpath("//p[text()='View']")).click();
	}

	@Then("^redirect on individual component details page$")
	public void redirect_on_individual_component_details_page() throws Throwable {
		String compDetailPage = driver.findElement(By.xpath("//a[text()='Component Details']")).getText();
		assertEquals(compDetailPage, "Component Details");
	}

	@When("^Get component information$")
	public void get_component_information() throws Throwable {
		String getComponentDetails1 = driver.findElement(By.xpath("//div[@class='row line-height']")).getText();
		System.out.println("\n***** GetComponentDetails *****" +"\n"+getComponentDetails1);
	}

	@Then("^validate the information of vulnerability of that component$")
	public void validate_the_information_of_vulnerability_of_that_component() throws Throwable {
		String getVulnerabilityDetails = driver.findElement(By.xpath("(//table[@class='el-table__body'])[1]")).getText();
		System.out.println("\n***** getVulnerabilityDetails *****" + "\n" + getVulnerabilityDetails);
	}

	@Then("^validate version history$")
	public void validate_version_history() throws Throwable {
		Utility.pagination_check();
	}

	/*
	 * public void pagination_check() throws InterruptedException { List<WebElement>
	 * pagination =
	 * driver.findElements(By.xpath("(//ul[@class='el-pager'])[2]/li"));
	 * Thread.sleep(5000); if (pagination.size() > 0) {
	 * System.out.println("pagination exists and size=>" + pagination.size()); for
	 * (int i = 1; i <= pagination.size(); i++) { JavascriptExecutor js =
	 * (JavascriptExecutor) driver;
	 * js.executeScript("arguments[0].click();",driver.findElement(By.xpath(
	 * "(//ul[@class='el-pager'])[2]/li[" + i + "]")));
	 * System.out.println(driver.findElement(By.xpath(
	 * "(//ul[@class='el-pager'])[2]/li[" + i + "]")).getText()); String
	 * getVersionHistry =
	 * driver.findElement(By.xpath("(//table[@class='el-table__body'])[2]")).getText
	 * (); System.out.println("\n***** getVersionHistry Details *****" + "\n" +
	 * getVersionHistry); } } else { System.out.println("no pagination"); } }
	 */
	
	@When("^Get the Allowed License Attribute$")
	public void get_the_Allowed_License_Attribute() throws Throwable {
	    String allowedLicenseAttribute = driver.findElement(By.xpath("//h4[text()='Allowed License Attributes']")).getText();
	    assertEquals("Allowed License Attributes", allowedLicenseAttribute);
	    allowedLicenseAttributeDetails = driver.findElement(By.xpath("(//div[@class='card-content content-style'])[1]")).getText();
	}

	@Then("^validate the Allowed License Attribute$")
	public void validate_the_Allowed_License_Attribute() throws Throwable {
		System.out.println("\nAllowed License Attribute Details :- "+allowedLicenseAttributeDetails);
	}

	@When("^Get the Required License Attribute$")
	public void get_the_Required_License_Attribute() throws Throwable {
	    String requiredLicenseAttribute = driver.findElement(By.xpath("//h4[text()='Required License Attributes']")).getText();
	    assertEquals("Required License Attributes", requiredLicenseAttribute);
	    requiredLicenseAttributeDetails = driver.findElement(By.xpath("(//div[@class='card-content content-style'])[2]")).getText();
	
	}

	@Then("^validate the Required License Attribute$")
	public void validate_the_Required_License_Attribute() throws Throwable {
		System.out.println("Required License Attribute Details :- "+requiredLicenseAttributeDetails);

	}

	@When("^Get the Restricted License Attribute$")
	public void get_the_Restricted_License_Attribute() throws Throwable {
	    String restrictedLicenseAttribute = driver.findElement(By.xpath("//h4[text()='Restricted License Attributes']")).getText();
	    assertEquals("Restricted License Attributes", restrictedLicenseAttribute);
	    restrictedLicenseAttributeDetails = driver.findElement(By.xpath("(//div[@class='card-content content-style'])[3]")).getText();
	
	}

	@Then("^validate the Restricted License Attribute$")
	public void validate_the_Restricted_License_Attribute() throws Throwable {
		System.out.println("Restricted License Attribute Details :- "+restrictedLicenseAttributeDetails);

	}

	@When("^Click on back button$")
	public void click_on_back_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.navigate().back();
	}

	@Then("^validate the sorting of functionality$")
	public void validate_the_sorting_of_functionality() throws Throwable {
			driver.findElement(By.xpath("//div[@class='breadcrumb-style']")).click();
			driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);
		    driver.findElement(By.xpath("(//span[@class='caret-wrapper']/i[@class='sort-caret ascending'])[3]")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("(//span[@class='caret-wrapper']/i[@class='sort-caret descending'])[3]")).click();
		 //   driver.findElement(By.xpath("(//span[@class='caret-wrapper']/i[@class='sort-caret descending'])[3]")).click();
	}
	
}
