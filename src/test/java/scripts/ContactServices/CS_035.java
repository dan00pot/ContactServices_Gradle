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

public class CS_035 {
	
/*###################################################################################
 * CS-035 - Adding private contacts on iOS client
	"Objective: To verify that private contacts are added on iOS client logged via Auto Configuration
	 
	Procedures:
	Step 1: User A logged into iOS client via Auto Configuration
	Step 2: Adding 10 private contacts (not searching SMGR or LDAP)
	Step 3: Checking above contacts of user A on SMGR
	 
	Expected results:
	Step 1: Successfully logged into iOS client via Auto Configuration
	Step 2: Successfully adding 10 private contacts 
	Step 3: Ensure that above contacts of user A would be existed on SMGR"
 ###############################################################################*/

	static IOSDriver iOSClientDriver;
	IOSClientKeywords iosClient = new IOSClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	final static Logger logger = LogManager.getLogger("CS_035");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "Users";
	ExcelData excel = new ExcelData();
	
	@Before
	public void beforeTest_ContactService_035() throws Exception {
		logger.info("beforeTest_AddContact starting...\n");
		iOSClientDriver = driverMgnt.createIOSClientDriver();
		logger.info("beforeTest_AddContact completed...\n");
	}
	
	@Test
	public void testAddContact() throws Exception {
		logger.info("testAddContact - Starting\n");
		try {
	

			for (int i=1;i<=numberOfContact;i++) {
				String firstName= "Private";	
				String lastName= "Contact" +i ;
				int b = aadsData.AADS_ADD_PRIVATE_CONTACT_USER1 + i;
				String phoneNumber = String.valueOf(b);
				String emailAddress = ("local"+b+"@aam1.com");
				
				boolean n=iosClient.addPrivateContact(iOSClientDriver, firstName, lastName, phoneNumber, emailAddress);
			
				if(n) System.out.println("Adding " +firstName +" "+ lastName+ "- PASSED...");
				else System.out.println("Adding " +firstName +" "+ lastName+ "- FAILED...");
				assertTrue(n);
		
			}
		} catch (Exception exception) {
			fail("testAddContact - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		iOSClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
