package utility;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import stepDef.Hook;

public class Utility {

	public static boolean isElementPresent(By by) {
		try {
			Hook.getDriver().findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void pagination_check() throws InterruptedException {
		List<WebElement> pagination = Hook.getDriver().findElements(By.xpath("(//ul[@class='el-pager'])[2]/li"));
		Thread.sleep(5000);
		if (pagination.size() > 0) {
			for (int i = 1; i <= pagination.size(); i++) {
				JavascriptExecutor js = (JavascriptExecutor) Hook.getDriver();
				js.executeScript("arguments[0].click();",
						Hook.getDriver().findElement(By.xpath("(//ul[@class='el-pager'])[2]/li[" + i + "]")));
			//	System.out.println(
			//			Hook.getDriver().findElement(By.xpath("(//ul[@class='el-pager'])[2]/li[" + i + "]")).getText());
				String getVersionHistry = Hook.getDriver().findElement(By.xpath("(//table[@class='el-table__body'])[2]")).getText();
				System.out.println("\n***** getVersionHistory Details *****" + "\n" + getVersionHistry);
				Thread.sleep(1000);
			}
		} else {
			System.out.println("no pagination");
		}
	}

	public static boolean isStringInteger(String number) {
		try {
			Integer.parseInt(number);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag = true;
		}

		return flag;
	}

	
	public static boolean retryingFindClick(By by) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 5) {
			try {
				Hook.getDriver().findElement(by).click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
		}
		return result;
	}
	
	
	public static boolean isNumber(String s) {
		for (int i = 0; i < s.length(); i++)
			if (Character.isDigit(s.charAt(i)) == false)
				return false;

		return true;
	}
	
	public static Boolean retryingFindClick(WebElement element) {
		Boolean result = false;
		int attempts = 0;
		while (attempts < 5) {
			try {
				element.click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}
	
	public static String createPolicyname() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		StringBuilder sb = new StringBuilder();

		Random random = new Random();

		int length = 7;

		for (int i = 0; i < length; i++) {

			int index = random.nextInt(alphabet.length());

			char randomChar = alphabet.charAt(index);

			sb.append(randomChar);
		}

		String randomString = sb.toString();
		return randomString;

	}
	
	public static void verifyLink() {
		String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;
		List<WebElement> links = Hook.driver.findElements(By.xpath("//div[@class='hyperlink-card']/div/a"));
        
        Iterator<WebElement> it = links.iterator();
        
        while(it.hasNext()){
            
            url = it.next().getAttribute("href");
            
        
            if(url == null || url.isEmpty()){
            	System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }
        
            
            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());
                
                huc.setRequestMethod("HEAD");
                
                huc.connect();
                
                respCode = huc.getResponseCode();
                
                if(respCode >= 400){
                    System.out.println("\n"+url+" ==> is a broken link");
                }
                else{
                    System.out.println("\n"+url+" ==> is a valid link");
                }
            
                    
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
}
