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

public class CS_049 {
	
/*#####################################################################################
 * CS-049 - Editing  contacts on Android client by searching SMGR contacts
	"Objective: To verify that contacts are edited  by searching SMGR contacts Android client logged via Auto Configuration
	 
	Procedures:
	Step 1: User A logged into Android client via Auto Configuration
	Step 2: Searching SMGR contacts and editing 10 contacts
	Step 3: Checking above contacts of user A on SMGR
	 
	Expected results:
	Step 1: Successfully logged into Android client via Auto Configuration
	Step 2: Successfully editing 10 contacts 
	Step 3: Ensure that the change on above contacts of user A would be existed on SMGR"
 * ####################################################################################*/

	static AndroidDriver<?> androidClientDriver;
	AndroidClientKeywords androidClient = new AndroidClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();


	final static Logger logger = LogManager.getLogger("CS_049");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "SMGR_Users";
	ExcelData excel = new ExcelData();
	static ExtentReports reports;
	ExtentTest Sresult;
	
	@Before
	public void beforeTest_ContactService_049() throws Exception {
		logger.info("beforeTest ContactService_049 starting...\n");
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		reports = ExtentManager.GetExtent();
		logger.info("beforeTest ContactService_049 completed...\n");
	}
	
	@Test
	public void testContactService_049() throws Exception {
		logger.info("test ContactService_049 - Starting\n");
		Sresult = reports.createTest("[ACA] Contact Services CS_049, Edit Contact Exist Info DN SMGR_USER, company, Location  - Android");
		try {
			androidClient.navigateToContactScreen(androidClientDriver);
			for (int i=1;i<=numberOfContact;i++) {
			String DN= excel.User_Switch_DN(i, sheet);	
			boolean n=androidClient.editExistedContactInfo(androidClientDriver, DN, "company", "TMA Lab 4","");
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
			fail("ContactService_049 - Failed - Exception occurs: "	+ exception.toString());
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
