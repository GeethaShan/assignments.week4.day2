package assignments.week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment4_Snapdeal {

	public ChromeDriver driversetup(String url) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driverElement = new ChromeDriver();
		driverElement.get(url);
		driverElement.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driverElement;
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// Create object for the Snapdeal class
		Assignment4_Snapdeal objClass = new Assignment4_Snapdeal();

		// 1. Launch https://www.snapdeal.com/
		ChromeDriver driver = objClass.driversetup("https://www.snapdeal.com/");
		System.out.println("Launched Snapdeal site");

		// 2. Go to Mens Fashion
		Thread.sleep(3000);
		WebElement mensFashion = driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]"));
		Thread.sleep(2000);
		mensFashion.click();
		Thread.sleep(3000);
		System.out.println("Clicked Men's fashion");

		// 3. Go to Sports Shoes
		driver.findElement(By.xpath("//div[@id='category6Data']//span[text()='Sports Shoes']")).click();
		System.out.println("Selecetd Sports shoes");
		
		// 4. Get the count of the sports shoes
		String sportsShoeElement = driver.findElement(By.xpath("//h1[contains(text(),'Sports Shoes')]/span")).getText();
		String sportsShoescount = sportsShoeElement.replaceAll("[^0-9]", "");
		System.out.println("Sports Shoe count : " + sportsShoescount);

		// 5. Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		System.out.println("Selected Training Shoes");

		// 6. Sort by Low to High
		driver.findElement(By.xpath("//span[text()='Sort by:']/following-sibling::i")).click();
		driver.findElement(By.xpath("//ul[@class='sort-value']//li[2]")).click();
		Thread.sleep(5000);
		System.out.println("Sorted by Low to High");

		// 7. Check if the items displayed are sorted correctly
		List<WebElement> itemsPrice = driver
				.findElements(By.xpath("//p[@class='product-title']/following::div//span[@class='lfloat product-price']"));
		
		int previousPrice=0;
		boolean sortOrderIssue=false;
		for (WebElement priceEle : itemsPrice) {
			String price = priceEle.getText().replaceAll("[^0-9]", "");
			int parseInt = Integer.parseInt(price);
			//System.out.println(parseInt);
			if (parseInt < previousPrice) {
				System.out.println("Elements are not sorted properly : "+parseInt+" < "+previousPrice);
				sortOrderIssue=true;
				break;
			}
			previousPrice=parseInt;
			
		}
		if(!sortOrderIssue) {
			System.out.println("Elements are sorted properly");
		}
		
		
		// 8. Mouse Hover on puma Blue Training shoes
		//No Puma brand displayed, so chose VSS
		Actions actionBuilder = new Actions(driver);
		List<WebElement> searchElements = driver.findElements(By.xpath("//p[@class='product-title']"));
		for (WebElement shoeELE : searchElements) {
			String shoeName = shoeELE.getAttribute("title");
			
			if (shoeName.equals("VSS Blue Training Shoes")) {
				actionBuilder.moveToElement(shoeELE).perform();
				break;
			}
		}
		
		// 9. click QuickView button
		Thread.sleep(2000);
		String pogId = driver.findElement(By.xpath("//p[@title='VSS Blue Training Shoes']/parent::a")).getAttribute("pogid");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@pogid='"+pogId+"']")).click();
		Thread.sleep(2000);
		System.out.println("Clicked Quick view button");
		
		// 10. Print the cost and the discount percentage
		String costStr = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		String cost = costStr.replaceAll("[^0-9]", "");
		System.out.println("Cost is "+cost);
		
		String discStr = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		String discPerc = discStr.replaceAll("OFF", "");
		System.out.println("Discount percentage is "+discPerc);
		
		// 11. Take the snapshot of the shoes.
		WebElement shoePic = driver.findElement(By.xpath("//img[@itemprop='image']"));
		File src = shoePic.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snapshot/shoePic.png");
		FileUtils.copyFile(src, dest);
		Thread.sleep(2000);
		System.out.println("Snapshot taken");
		
		// 12. Close the current window
		driver.findElement(By.xpath("//div[contains(@class,'close1')]/i")).click();
		Thread.sleep(2000);
		System.out.println("Closed current window");
		
		// 13. Close the main window
		driver.close();
		System.out.println("Closed main window");
	}

}
