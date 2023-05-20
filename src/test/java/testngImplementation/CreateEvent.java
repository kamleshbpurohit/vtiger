package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateEvent extends BaseClass
{
	@Test
	public void createEvent()
	{
		SoftAssert soft = new SoftAssert();
		
		home.selectQuickCreateDropdown(web);
		
		soft.assertTrue(toDo.getCreateToDoHeader().equals("Create To Do"));
		
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Event");
		
		toDo.setsubjectTF(map.get("Subject"));
		toDo.handleStatusDropdown(web, map.get("Status"));
		
		toDo.selectEndDate();
		toDo.selectRequiredDate(map.get("Month"), map.get("End Date"));
		toDo.selectStartDate();
		toDo.selectRequiredDate(map.get("Month"), map.get("Start Date"));
		
		toDo.setTimeTF(map.get("Time"));
		toDo.handleActivityTypeDropdown(web, map.get("Activity Type"));
		toDo.clickGroupRadiobox();
		toDo.handleGroupDD(web, map.get("Group"));
		toDo.clickOnSaveButton();
		
		
	    boolean	status=cal.getEventSubjectPageheader().contains(map.get("Subject"));
		
		if(status)
		{
			System.out.println("pass");
			excel.writeDataIntoExcel("TestData", "Pass", IConstantPath.EXCEL_FILE_PATH, "Create Event");
		}
		else {
			{
				System.out.println("fail");
				soft.fail();
			}
		}
		
		soft.assertAll();
	}
}
