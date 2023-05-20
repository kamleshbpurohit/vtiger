package pompages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CreateNewContactPage {
	
	//Declaration
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(name="salutationtype") 
	private WebElement firstNameSalutationDropdown;
	
	@FindBy(name="lastname")
	private WebElement lastNameTextField;
	
	@FindBy(xpath="//input[@name=\"account_name\"]")
	private WebElement organizationTF;
	
	@FindBy(xpath="//img[@title='Select' and contains(@onclick,'Accounts&action')]")
	private WebElement organizationPlusButton;
	
	@FindBy(xpath="//a[@href=\"javascript:window.close();\"]")
	private List<WebElement> organizationList;
	
	@FindBy(xpath="//input[@name='imagename']") 
	private WebElement contactImage;
	
	@FindBy(xpath="//input[contains(@value,'Save')]") 
	private WebElement saveButton;
	
	@FindBy(xpath="//img[@title=\"Select\"]") 
	private WebElement organizationsPlusButton;
	
	//Initialization
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	//Utilization
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void selectFirstNameSalutation(WebDriverUtility webdriver, String value) {
		webdriver.dropDown(firstNameSalutationDropdown, value);
	}
	
	public void setLastName(String name) {
		lastNameTextField.sendKeys(name);
	}
	
	public void setOrganizationName(String orgname)
	{
		organizationTF.sendKeys(orgname);
	}
	public void selectExistingOrganization(WebDriver driver, String existOrgName) {
		String parent=driver.getWindowHandle();
        organizationPlusButton.click();
        Set<String> child = driver.getWindowHandles();
        for(String Child: child)
        {
        	driver.switchTo().window(Child);
        }
        for(WebElement ele:organizationList)
        {
        	String org=ele.getText();
        	if(org.contains(existOrgName))
        	{
        		ele.click();
        		break;
        	}
        }
        driver.switchTo().window(parent);  
	}
	
	public void uploadContactImage(String imageFilePath) {
		contactImage.sendKeys(imageFilePath);
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}
		
}
