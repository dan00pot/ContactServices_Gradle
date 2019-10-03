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

public class CS_059 {
	
/*###############################################################
 * CS-059 - Deleting  contacts on iOS client by searching SMGR contacts
	"Objective: To verify that contacts are edited  by searching SMGR contacts iOS client logged via Auto Configuration
	 
	Procedures:
	Step 1: User A logged into iOS client via Auto Configuration
	Step 2: Searching SMGR contacts and deleting 10 contacts
	Step 3: Checking above contacts of user A on SMGR
	 
	Expected results:
	Step 1: Successfully logged into iOS client via Auto Configuration
	Step 2: Successfully deleting 10 contacts
	Step 3: Ensure that 10 above contacts wouldn't be exited on SMGR"
 * ###############################################################*/

	static IOSDriver iOSClientDriver;
	IOSClientKeywords iosClient = new IOSClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	final static Logger logger = LogManager.getLogger("CS_059");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "SMGR_Users";
	ExcelData excel = new ExcelData();
	
	@Before
	public void beforeTest_ContactService_059() throws Exception {
		logger.info("beforeTest_ContactService_059 starting...\n");
		iOSClientDriver = driverMgnt.createIOSClientDriver();
		logger.info("beforeTest_ContactService_059 completed...\n");
	}
	
	@Test
	public void testContactService_059() throws Exception {
		logger.info("test ContactService_059 - Starting\n");
		try {
		iosClient.navigateToContactScreen(iOSClientDriver);

			for (int i=1;i<=numberOfContact;i++) {
			String DN= excel.User_Switch_DN(i, sheet);	
			boolean n=iosClient.removeContact(iOSClientDriver,DN);
			if(n) System.out.println("Adding " +DN+ "- PASSED...");
			else System.out.println("Adding " +DN+ "- FAILED...");
			assertTrue(n);
		
			}
		} catch (Exception exception) {
			fail("ContactService_059 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		iOSClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
