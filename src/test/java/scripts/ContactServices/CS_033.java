package scripts.ContactServices;


import libs.clients.AndroidClientKeywords;
import libs.clients.WindowsClientKeywords;
import libs.common.DriverManagement;
import libs.common.Selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.winium.WiniumDriver;

import excel.ExcelData;
import excel.ExcelUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.windows.WindowsDriver;

import static org.junit.Assert.*;
import testData.aadsData;

public class CS_033 {

/*#########################################################################
 * CS-033 - Adding private contacts on Windows client
 * "Objective: To verify that private contacts are added on Windows client logged via Auto Configuration
 
	Procedures:
	Step 1: User A logged into Windows client via Auto Configuration
	Step 2: Adding 10 private contacts (not searching SMGR or LDAP)
	Step 3: Checking above contacts of user A on SMGR
	 
	Expected results:
	Step 1: Successfully logged into Windows client via Auto Configuration
	Step 2: Successfully adding 10 private contacts 
	Step 3: Ensure that above contacts of user A would be existed on SMGR"

############################################################################*/
	
	WindowsClientKeywords winClient = new WindowsClientKeywords();
	
	
	static WiniumDriver winClientDriver;
	static WindowsDriver<?> windowsDriver;
	static WindowsDriver<?> windowsDriverRoot;
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();
	
	final static Logger logger = LogManager.getLogger("CS_033");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "Users";
	ExcelData excel = new ExcelData();
	
	String Winium_URL = aadsData.WiniumURL(1);
	String WinApp_URL = aadsData.WinAppURL(1) ;
	
	@Before
	public void beforeTestContactService_033() throws Exception {
		logger.info("beforeTest_ContactService_033 starting...\n");
		winClientDriver = driverMgnt.createWindowsClientDriver(Winium_URL);
		windowsDriverRoot = driverMgnt.createWinAppDriver(WinApp_URL);
		winClient.confirmOpenApp(windowsDriverRoot);
		logger.info("beforeTest_AddContact completed...\n");
	}
	
	@Test
	public void testContactService_033() throws Exception {
		logger.info("test ContactService_033 - Starting\n");
		try {
			
			winClient.mainWindowsNavigateToContactTab(windowsDriverRoot);
			
			for (int i=1;i<=numberOfContact;i++) {
			String firstName= "Private";	
			String lastName= "Contact" +i ;
			int b = aadsData.AADS_ADD_PRIVATE_CONTACT_USER1 + i;
			String phoneNumber = String.valueOf(b);
			String emailAddress = ("local"+b+"@aam1.com");
			
			boolean s=winClient.addPrivateContact(windowsDriverRoot, firstName, lastName, phoneNumber, emailAddress);
		
			if(s) System.out.println("Adding " +firstName +" "+ lastName+ "- PASSED...");
			else System.out.println("Adding " +firstName +" "+ lastName+ "- FAILED...");
			assertTrue(s);
			}
		} catch (Exception exception) {
			fail("testContactService_033 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		windowsDriverRoot.quit();
		logger.info("tearDown completed...\n");
	}
}
