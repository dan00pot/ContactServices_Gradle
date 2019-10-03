package scripts.ContactServices;


import libs.clients.AndroidClientKeywords;
import libs.common.DriverManagement;
import libs.common.Selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import excel.ExcelData;
import excel.ExcelUtils;
import io.appium.java_client.android.AndroidDriver;

import static org.junit.Assert.*;
import testData.aadsData;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import libs.listener.ExtentManager;
public class CS_043 {
	
/*#######################################################################################
 * CS-043 - Editing private contacts on Android client
	"Objective: To verify that private contacts are edited  on Android client logged via Auto Configuration
	 
	Procedures:
	Step 1: User A logged into Android client via Auto Configuration
	Step 2: Editing 10 private contacts (They are not added by searching SMGR contacts or LDAP contacts)
	Step 3: Checking above contacts of user A on SMGR
	 
	Expected results:
	Step 1: Successfully logged into Android client via Auto Configuration
	Step 2: Successfully editing 10 private contacts 
	Step 3: Ensure that the change on above contacts of user A would be existed on SMGR"
#########################################################################################*/

	static AndroidDriver<?> androidClientDriver;
	AndroidClientKeywords androidClient = new AndroidClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	
	final static Logger logger = LogManager.getLogger("CS_043");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "Users";
	ExcelData excel = new ExcelData();
	static ExtentReports reports;
	ExtentTest Sresult;
	
	@Before
	public void beforeTest_ContactService_043() throws Exception {
		logger.info("beforeTest ContactService_043 starting...\n");
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		reports = ExtentManager.GetExtent();
		logger.info("beforeTest ContactService_043 completed...\n");
	}
	
	@Test
	public void testContactService_043() throws Exception {
		logger.info("test ContactService_043 - Starting\n");
		Sresult = reports.createTest("[ACA] Contact Services CS_043", "Edit Contact Exist Info firstname and lastname - Android");
		try {
			for (int i=1;i<=numberOfContact;i++) {
			String firstName= "Private";	
			String lastName= "Contact" +i ;
			int b = aadsData.AADS_ADD_PRIVATE_CONTACT_USER1 + i;
			String phoneNumber = String.valueOf(b);
			String emailAddress = ("local"+b+"@aam1.com");
			String newFirstName= "Edited" ;
			
			String contact = firstName + " " + lastName;
			
			boolean n=androidClient.editExistedContactInfo(androidClientDriver, contact, "first_name", newFirstName, lastName);			

			if(n=true) {
				System.out.println("Editting " +firstName +" "+ lastName+ "- PASSED...");
				Sresult.log(Status.PASS, "Editting : " +firstName +" "+ lastName+  " - PASSED...");
			}
			else {
				System.out.println("Editting " +firstName +" "+ lastName+ "- FAILED...");
				Sresult.log(Status.FAIL, "Editting " +firstName +" "+ lastName+  "- FAILED...");
				continue;
			}
			assertTrue(n);
		
			}
		} catch (Exception exception) {
			fail("testContactService_043 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
//		reports.flush();
		androidClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
