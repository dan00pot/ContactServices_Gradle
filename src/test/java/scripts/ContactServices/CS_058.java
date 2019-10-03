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

public class CS_058 {
	
/*######################################################################
 * CS-058 - Deleting  contacts on Android client by searching SMGR contacts
	"Objective: To verify that contacts are edited  by searching SMGR contacts Android client logged via Auto Configuration
	 
	Procedures:
	Step 1: User A logged into Android client via Auto Configuration
	Step 2: Searching SMGR contacts and deleting 10 contacts
	Step 3: Checking above contacts of user A on SMGR
	 
	Expected results:
	Step 1: Successfully logged into Android client via Auto Configuration
	Step 2: Successfully deleting 10 contacts
	Step 3: Ensure that 10 above contacts wouldn't be exited on SMGR"
 * #####################################################################*/

	static AndroidDriver<?> androidClientDriver;
	AndroidClientKeywords androidClient = new AndroidClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	Logger logger = LogManager.getLogger("CS_058");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "SMGR_Users";
	ExcelData excel = new ExcelData();
	static ExtentReports reports;
	ExtentTest Sresult;
	
	@Before
	public void beforeTest_ContactService_058() throws Exception {
		logger.info("beforeTest_ContactService_058 starting...\n");
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		reports = ExtentManager.GetExtent();
		logger.info("beforeTest_ContactService_058 completed...\n");
	}
	
	@Test
	public void testContactService_058() throws Exception {
		Sresult = reports.createTest("[ACA] Contact Services CS_058, remove Contact in contact list SMGR_Users Info DN  - Android");
		logger.info("Test ContactService_058 - Starting\n");
		try {
		//	androidClient.navigateToContactScreen(androidClientDriver);
			for (int i=1;i<=numberOfContact;i++) {
			String DN= excel.User_Switch_DN(i, sheet);	
			boolean n=androidClient.removeContactInContactList(androidClientDriver, DN);
			if(n=true) {
				Sresult.log(Status.PASS, "Adding : " +DN+ " - PASSED...");
				System.out.println("Adding " +DN+ "- PASSED...");
			}
			else {
				System.out.println("Adding " +DN+ "- FAILED...");
				Sresult.log(Status.FAIL, "Adding " +DN+ "- FAILED...");
				continue;
			}
			assertTrue(n);

		
			}
		} catch (Exception exception) {
			fail("ContactService_058  - Failed - Exception occurs: "	+ exception.toString());
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
