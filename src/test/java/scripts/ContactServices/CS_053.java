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

public class CS_053 {

/*#############################################################
 * CS-053 - Deleting private contacts on iOS client
	"Objective: To verify that private contacts are edited  on iOS client logged via Auto Configuration
	 
	Procedures:
	Step 1: User A logged into iOS client via Auto Configuration
	Step 2: Deleting 10 private contacts (They are not added by searching SMGR contacts or LDAP contacts)
	Step 3: Checking above contacts of user A on SMGR
	 
	Expected results:
	Step 1: Successfully logged into iOS client via Auto Configuration
	Step 2: Successfully deleting 10 private contacts 
	Step 3: Ensure that 10 above contacts wouldn't be exited on SMGR"
 * ############################################################*/
	
	static IOSDriver iOSClientDriver;
	IOSClientKeywords iosClient = new IOSClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	final static Logger logger = LogManager.getLogger("CS_053");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "Users";
	ExcelData excel = new ExcelData();
	
	@Before
	public void beforeTest_ContactService_053() throws Exception {
		logger.info("beforeTest_ContactService_053 starting...\n");
		iOSClientDriver = driverMgnt.createIOSClientDriver();
		logger.info("beforeTest_ContactService_053 completed...\n");
	}
	
	@Test
	public void testAddContact() throws Exception {
		logger.info("test ContactService_053 - Starting\n");
		try {
	
			iosClient.navigateToContactScreen(iOSClientDriver);
			for (int i=1;i<=numberOfContact;i++) {
				String firstName= "Private";	
				String lastName= "Contact" +i ;
				int b = aadsData.AADS_ADD_PRIVATE_CONTACT_USER1 + i;
				String phoneNumber = String.valueOf(b);
				String emailAddress = ("local"+b+"@aam1.com");
				String newFirstName= "Edited" ;
				
				String contact = newFirstName + " " + lastName;
				boolean n=iosClient.removeContact(iOSClientDriver, contact);
			
				if(n) System.out.println("Adding " +firstName +" "+ lastName+ "- PASSED...");
				else System.out.println("Adding " +firstName +" "+ lastName+ "- FAILED...");
				assertTrue(n);
		
			}
		} catch (Exception exception) {
			fail("ContactService_053 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		iOSClientDriver.quit();
		logger.info("tearDown completed...\n");
	}
}
