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
public class CS_046 {

/*#########################################################################################
 * CS-046 - Editing  contacts on Android client by searching LDAP contacts
	"Objective: To verify that contacts are edited  by searching LDAP contacts Android client logged via Auto Configuration
	 
	Procedures:
	Step 1: User A logged into Android client via Auto Configuration
	Step 2: Searching LDAP contacts and editing 10 contacts
	Step 3: Checking above contacts of user A on SMGR
	 
	Expected results:
	Step 1: Successfully logged into Android client via Auto Configuration
	Step 2: Successfully editing 10 contacts 
	Step 3: Ensure that the change on above contacts of user A would be existed on SMGR"
 * ########################################################################################*/	
	
	static AndroidDriver<?> androidClientDriver;
	AndroidClientKeywords androidClient = new AndroidClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();

	final static Logger logger = LogManager.getLogger("CS_046");
	String configurationName = "configuration1";
	int numberOfContact = 3;
	String sheet = "Users";
	ExcelData excel = new ExcelData();
	static ExtentReports reports;
	ExtentTest Sresult;
	
	@Before
	public void beforeTest_ContactService_046() throws Exception {
		logger.info("beforeTest_AddContact starting...\n");
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		reports = ExtentManager.GetExtent();
		logger.info("beforeTest_AddContact completed...\n");
	}
	
	@Test
	public void testAddContact() throws Exception {
		logger.info("testAddContact - Starting\n");
		Sresult = reports.createTest("[ACA] Contact Services CS_046", "Edit Contact Exist Info DN, company ,Location  - Android");
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
			fail("testAddContact - Failed - Exception occurs: "	+ exception.toString());
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
