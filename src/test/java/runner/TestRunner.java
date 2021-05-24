package runner;

import java.io.File;

import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(features = 
{"src/test/java/features"},
//{"src/test/java/features/21_Tag.feature"},
//{"src/test/java/features/06_Component.feature","src/test/java/features/07_ComponentScanResult.feature"},
//{"src/test/java/features/04_Project.feature","src/test/java/features/05_ProjectDetails.feature"},
//{"src/test/java/features/08_Vulnerability.feature","src/test/java/features/09_VulnerabilityScanResult.feature"},
//{"src/test/java/features/01_Register.feature","src/test/java/features/02_Login.feature","src/test/java/features/03_Dashboard.feature","src/test/java/features/04_Project.feature","src/test/java/features/05_ProjectDetails.feature","src/test/java/features/06_Component.feature","src/test/java/features/07_ComponentScanResult.feature"},
		//,"src/test/java/features/08_Vulnerability.feature","src/test/java/features/09_VulnerabilityScanResult.feature","src/test/java/features/10_KnowledgeGraph.feature","src/test/java/features/12_CreatePolicy.feature","src/test/java/features/13_PolicyDetails.feature","src/test/java/features/14_CompliancePolicy.feature","src/test/java/features/15_ComplianceResult.feature","src/test/java/features/16_GeneralSetting.feature","src/test/java/features/17_Teams.feature","src/test/java/features/18_MemberInvitation.feature","src/test/java/features/19_AccessToken.feature","src/test/java/features/20_Integrations.feature","src/test/java/features/21_Tag.feature"},
	glue = { "stepDef" },tags= {"~@skip","~@binary","~@group","~@upload"}, plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" }, monochrome = true)

public class TestRunner extends AbstractTestNGCucumberTests {
	  public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File("Config/Report.xml"));
	}
}
