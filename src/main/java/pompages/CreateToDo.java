package pompages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CreateToDo
{
	//DECLARATION
	@FindBy(xpath="//b[text()=\"Create To Do\"]")
	private WebElement createToDoHeader;
	
	@FindBy(xpath="//input[@name=\"subject\"]")
	private WebElement subjectTF;
	
	@FindBy(xpath="//select[@name=\"eventstatus\"]")
	private WebElement statusDropdown;
	
	
	
	@FindBy(xpath="//img[@id='jscal_trigger_due_date']")
	private WebElement endCalendarElement;
	
	@FindBy(xpath="//td[@class='title']")
	private WebElement CalendarMonth;
	
	@FindAll({@FindBy(xpath="//td[@class='hilite nav button hilite']"),@FindBy(xpath="//td[@class=\"title\"][1]/following::td[@class=\"button nav\"][2]")})
	private WebElement nextMonthButton;
	
	@FindBy(xpath="/html/body/div[19]/table/tbody/tr/td")
	private List<WebElement> allDates;
	
	
	
	
	
	@FindBy(xpath="//img[@id='jscal_trigger_date_start']")
	private WebElement startCalendarElement;
	
	@FindBy(xpath="/html[1]/body[1]/div[46]/table[1]/thead[1]/tr[1]/td[2]")
	private WebElement monthElement;
	
	@FindBy(xpath="//input[@name='time_start']")
	private WebElement timeTF;
	
	@FindBy(xpath="//select[@name='activitytype']")
	private WebElement activityTypDropdown;
	
	@FindBy(xpath="//input[@value='T']")
	private WebElement groupradiobox;
	
	@FindBy(xpath="//select[@name='assigned_group_id']")
	private WebElement groupradioboxdd;
	
	@FindBy(xpath="//input[@value='Save']")
	private WebElement saveButton;
	
	//INITIALIZATION
	public CreateToDo(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//UTILIZATION
	public String getCreateToDoHeader()
	{
		return createToDoHeader.getText();
	}
	
	public void setsubjectTF(String subject)
	{
		subjectTF.sendKeys(subject);
	}
	
	public void handleStatusDropdown(WebDriverUtility web, String status)
	{
		web.dropDown(statusDropdown, status);
	}
	
	public void selectStatus(WebDriverUtility web, String status)
	{
		web.dropDown(statusDropdown, status);
	}
	
	public void selectEndDate()
	{
		endCalendarElement.click();
	}
	
	public void selectStartDate()
	{
		startCalendarElement.click();
	}
	
	public void selectRequiredDate(String mon, String reqDate)
	{
		while(true)
		{
			String month=CalendarMonth.getText();
			if(month.contains(mon))
			{
				break;
			}
			else
			{
				nextMonthButton.click();		
			}
		}
		
		for(int i=0; i<allDates.size();i++)
		{
			String date=allDates.get(i).getText();
			System.out.println(date);
			if(date.contains(reqDate))
			{
				allDates.get(i).click();
				break;
			}
		}
	}
	
	public void setTimeTF(String time)
	{
		timeTF.clear();
		timeTF.sendKeys(time);
	}
	
	public void handleActivityTypeDropdown(WebDriverUtility web, String activityType)
	{
		web.dropDown(activityTypDropdown, activityType);
	}
	
	public void clickGroupRadiobox()
	{
		groupradiobox.click();
	}
	
	public void handleGroupDD(WebDriverUtility web, String group)
	{
		web.dropDown(groupradioboxdd, group);
	}
	public void clickOnSaveButton()
	{
		saveButton.submit();
	}
	
	
	
	
}
