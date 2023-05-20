package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalendarPage
{
	@FindBy(xpath ="//span[@class=\"lvtHeaderText\"]")
	private WebElement eventSubjectPageheader;
	
	public CalendarPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String getEventSubjectPageheader()
	{
		return eventSubjectPageheader.getText();
	}

}
