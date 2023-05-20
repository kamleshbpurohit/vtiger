package testngImplementation;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateAndDuplicateLeadTest extends BaseClass
{

	@Test
	public void createAndDuplicateLeadTest() throws IOException {
		SoftAssert soft = new SoftAssert();
		home.clickLeadsTab();
		
		leads.clickPlusButton();
		soft.assertTrue(createNewLead.getPageHeader().contains("Creating New Lead"));
				
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Lead");
		
		createNewLead.selectSalutation(web, map.get("First Name Salutation"));
		
		String leadName = map.get("Last Name")+javaUtil.generateRandomNumber(100);
		createNewLead.setLastName(leadName);
		createNewLead.setCompany(map.get("Company"));
		createNewLead.clickSaveButton();
		
		soft.assertTrue(newLeadInfo.getPageHeader().contains(leadName));
		
		newLeadInfo.clickDuplicateButton();
		soft.assertTrue(duplicatingPage.getPageHeader().contains(leadName));
		
		String newLastName =map.get("New Last Name")+javaUtil.generateRandomNumber(100);
		duplicatingPage.setNewLeadName(newLastName);
		duplicatingPage.clickSaveButton();
		
		soft.assertTrue(newLeadInfo.getPageHeader().contains(newLastName));
		
		newLeadInfo.clickLeads();
		soft.assertTrue(leads.getLastLeadName().contains(newLastName));
		
		if(leads.getLastLeadName().contains(newLastName)) 
		{
			System.out.println("Test Case passed");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
			
		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", IConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
		soft.assertAll();	
	}

}
