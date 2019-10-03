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

public class CS_039 {

/*################################################################################
 * CS-039 - Adding  contacts on Windows client by searching SMGR contacts
	"Objective: To verify that contacts are added by searching SMGR contacts Windows client logged via Auto Configuration
	 
	Procedures:
	Step 1: User A logged into Windows client via Auto Configuration
	Step 2: Searching SMGR contacts and adding 10 contacts
	Step 3: Checking above contacts of user A on SMGR
	 
	Expected results:
	Step 1: Successfully logged into Windows client via Auto Configuration
	Step 2: Successfully adding 10 contacts 
	Step 3: Ensure that above contacts of user A would be existed on SMGR"
#################################################################################*/
	
	static WiniumDriver winClientDriver;
	static WindowsDriver<?> windowsDriver;
	static WindowsDriver<?> windowsDriverRoot;
	Selenium selenium = new Selenium();
	WindowsClientKeywords winClient = new WindowsClientKeywords();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	final static Logger logger = LogManager.getLogger("CS_039");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "SMGR_Users";
	ExcelData excel = new ExcelData();
	
	String Winium_URL = aadsData.WiniumURL(1);
	String WinApp_URL = aadsData.WinAppURL(1) ;
	
	@Before
	public void beforeTest_ContactService_039() throws Exception {
		logger.info("beforeTest ContactService_039 starting...\n");
		winClientDriver = driverMgnt.createWindowsClientDriver(Winium_URL);
		windowsDriverRoot = driverMgnt.createWinAppDriver(WinApp_URL);
		winClient.confirmOpenApp(windowsDriverRoot);
		logger.info("beforeTest ContactService_039 completed...\n");
	}
	
	@Test
	public void testContactService_039() throws Exception {
		logger.info("test ContactService_039 - Starting\n");
		try {
			for (int i=1;i<=numberOfContact;i++) {
			String DN= excel.User_Switch_DN(i, sheet);	
			boolean n=winClient.addContactBySearch(windowsDriverRoot, DN);
			if(n) System.out.println("Adding " +DN+ "- PASSED...");
			else System.out.println("Adding " +DN+ "- FAILED...");
			assertTrue(n);
		
			}
		} catch (Exception exception) {
			fail("ContactService_039 - Failed - Exception occurs: "	+ exception.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown starting...\n");
		windowsDriverRoot.quit();
		logger.info("tearDown completed...\n");
	}
}
