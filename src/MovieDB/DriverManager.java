package MovieDB;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverManager {
	private static WebDriver driver;
	
	public static WebDriver getDriver(){
		if (driver == null){
			createChromeDriver();
		}		
		return driver;
	}
	
	public static void setPage(String page){
		if (driver == null){
			createChromeDriver();
		}
		driver.get(page);
	}
	
	public static void createFirefoxDriver(){
		System.setProperty("webdriver.gecko.driver", "C:/Dependencies/Selenium/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	public static void createChromeDriver(){
		System.setProperty("webdriver.chrome.driver", "C:/Dependencies/Selenium/chromedriver.exe");
		driver = new ChromeDriver();	
	}
	
	public static void createInternetExplorerDriver(){
		System.setProperty("webdriver.ie.driver", "C:/Dependencies/Selenium/IEDriverServer.exe");
		driver = new InternetExplorerDriver();	
	}
	
	public static void killDriver(){
		driver.quit();
	}
}
