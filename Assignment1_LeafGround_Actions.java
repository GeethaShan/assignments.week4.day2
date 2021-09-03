package assignments.week4.day2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment1_LeafGround_Actions {
	
	public ChromeDriver driverSetUp(String url) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driverChrome = new ChromeDriver();
		driverChrome.get(url);
		driverChrome.manage().window().maximize();
		driverChrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driverChrome;
	}

	public static void main(String[] args) throws InterruptedException {
		Assignment1_LeafGround_Actions objClass = new Assignment1_LeafGround_Actions();
		
		//1.http://www.leafground.com/pages/sortable.html
		ChromeDriver driver = objClass.driverSetUp("http://www.leafground.com/pages/sortable.html");
		System.out.println("Launched page Sortable");
		WebElement element5 = driver.findElement(By.xpath("//li[text()='Item 5']"));
		WebElement element1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
		Actions actionBuilder = new Actions(driver);
		actionBuilder.clickAndHold(element5).moveToElement(element1).release().perform();
		Thread.sleep(2000);
		System.out.println("Pass - Sortable");
		
		
		//2.http://www.leafground.com/pages/selectable.html
		driver.get("http://www.leafground.com/pages/selectable.html");
		System.out.println("Launched page Selectable");
		WebElement ele5 = driver.findElement(By.xpath("//li[text()='Item 5']"));
		WebElement ele1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
		actionBuilder.dragAndDrop(ele1, ele5).perform();
		Thread.sleep(2000);
		System.out.println("Pass - Selectable");
		
		//3.http://www.leafground.com/pages/drag.html
		driver.get("http://www.leafground.com/pages/drag.html");
		System.out.println("Launched page Draggable");
		WebElement drag = driver.findElement(By.id("draggable"));
		actionBuilder.dragAndDropBy(drag, 200, 200).perform();
		Thread.sleep(2000);
		System.out.println("Pass - Draggable");
		
		//4.http://www.leafground.com/pages/drop.html
		driver.get("http://www.leafground.com/pages/drop.html");
		System.out.println("Launched page Droppable");
		WebElement dragEle = driver.findElement(By.id("draggable"));
		WebElement dropEle = driver.findElement(By.id("droppable"));
		actionBuilder.dragAndDrop(dragEle, dropEle).perform();
		Thread.sleep(2000);
		System.out.println("Pass - Droppable");
		
		//5.http://www.leafground.com/pages/mouseOver.html
		driver.get("http://www.leafground.com/pages/mouseOver.html");
		System.out.println("Launched page mouseOver");
		WebElement testLeafEle = driver.findElement(By.className("btnMouse"));
		actionBuilder.moveToElement(testLeafEle).perform();
		Thread.sleep(2000);
		List<WebElement> findElements = driver.findElements(By.xpath("//a[@class='listener']"));
		System.out.println("Link Names :");
		for (WebElement link : findElements) {
			System.out.println(link.getText());
		}
		Thread.sleep(2000);
		actionBuilder.moveToElement(testLeafEle).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='RPA']")).click();
		Thread.sleep(2000);
		Alert alertBox = driver.switchTo().alert();
		alertBox.accept();
		System.out.println("Pass - Clicked RPA course and handled alert");
		
		//close the browser
		driver.close();
		
	}

}
