package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateOrganizationWithIndustryAndType extends BaseClass
{
	@Test
	public void CreateOrganizationWithIndustry()
	{
		SoftAssert soft = new SoftAssert();
		home.clickOrganizationsTab();
		
		soft.assertTrue(organizations.getPageHeader().contains("Organizations"));
		organizations.clickPlusButton();
		
		soft.assertTrue(createOrganization.getPageHeader().contains("Creating New Organization"));
		
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Organization");
		
		String newOrganizationName = map.get("Organization Name")+javaUtil.generateRandomNumber(100);
				
		createOrganization.setOrganizationName(newOrganizationName);
		createOrganization.selectIndustry(web, map.get("Industry"));
		createOrganization.selectType(web, map.get("type"));
		createOrganization.clickGroupRadioButton();
		createOrganization.selectGroupFromDropdown(web, map.get("Group"));
		createOrganization.clickSave();
		
		soft.assertTrue(newOrganization.getPageHeader().contains(newOrganizationName));

		newOrganization.clickOrganization();
		
		soft.assertTrue(organizations.getPageHeader().contains("Organizations"));

		soft.assertTrue(organizations.getNewOrganization().equalsIgnoreCase(newOrganizationName));
		if (organizations.getNewOrganization().equalsIgnoreCase(newOrganizationName)) {
			System.out.println("Test Case Passed");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH, "Create Organization");
		}
			
		else 
		{
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", IConstantPath.EXCEL_FILE_PATH, "Create Organization");
		}
		soft.assertAll();
	}
}
