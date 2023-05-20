package testngImplementation;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;

public class DeleteLead extends BaseClass
{
	@Test
	public void deleteLead()
	{
		SoftAssert soft = new SoftAssert();
		
		home.clickLeadsTab();
		int leadsBefore = leads.getExistingLeads();
		leads.clickOnDelTab();
		
		web.alertPopup();
		
		int leadsAfter = leads.getExistingLeads();
		
		if(leadsBefore!=leadsAfter)
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("fail");
			soft.fail();
		}
		
		soft.assertAll();
	}

}
