package scripts.ContactServices;

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
import io.appium.java_client.windows.WindowsDriver;

import static org.junit.Assert.*;
import testData.aadsData;

public class CS_036 {

/*###############################################################################
 * CS-036 - Adding  contacts on Windows client by searching LDAP contacts
	"Objective: To verify that contacts are added by searching LDAP contacts Windows client logged via Auto Configuration
	 
	Procedures:
	Step 1: User A logged into Windows client via Auto Configuration
	Step 2: Searching LDAP contacts and adding 10 contacts
	Step 3: Checking above contacts of user A on SMGR
	 
	Expected results:
	Step 1: Successfully logged into Windows client via Auto Configuration
	Step 2: Successfully adding 10 contacts 
	Step 3: Ensure that above contacts of user A would be existed on SMGR"
###############################################################################*/
	
	static WiniumDriver winClientDriver;
	static WindowsDriver<?> windowsDriver;
	static WindowsDriver<?> windowsDriverRoot;
	Selenium selenium = new Selenium();
	WindowsClientKeywords winClient = new WindowsClientKeywords();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	
	final static Logger logger = LogManager.getLogger("CS_036");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "Users";
	ExcelData excel = new ExcelData();
	
	String Winium_URL = aadsData.WiniumURL(1);
	String WinApp_URL = aadsData.WinAppURL(1) ;
	
	@Before
	public void beforeTest_ContactService_036() throws Exception {
		logger.info("beforeTest_ContactService_036 starting...\n");
		winClientDriver = driverMgnt.createWindowsClientDriver(Winium_URL);
		windowsDriverRoot = driverMgnt.createWinAppDriver(WinApp_URL);
		winClient.confirmOpenApp(windowsDriverRoot);
		logger.info("beforeTest_AddContact completed...\n");
	}
	
	@Test
	public void testAddContact() throws Exception {
		logger.info("testContactService_036 - Starting\n");
		try {
			for (int i=1;i<=numberOfContact;i++) {
			String DN= excel.User_Switch_DN(i, sheet);	
			boolean n=winClient.addContactBySearch(windowsDriverRoot, DN);
			if(n) System.out.println("Adding " +DN+ "- PASSED...");
			else System.out.println("Adding " +DN+ "- FAILED...");
			assertTrue(n);
			}
		} catch (Exception exception) {
			fail("testContactService_036 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
	//	windowsDriverRoot.quit();
		logger.info("tearDown completed...\n");
	}
}
