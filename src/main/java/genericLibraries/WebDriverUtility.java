package genericLibraries;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class gives the reusable methods to perform webdriver actions
 * @author Kamlesh B
 *
 */

public class WebDriverUtility {
	public WebDriver driver;
	
	/**
	 * This method Opens the browser and navigates to application
	 * @param Browser 
	 * @return 
	 */
	public WebDriver openApplication(String Browser, String url, long time) 
	{
		switch(Browser)
		{
		case "chrome":
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		break;
		case "firefox":
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		break;
		case "edge":
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		break;
		default:System.out.println("Enter valid browser");
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		return driver;
	}

	/**
	 * This method is used to perform mouse hover action 
	 * @param element 
	 */
	public void mouseHoverToElement(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();
	}
	
	/**
	 * This method is used to handle dropdown by text
	 * @param element 
	 * @param text 
	 */
	public void dropDown(WebElement element, String text) {
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}
	
	/**
	 * This method is used to handle dropdown by index
	 * @param text
	 * @param element
	 * @param indexValue 
	 */
	public void dropDown(WebElement element, int indexValue) {
		Select s = new Select(element);
		s.selectByIndex(indexValue);
	}
	
	/**
	 * This method is used to switch to frame
	 * @param index 
	 */
	public void switchToFrame(String index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to switch back from the frame
	 */
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method is used scroll the web page till the element
	 * @param element 
	 */
	public void scrollTillElement(Object element) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	/**
	 * This method is used to take the screenshot
	 * @param javaUtility 
	 * @return 
	 */
	public String getScreenshot(WebDriver driver,JavaUtility javaUtility, String classname) {
		String currentTime = javaUtility.currentTime();
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/"+classname+"_"+currentTime+".png");
		try 
		{
			FileUtils.copyFile(src, dest);
		} catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		return dest.getAbsolutePath();
	}
	
	
	/**
	 * This method is used to handle alert pop-up
	 */
	public void alertPopup() 
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method is used to get parent window title
	 */
	public String getParentWindow()
	{
		return driver.getWindowHandle();
	}
	
	/**
	 * This method is used to switch to specified window
	 * @param windowID 
	 */
	public void switchToWindow(String windowID) {
		driver.switchTo().window(windowID);
	}
	
	/**
	 * This method is used to handle child browser popup
	 * @param expectedTitle 
	 */
	public void handleChildBrowserPopup(Object expectedTitle) {
		Set<String> windowTitles = driver.getWindowHandles();
		for(String windowID : windowTitles) {
			driver.switchTo().window(windowID);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle))
				break;
		}
	}
	
		
	/**
	 * This method is used to close browser
	 */
	public void closeBrowser() {
		driver.quit();
	}



	
}
