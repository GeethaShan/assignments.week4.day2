package assignments.week4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment2_JQuery_Actions {

	public ChromeDriver driversetup(String url) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driverElement = new ChromeDriver();
		driverElement.get(url);
		driverElement.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driverElement;

	}

	public static void main(String[] args) {
		Assignment2_JQuery_Actions objClass = new Assignment2_JQuery_Actions();

		// 1. https://jqueryui.com/draggable
		ChromeDriver driver = objClass.driversetup("https://jqueryui.com/draggable");
		driver.switchTo().frame(0);
		WebElement dragElement = driver.findElement(By.id("draggable"));
		Actions actionBuilder = new Actions(driver);
		actionBuilder.dragAndDropBy(dragElement, 50, 50).perform();
		actionBuilder.dragAndDropBy(dragElement, 50, 50).perform();
		System.out.println("Pass - Draggable");
				
		// 2. https://jqueryui.com/droppable
		driver.get("https://jqueryui.com/droppable");
		// Switch to the frame
		driver.switchTo().frame(0);
		// Locate the elements
		WebElement drag = driver.findElement(By.id("draggable"));
		WebElement drop = driver.findElement(By.id("droppable"));
		actionBuilder.dragAndDrop(drag, drop).perform();
		System.out.println("Pass - Droppable");

		// 3. https://jqueryui.com/resizable
		driver.get("https://jqueryui.com/resizable");
		// Switch to the frame
		driver.switchTo().frame(0);
		// Locate the elements
		WebElement resizeHandle = driver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
		WebElement headerElement = driver.findElement(By.xpath("//h3[@class='ui-widget-header']"));
		actionBuilder.clickAndHold(resizeHandle).moveToElement(headerElement).release().perform();
		System.out.println("Pass - Resizable");
		
		// 4. https://jqueryui.com/selectable
		driver.get("https://jqueryui.com/selectable");
		// Switch to the frame
		driver.switchTo().frame(0);
		// Locate the elements
		WebElement element1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement element5 = driver.findElement(By.xpath("//li[text()='Item 5']"));
		actionBuilder.clickAndHold(element1).moveToElement(element5).release().perform();
		System.out.println("Pass - Selectable");
		
		// 5. https://jqueryui.com/sortable
		driver.get("https://jqueryui.com/sortable");
		// Switch to the frame
		driver.switchTo().frame(0);
		// Locate the elements
		WebElement item1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement item5 = driver.findElement(By.xpath("//li[text()='Item 5']"));
		Point location = item5.getLocation(); 
		int x = location.getX();
		int y = location.getY();
		actionBuilder.dragAndDropBy(item1, x, y).perform();
		System.out.println("Pass - Sortable");
		
		//Close the driver
		driver.close();
	}

}
