package assignments.week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment3_Nykaa {
	
	public ChromeDriver driversetup(String url) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driverElement = new ChromeDriver();
		driverElement.get(url);
		driverElement.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driverElement;

	}

	public static void main(String[] args) throws InterruptedException {
		//Create object for the Nykaa class
		Assignment3_Nykaa objClass = new Assignment3_Nykaa();

		// 1) Go to https://www.nykaa.com/
		ChromeDriver driver = objClass.driversetup("https://www.nykaa.com/");
		System.out.println("Launched Nykaa site");
		
		// 2) Mouseover on Brands and Mouseover on Popular
		Actions actionBuilder = new Actions(driver);
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		actionBuilder.moveToElement(brand).perform();
		Thread.sleep(2000);
		WebElement popular = driver.findElement(By.xpath("//a[text()='Popular']"));
		actionBuilder.moveToElement(popular).perform();
		Thread.sleep(2000);
		
		// 3) Click L'Oreal Paris
		driver.findElement(By.xpath("//div[@id='brandCont_Popular']//img[contains(@src,'loreal')]")).click();
		Thread.sleep(3000);
		// 4) Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));
		String title = driver.getTitle();
		if (title.contains("L'Oreal Paris")) {
			System.out.println("PASS - Title contains L'Oreal Paris");
		}
		else
			System.out.println("FAIL - Title does not contain L'Oreal Paris.Title is : "+title);
		
		// 5) Click sort By and select customer top rated
		driver.findElement(By.xpath("//div[@id='sortComponent']//i")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='customer top rated']/following-sibling::div")).click();
		Thread.sleep(3000);
		System.out.println("Sorted by customer top rated");
		
		// 6) Click Category and click Shampoo
		driver.findElement(By.xpath("//div[text()='Category']//following::i[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Hair']/following::div[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Hair Care']/following::div[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Shampoo']/following::div[1]")).click();
		Thread.sleep(3000);
		System.out.println("Clicked Shampoo under category");
		
		// 7) check whether the Filter is applied with Shampoo
		WebElement filters = driver.findElement(By.xpath("//span[text()='filters applied']/following::li[1]"));
		Thread.sleep(3000);
		String filterSel = filters.getText().replaceAll("close", "");
		System.out.println(filterSel);
		if (filterSel.equals("Shampoo")) {
			System.out.println("PASS - Filter is applied with Shampoo");
		}
		else
			System.out.println("FAIL - Filter is not applied with Shampoo");
		
		// 8) Click on L'Oreal Paris Colour Protect Shampoo
		List<WebElement> findElements = driver.findElements(By.tagName("h2"));
		Thread.sleep(3000);
		System.out.println(findElements.size());
		for (WebElement eachShampoo : findElements) {
			if (eachShampoo.getText().equals("L'Oreal Paris Colour Protect Shampoo")) {
				eachShampoo.click();
				break;
			}
			
		}
		windowHandles = driver.getWindowHandles();
		windowHandlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(2));
		
		if (driver.getTitle().contains("Buy L'Oreal Paris Colour Protect Shampoo")) {
			System.out.println("Succesfully clicked L'Oreal Paris Colour Protect Shampoo");
		}
		else
			System.out.println("Not clicked L'Oreal Paris Colour Protect Shampoo");
		
		// 9) GO to the new window and select size as 175ml
		driver.findElement(By.xpath("//span[text()='175ml']")).click();
		System.out.println("Selected size as 175ml");
		
		// 10) Print the MRP of the product
		String price = driver.findElement(By.xpath("//div[text()='inclusive of all taxes']/preceding-sibling::div[1]//span[2]/span")).getText().replaceAll("[^0-9]", "");
		System.out.println("MRP of L'Oreal Paris Colour Protect Shampoo is : "+price);
		
		// 11) Click on ADD to BAG
		driver.findElement(By.xpath("//button[text()='ADD TO BAG']")).click();
		Thread.sleep(3000);
		System.out.println("Clicked on Add to Bag");
		
		//  12) Go to Shopping Bag 
		driver.findElement(By.className("AddBagIcon")).click();
		Thread.sleep(3000);
		System.out.println("Navigated to shopping Bag");
		
		// 13) Print the Grand Total amount
		String grandTotal = driver.findElement(By.xpath("//span[text()='Grand Total']/parent::div[@class='name medium-strong']/following-sibling::div")).getText().replaceAll("[^0-9]", "");
		System.out.println("Grand Total is : "+grandTotal);
		Thread.sleep(3000);
		
		// 14) Click Proceed
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		System.out.println("Clicked proceed button");
		
		// 15) Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		System.out.println("Clicked continue as Guest button");
		
		// 16) Check if this grand total is the same in step 13
		String grandTotal2 = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div/span")).getText();
		if (grandTotal2.equals(grandTotal)) {
			System.out.println("PASS - Grand total is same as step 13.GrandTotal :"+grandTotal2);
		}
		else
			System.out.println("FAIL - Grand total is not same as step 13.GrandTotal :"+grandTotal2);
		
		// 17) Close all windows
		driver.quit();
		System.out.println("Closed all windows");
		
	}

}
