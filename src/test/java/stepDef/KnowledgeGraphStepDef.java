package stepDef;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class KnowledgeGraphStepDef {

	private WebDriver driver;

	public KnowledgeGraphStepDef() {
		this.driver = Hook.getDriver();
		
	}

	@When("^Click on knowledge graph menu$")
	public void click_on_knowledge_graph_menu() throws Throwable {
		new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/u/scantist3/org/scantist3/knowledge-graph']")));
	    driver.findElement(By.xpath("//a[@href='/u/scantist3/org/scantist3/knowledge-graph']")).click();
	}

	@Then("^Validate the knowledge graph page$")
	public void validate_the_knowledge_graph_page() throws Throwable {
		Thread.sleep(1000);
//		Actions action = new Actions(driver);
//		Thread.sleep(1000);
//		action.moveByOffset(500, 500).click().build().perform();
		
		driver.navigate().refresh();
		Thread.sleep(1000);

		
		String pageTitle = driver.findElement(By.xpath("//a[text()='Knowledge Graph']")).getText();
		String expectedPageTitle = "Knowledge Graph";
		assertEquals(pageTitle, expectedPageTitle);

		
	}

	@When("^Get the knowledge graph information$")
	public void get_the_knowledge_graph_information() throws Throwable {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@class='el-loading-mask'][@style='display: none;']"));
			}
		});
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.xpath("//div[@class='card']"));
		ele.isDisplayed();
		String knowledgeGraph = ele.getText();
		Thread.sleep(2000);

	}

	@Then("^Knowledge graph is displaying$")
	public void knowledge_graph_is_displaying() throws Throwable {

		driver.findElement(By.xpath("(//span[@class='el-checkbox__inner'])[1]")).click();
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.xpath("//div[@class='card']"));
		ele.isDisplayed();
		String knowledgeGraph = ele.getText();
		Thread.sleep(2000);
		System.out.println("\n*****KnowledgeGraph Displaying*****");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[@class='el-checkbox__inner'])[1]")).click();
	}

	@When("^Search knowledge graph Most Vulnerable Projects$")
	public void search_knowledge_graph_Most_Vulnerable_Projects() throws Throwable {
		driver.findElement(By.xpath("(//input[@placeholder='Select a query'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//li/span[text()='Most Vulnerable Projects']")))
				.click();
		driver.findElement(By.xpath("//button[@id='submitQueryButton']")).click();
	}

	@When("^Search knowledge graph Critical Projects Projects$")
	public void search_knowledge_graph_Critical_Projects_Projects() throws Throwable {
		driver.findElement(By.xpath("(//input[@placeholder='Select a query'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li/span[text()='Critical Projects']")))
				.click();
		driver.findElement(By.xpath("//button[@id='submitQueryButton']")).click();
	}

	@When("^Search knowledge graph Most Common CVEs Projects$")
	public void search_knowledge_graph_Most_Common_CVEs_Projects() throws Throwable {
		driver.findElement(By.xpath("(//input[@placeholder='Select a query'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li/span[text()='Most Common CVEs']")))
				.click();
		driver.findElement(By.xpath("//button[@id='submitQueryButton']")).click();
	}

	@When("^Search knowledge graph Less Maintained Projects$")
	public void search_knowledge_graph_Less_Maintained_Projects() throws Throwable {
		driver.findElement(By.xpath("(//input[@placeholder='Select a query'])[1]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 4000);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//li/span[text()='Less Maintained Projects']")))
				.click();
		driver.findElement(By.xpath("//button[@id='submitQueryButton']")).click();
	}

	@When("^Search for projects in advanced search$")
	public void search_for_projects_in_advanced_search() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='tab-advanced'][text()='Advanced']"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='text'])[2]"))).click();
		Thread.sleep(1000);

		List<WebElement> internal_agt = driver.findElements(By.xpath("(//ul[@class='el-scrollbar__view el-select-dropdown__list'])[16]/li"));
		Thread.sleep(2000);
		
		  for (WebElement ele : internal_agt) 
		  { 
			  String str = ele.getText();
			//  System.out.println("List"+ele.getText());

		  if(str.equalsIgnoreCase("Projects")) 
		  { 
			  Thread.sleep(1000); 
			  ele.click(); 
		  } 
		  }
		 
	}

	@When("^Search for vulnerability in advanced search$")
	public void search_for_vulnerability_in_advanced_search() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='text'])[2]"))).click();
		List<WebElement> internal_agt = driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
		Thread.sleep(1000);
		for (WebElement ele : internal_agt) {
			String str = ele.getText();
			if (str.equalsIgnoreCase("Vulnerabilities")) {
				ele.click();
			}
		}
	}

	@When("^Search for Licenses in advanced search$")
	public void search_for_Licenses_in_advanced_search() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='text'])[2]"))).click();
		List<WebElement> internal_agt = driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
		Thread.sleep(1000);
		for (WebElement ele : internal_agt) {
			String str = ele.getText();
			if (str.equalsIgnoreCase("Licenses")) {
				Thread.sleep(1000);
				ele.click();
			}
		}
	}

	@When("^Search for component in advanced search$")
	public void search_for_component_in_advanced_search() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='text'])[2]"))).click();
		List<WebElement> internal_agt = driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
		Thread.sleep(1000);
		for (WebElement ele : internal_agt) {
			String str = ele.getText();
			if (str.equalsIgnoreCase("Components")) {
				Thread.sleep(1000);
				ele.click();
			}
		}
	}

	@When("^Search for customs in advanced search$")
	public void search_for_customs_in_advanced_search() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='text'])[2]"))).click();
		List<WebElement> internal_agt = driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li"));
		Thread.sleep(1000);
		for (WebElement ele : internal_agt) {
			String str = ele.getText();
			if (str.equalsIgnoreCase("Custom")) {
				Thread.sleep(1000);
				ele.click();
			}
		}
	}

}
