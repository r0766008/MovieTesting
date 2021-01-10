package MovieDB;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MovieDB {
	static WebDriver driver;
	static WebDriverWait wait;
	public static void main(String[] args) {
		driver = DriverManager.getDriver();
		wait = new WebDriverWait(driver, 10);
		driver.get("http://localhost:4200/movies");
		
		System.out.println(goToCreateMovie());

		System.out.println(addMovie(null, "2009", "Action, Adventure, Fantasy", "162", "6EiRUJpuoeQPghrs3YNktfnqOVh"));
		System.out.println(addMovie("Avatar", null, "Action, Adventure, Fantasy", "162", "6EiRUJpuoeQPghrs3YNktfnqOVh"));
		System.out.println(addMovie("Avatar", "2009", null, "162", "6EiRUJpuoeQPghrs3YNktfnqOVh"));
		System.out.println(addMovie("Avatar", "2009", "Action, Adventure, Fantasy", null, "6EiRUJpuoeQPghrs3YNktfnqOVh"));
		System.out.println(addMovie("Avatar", "2009", "Action, Adventure, Fantasy", "162", null));
		System.out.println(addMovie("Avatar", "2009", "Action, Adventure, Fantasy", "162", "6EiRUJpuoeQPghrs3YNktfnqOVh"));
		
		System.out.println(goToEditMovie());
		
		System.out.println(goToCreateCast());

		System.out.println(addCast(null, "Worthington", "Jake Sully", "44", "Godalming, Surrey, England, UK", "vM1WIfYQ1HUBtlVPwB9Hp9fLcn8"));
		System.out.println(addCast("Sam", null, "Jake Sully", "44", "Godalming, Surrey, England, UK", "vM1WIfYQ1HUBtlVPwB9Hp9fLcn8"));
		System.out.println(addCast("Sam", "Worthington", null, "44", "Godalming, Surrey, England, UK", "vM1WIfYQ1HUBtlVPwB9Hp9fLcn8"));
		System.out.println(addCast("Sam", "Worthington", "Jake Sully", null, "Godalming, Surrey, England, UK", "vM1WIfYQ1HUBtlVPwB9Hp9fLcn8"));
		System.out.println(addCast("Sam", "Worthington", "Jake Sully", "44", null, "vM1WIfYQ1HUBtlVPwB9Hp9fLcn8"));
		System.out.println(addCast("Sam", "Worthington", "Jake Sully", "44", "Godalming, Surrey, England, UK", null));
		System.out.println(addCast("Sam", "Worthington", "Jake Sully", "44", "Godalming, Surrey, England, UK", "vM1WIfYQ1HUBtlVPwB9Hp9fLcn8"));

		System.out.println(goToEditCast());

		System.out.println(editCast(null, "Worthington", "Jake Sully", "44", "Godalming, Surrey, England, UK"));
		System.out.println(editCast("Sam (Updated)", null, "Jake Sully", "44", "Godalming, Surrey, England, UK"));
		System.out.println(editCast("Sam (Updated)", "Worthington", null, "44", "Godalming, Surrey, England, UK"));
		System.out.println(editCast("Sam (Updated)", "Worthington", "Jake Sully", null, "Godalming, Surrey, England, UK"));
		System.out.println(editCast("Sam (Updated)", "Worthington", "Jake Sully", "44", null));
		System.out.println(editCast("Sam (Updated)", "Worthington", "Jake Sully", "44", "Godalming, Surrey, England, UK"));
		
		System.out.println(deleteCast());
		
		System.out.println(editMovie(null, "2009", "Action, Adventure, Fantasy", "162"));
		System.out.println(editMovie("Avatar (Updated)", null, "Action, Adventure, Fantasy", "162"));
		System.out.println(editMovie("Avatar (Updated)", "2009", null, "162"));
		System.out.println(editMovie("Avatar (Updated)", "2009", "Action, Adventure, Fantasy", null));
		System.out.println(editMovie("Avatar (Updated)", "2009", "Action, Adventure, Fantasy", "162"));
		
		System.out.println(deleteMovie());
		
		driver.quit();
	}
	
	public static String goToCreateMovie() {
		String message = "Create movie page";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'New')]"))).click();
		return message;
	}
	
	public static String addMovie(String title, String year, String category, String minutes, String imdbID) {
		String message = "";
		driver.navigate().refresh();
		if (title != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("title"))).sendKeys(title);
		if (year != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("year"))).sendKeys(year);
		if (category != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("category"))).sendKeys(category);
		if (minutes != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("minutes"))).sendKeys(minutes);
		if (imdbID != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("imdbID"))).sendKeys(imdbID);
		if(driver.findElements(By.className("is-invalid")).size() > 0) {
			for (WebElement we : driver.findElements(By.className("is-invalid"))) {
				we.findElement(By.tagName("div"));
				message += we.getText();
			}
		} else {
			message = "Movie added";
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit')]"))).click();
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public static String goToEditMovie() {
		String message = "Edit movie page";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[" + String.valueOf(driver.findElements(By.xpath("//tbody/tr")).size()) + "]/td[4]/div[1]/a[1]/mat-icon[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[" + String.valueOf(driver.findElements(By.xpath("//tbody/tr")).size()) + "]/td[4]/div[1]/div[1]/a[1]"))).click();
		return message;
	}
	
	public static String goToCreateCast() {
		String message = "Create cast page";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'New')]"))).click();
		return message;
	}
	
	public static String addCast(String firstName, String lastName, String character, String age, String birthPlace, String iMDB) {
		String message = "";
		driver.navigate().refresh();
		if (firstName != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName"))).sendKeys(firstName);
		if (lastName != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("lastName"))).sendKeys(lastName);
		if (character != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("character"))).sendKeys(character);
		if (age != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("age"))).sendKeys(age);
		if (birthPlace != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("birthPlace"))).sendKeys(birthPlace);
		if (iMDB != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("iMDB"))).sendKeys(iMDB);
		if(driver.findElements(By.className("is-invalid")).size() > 0) {
			for (WebElement we : driver.findElements(By.className("is-invalid"))) {
				we.findElement(By.tagName("div"));
				message += we.getText();
			}
		} else {
			message = "Cast added";
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit')]"))).click();
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public static String goToEditCast() {
		String message = "Edit cast page";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[" + String.valueOf(driver.findElements(By.xpath("//tbody/tr")).size()) + "]/td[4]/div[1]/a[1]/mat-icon[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[" + String.valueOf(driver.findElements(By.xpath("//tbody/tr")).size()) + "]/td[4]/div[1]/div[1]/a[1]"))).click();
		return message;
	}
	
	public static String editCast(String firstName, String lastName, String character, String age, String birthPlace) {
		String message = "";
		wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName"))).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName"))).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("lastName"))).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("lastName"))).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("character"))).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("character"))).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("age"))).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("age"))).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("birthPlace"))).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("birthPlace"))).sendKeys(Keys.DELETE);
		if (firstName != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName"))).sendKeys(firstName);
		if (lastName != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("lastName"))).sendKeys(lastName);
		if (character != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("character"))).sendKeys(character);
		if (age != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("age"))).sendKeys(age);
		if (birthPlace != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("birthPlace"))).sendKeys(birthPlace);
		if(driver.findElements(By.className("is-invalid")).size() > 0) {
			for (WebElement we : driver.findElements(By.className("is-invalid"))) {
				we.findElement(By.tagName("div"));
				message += we.getText();
			}
		} else {
			message = "Cast updated";
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit')]"))).click();
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public static String deleteCast() {
		String message = "";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[" + String.valueOf(driver.findElements(By.xpath("//tbody/tr")).size()) + "]/td[4]/div[1]/a[1]/mat-icon[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[" + String.valueOf(driver.findElements(By.xpath("//tbody/tr")).size()) + "]/td[4]/div[1]/div[1]/a[2]"))).click();
		if (driver.findElements(By.xpath("//tbody/tr[" + String.valueOf(driver.findElements(By.xpath("//tbody/tr")).size()) + "]/td[4]/div[1]/a[1]/mat-icon[1]")).size() == 0) {
			message = "Cast deleted";
		} else message = "Cast not deleted";
		return message;
	}
	
	public static String editMovie(String title, String year, String category, String minutes) {
		String message = "";
		wait.until(ExpectedConditions.elementToBeClickable(By.id("title"))).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("title"))).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("year"))).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("year"))).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("category"))).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("category"))).sendKeys(Keys.DELETE);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("minutes"))).sendKeys(Keys.CONTROL + "a");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("minutes"))).sendKeys(Keys.DELETE);
		if (title != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("title"))).sendKeys(title);
		if (year != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("year"))).sendKeys(year);
		if (category != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("category"))).sendKeys(category);
		if (minutes != null) wait.until(ExpectedConditions.elementToBeClickable(By.id("minutes"))).sendKeys(minutes);
		if(driver.findElements(By.className("is-invalid")).size() > 0) {
			for (WebElement we : driver.findElements(By.className("is-invalid"))) {
				we.findElement(By.tagName("div"));
				message += we.getText();
			}
		} else {
			message = "Movie updated";
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit')]"))).click();
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public static String deleteMovie() {
		String message = "";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[" + String.valueOf(driver.findElements(By.xpath("//tbody/tr")).size()) + "]/td[4]/div[1]/a[1]/mat-icon[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[" + String.valueOf(driver.findElements(By.xpath("//tbody/tr")).size()) + "]/td[4]/div[1]/div[1]/a[2]"))).click();
		if (driver.findElements(By.xpath("//tbody/tr[" + String.valueOf(driver.findElements(By.xpath("//tbody/tr")).size()) + "]/td[4]/div[1]/a[1]/mat-icon[1]")).size() == 0) {
			message = "Movie deleted";
		} else message = "Movie not deleted";
		return message;
	}
}
