package scripts.ContactServices;


import libs.clients.AndroidClientKeywords;
import libs.clients.IOSClientKeywords;
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
import io.appium.java_client.ios.IOSDriver;

import static org.junit.Assert.*;
import testData.aadsData;

public class CS_047 {
	
/*#################################################################################
 * CS-047 - Editing  contacts on iOS client by searching LDAP contacts
	"Objective: To verify that contacts are edited  by searching LDAP contacts iOS client logged via Auto Configuration
	 
	Procedures:
	Step 1: User A logged into iOS client via Auto Configuration
	Step 2: Searching LDAP contacts and editing 10 contacts
	Step 3: Checking above contacts of user A on SMGR
	 
	Expected results:
	Step 1: Successfully logged into iOS client via Auto Configuration
	Step 2: Successfully editing 10 contacts 
	Step 3: Ensure that the change on above contacts of user A would be existed on SMGR"
 * ################################################################################*/

	static IOSDriver iOSClientDriver;
	IOSClientKeywords iosClient = new IOSClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	final static Logger logger = LogManager.getLogger("CS_047");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "Users";
	ExcelData excel = new ExcelData();
	
	@Before
	public void beforeTest_ContactService_047() throws Exception {
		logger.info("beforeTest ContactService_047 starting...\n");
		iOSClientDriver = driverMgnt.createIOSClientDriver();
		logger.info("beforeTest ContactService_047 completed...\n");
	}
	
	@Test
	public void testContactService_047() throws Exception {
		logger.info("test ContactService_047 - Starting\n");
		try {

			for (int i=1;i<=numberOfContact;i++) {
			String DN= excel.User_Switch_DN(i, sheet);	
			boolean n= iosClient.editExistedContactInfo(iOSClientDriver, DN, "company", "TMA Solution", "");
			if(n) System.out.println("Adding " +DN+ "- PASSED...");
			else System.out.println("Adding " +DN+ "- FAILED...");
			assertTrue(n);
		
			}
		} catch (Exception exception) {
			fail("ContactService_047 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		iOSClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
