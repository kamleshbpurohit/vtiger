package genericLibraries;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pompages.CalendarPage;
import pompages.ContactsPage;
import pompages.CreateNewContactPage;
import pompages.CreateNewLeadPage;
import pompages.CreateNewOrganizationPage;
import pompages.CreateToDo;
import pompages.DuplicatingLeadPage;
import pompages.HomePage;
import pompages.LeadsPage;
import pompages.LoginPage;
import pompages.NewContactInfoPage;
import pompages.NewLeadInfoPage;
import pompages.NewOrganizationInfoPage;
import pompages.OrganizationsPage;

public class BaseClass {
	protected ExcelUtility excel;
	protected PropertyFileUtility property;
	protected JavaUtility javaUtil;
	protected WebDriverUtility web;
	protected WebDriver driver;
	protected LoginPage login;
	protected HomePage home;
	protected CreateToDo toDo;
	protected ContactsPage contacts;
	protected CreateNewContactPage createContact;
	protected NewContactInfoPage newContact;
	public static JavaUtility sjavaUtil;
	public static WebDriver sdriver;
	protected LeadsPage leads;
	protected CreateNewLeadPage createNewLead;
	protected NewLeadInfoPage newLeadInfo;
	protected DuplicatingLeadPage duplicatingPage;
	protected OrganizationsPage organizations;
	protected CreateNewOrganizationPage createOrganization;
	protected NewOrganizationInfoPage newOrganization;
	protected CalendarPage cal;
	
	
	//@BeforeSuite
	//@BeforeTest
	
	@BeforeClass
	public void classSetup() 
	{
		excel = new ExcelUtility();
		property = new PropertyFileUtility();
		javaUtil = new JavaUtility();
		sjavaUtil = javaUtil;
		web = new WebDriverUtility();

		property.propertyFileInitialization(IConstantPath.PROPERTY_FILE_PATH);
		excel.excelFileInitialization(IConstantPath.EXCEL_FILE_PATH);
		
		long time = Long.parseLong(property.fetchProperty("timeouts"));
		
		driver = web.openApplication(property.fetchProperty("browser"), property.fetchProperty("url"), time);
		sdriver=driver;
	}
	
	@BeforeMethod
	public void methodSetup() {
		
		sdriver = driver;
		Assert.assertTrue(driver.getTitle().contains("vtiger"));

		login = new LoginPage(driver);
		login.loginToApp(property.fetchProperty("username"), property.fetchProperty("password"));
		Assert.assertTrue(driver.getTitle().contains("Home"));
		
		home = new HomePage(driver);
		contacts = new ContactsPage(driver);
		createContact = new CreateNewContactPage(driver);
		newContact = new NewContactInfoPage(driver);	
		leads = new LeadsPage(driver);
		createNewLead = new CreateNewLeadPage(driver);
		newLeadInfo = new NewLeadInfoPage(driver);
		duplicatingPage = new DuplicatingLeadPage(driver);
		organizations = new OrganizationsPage(driver);
		createOrganization = new CreateNewOrganizationPage(driver);
		newOrganization = new NewOrganizationInfoPage(driver);
		toDo =new CreateToDo(driver);
		cal = new CalendarPage(driver);
		
	}
	
	@AfterMethod
	public void methodTearDown()
	{
		home.signOutOfApp(web);
		web.closeBrowser();
	}
	
	@AfterClass
	public void classTeardown() {
		excel.closeWorkbook();
	}
	
	//@AfterTest
	//@AfterSuite

}
