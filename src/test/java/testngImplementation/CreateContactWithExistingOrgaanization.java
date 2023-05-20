package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateContactWithExistingOrgaanization extends BaseClass
{
	@Test
	public void createContactWithExistingOrgaanization()
	{
		SoftAssert soft=new SoftAssert();
		
		home.clickContactsTab();
		
		soft.assertTrue(contacts.getPageHeader().contains("Contacts"));
		contacts.clickPlusButton();
		
		soft.assertTrue(createContact.getPageHeader().contains("Creating New Contact"));
		Map<String, String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Contact With Existing Organization");
		createContact.selectFirstNameSalutation(web, map.get("First Name Salutation"));
		String lastname= map.get("Last Name")+ javaUtil.generateRandomNumber(100);
		createContact.setLastName(lastname);
		createContact.selectExistingOrganization(driver, map.get("Existing Organization"));
		createContact.uploadContactImage(map.get("Contact Image"));
		createContact.clickSaveButton();
		
		soft.assertTrue(newContact.getPageHeader().contains(lastname));
		newContact.clickContactsLink();
		
		soft.assertTrue(contacts.getPageHeader().contains("Contacts"));
		
		boolean status = contacts.getLastContactName().contains(lastname);
		soft.assertTrue(status);
		if(status)
		{
			System.out.println("pass");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH,"Create Contact With Existing Organization");
		}
		else
		{
			System.out.println("Fail");
			excel.writeDataIntoExcel("TestData", "Fail", IConstantPath.EXCEL_FILE_PATH,"Create Contact With Existing Organization");
		}
		
			
	}

}
