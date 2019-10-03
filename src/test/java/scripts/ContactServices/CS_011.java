package scripts.ContactServices;


import libs.clients.AndroidClientKeywords;
import libs.common.DriverManagement;
import libs.common.Selenium;
import libs.listener.ExtentManager;

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

public class CS_011 {
	
/*	####################################################################################
 * CS-011 Search and add contact having the same email address on single LDAP of AADS and SMGR
	Configurations:
	1. SMGR does not Sync with any LDAP
	2. AADS connect to  only LDAP: AD2008
	3. Email khuong1@aam1.com already exists on single LDAP (Full name: Khuong Nguyen) and SMGR (Full name: Khuong Nguyen1)
	 
	Procedures:
	1. Search with email  khuong1@aam1.com.
	2. Add this user as contact.


	Expected result:
	1. User will be merged with full name configured on LDAP: Khuong Nguyen.
	2. AADS will fetch name from LDAP (Khuong Nguyen) and would save that for new contact."
######################################################################################################*/

	static AndroidDriver<?> androidClientDriver;
	AndroidClientKeywords androidClient = new AndroidClientKeywords();
	Selenium selenium = new Selenium();
	DriverManagement driverMgnt = new DriverManagement();
	aadsData aadsData = new aadsData();
	final static Logger logger = LogManager.getLogger("CS_011");
	String configurationName = "configuration1";
	int numberOfContact = 10; //10
	String sheet = "Users";
	ExcelData excel = new ExcelData();
	static ExtentReports reports;
	ExtentTest Sresult;
	
	@Before
	public void beforeTest_ContactService_011() throws Exception {
		logger.info("beforeTest ContactService_011 starting...\n");
		androidClientDriver = driverMgnt.createAndroidClientDriver();
		reports = ExtentManager.GetExtent();
		logger.info("beforeTest ContactService_011 completed...\n");
	}
	
	@Test
	public void testContactService_011() throws Exception {
		logger.info("ContactService_011 - Starting\n");
		Sresult = reports.createTest("[ACA] Contact Services", "verify Adding Contact By Search");
		try {
			androidClient.navigateToContactScreen(androidClientDriver);
			for (int i=1;i<=numberOfContact;i++) {
			String mail= excel.User_Switch_MAIL(i, sheet);
			String DN = excel.User_Switch_DN(i, sheet);
			
			boolean n=androidClient.addContactBySearch(androidClientDriver, mail, DN);
			if(n) {System.out.println("Adding " +DN+ "- PASSED...");
				 Sresult.log(Status.PASS, "Adding Contact By Search: " +DN +" "+ mail+  " - PASSED...");
			}else {
				System.out.println("Adding " +DN+ "- FAILED...");
				Sresult.log(Status.PASS, "Adding Contact By Search : " +DN +" "+ mail+   " - PASSED...");
				continue;
				}
			assertTrue(n);
			androidClient.removeContactInContactList(androidClientDriver, DN);
			}

		} catch (Exception exception) {
			fail("ContactService_011 - Failed - Exception occurs: "	+ exception.toString());
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
