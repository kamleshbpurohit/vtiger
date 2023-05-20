package pompages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {

	//Declaration
	@FindBy(xpath="//img[@title='Create Lead...']")
	private WebElement plusButton;

	@FindBy(xpath="//a[@href='index.php?action=ListView&module=Contacts&parenttab=Marketing']")
	private WebElement pageHeader;

	@FindBy(xpath="//tbody/tr[last()]/td[3]/a[@title=\"Leads\"]")
	private WebElement lastContactInList;
	
	@FindBy(xpath = "//a[text()=\"del\"][1]")
	private WebElement deleteTab;

	@FindBy(xpath = "//a[text()=\"del\"]")
	private List<WebElement> listOfLeads;
	
	//Initialization
	public LeadsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	//Business libraries
	public void clickPlusButton() {
		plusButton.click();
	}

	public void clickOnDelTab()
	{
		deleteTab.click();
	}
	
	public int getExistingLeads()
	{
		
		int i=0;
		List<WebElement> num = listOfLeads;
		for(int j=0;j<num.size();j++)
		{
			i++;
		}
		return i;
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}

	public String getLastLeadName() {
		return lastContactInList.getText();
	}


}
